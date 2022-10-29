package edu.puj.feedi.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.puj.feedi.databinding.ActivityLoginBinding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import edu.puj.feedi.R;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = RegistroActivity.class.getName();
    ActivityLoginBinding binding;
    String idUsuario, rol=" ";
    DocumentReference documentReference;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.setTitle("Inicio de Sesión");

        binding.signupButton.setOnClickListener(view -> startActivity(new Intent(this, RegistroActivity.class)));
        binding.loginButton.setOnClickListener(view -> doLogin());
        binding.forgotButton.setOnClickListener(view -> forgotPwd());
    }

    private void doLogin(){
        if(validarDatos()){
            String email = binding.loginEmail.getEditText().getText().toString();
            String pwd = binding.loginPass.getEditText().getText().toString();
            mAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnSuccessListener(authResult -> {
                        Log.i(TAG, "doLogin: login success");

                        Log.i(TAG, "email " + email);

                        documentReference = firestore.collection("Usuarios").document(email);
                        //acceder al rol
                       /* documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                rol = value.getString("rol");
                            }
                        });*/
                        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                        rol = document.getString("rol");
                                    } else {
                                        Log.d(TAG, "No such document");
                                    }
                                } else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                        //comparar rol
                        if (rol.equals("Cliente")){
                            Intent intent = new Intent(this, ClienteHomeActivity.class);
                            intent.putExtra("correo",email);
                            startActivity(intent);

                        }
                        if (rol.equals("Restaurante")){
                            Intent intent = new Intent(this, RestauranteHomeActivity.class);
                            intent.putExtra("correo",email);
                            startActivity(intent);

                        }
                    }).addOnFailureListener(e -> {
                        Log.e(TAG, "doLogin: " + e.toString());
                        Toast.makeText(getBaseContext(), "Usuario No Registrado", Toast.LENGTH_LONG).show();
                    });
        } else return;
    }

    private void forgotPwd(){
        String email = binding.loginEmail.getEditText().getText().toString();

        if(email == null || email.isEmpty()){
            Toast.makeText(getBaseContext(), "Digita el email", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnSuccessListener(unused ->
                Toast.makeText(getBaseContext(), String.format("Revise el correo %s con más instrucciones.", email),Toast.LENGTH_LONG).show());
    }

    private boolean validarDatos(){
        boolean valido = true;

        if (TextUtils.isEmpty(binding.loginEmail.getEditText().getText().toString())){
            binding.loginEmail.setError("Email vacío");
            valido = false;
        }else binding.loginEmail.setError(null);

        if (TextUtils.isEmpty(binding.loginPass.getEditText().getText().toString())){
            binding.loginPass.setError("Contraseña vacía");
            valido = false;
        }else binding.loginPass.setError(null);
        return valido;
    }
}