package edu.puj.feedi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.puj.feedi.databinding.ActivityRegistroBinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.puj.feedi.R;

public class RegistroActivity extends AppCompatActivity {
    public static final String TAG = RegistroActivity.class.getName();
    ActivityRegistroBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.setTitle("Crear Nuevo Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.createButton.setOnClickListener(view -> registro());
    }

    private void registro() {
        String email = binding.createEmail.getEditText().getText().toString();
        String pwd = binding.createPass.getEditText().getText().toString();
        String nombre = binding.createDisplayName.getEditText().getText().toString();
        String cel = binding.createPhone.getEditText().getText().toString();
        String rol = " ";

        if(email == null || email.isEmpty() || pwd == null || pwd.isEmpty() || nombre == null || nombre.isEmpty() || cel == null ||cel.isEmpty() ||
                !(binding.radioClient.isChecked() || binding.radioRestaurant.isChecked()) ){
            Toast.makeText(getBaseContext(), "Llena todos los datos", Toast.LENGTH_LONG).show();
        }
        else{
            if (binding.radioClient.isChecked())
                rol = "Cliente";
            if (binding.radioRestaurant.isChecked())
                rol = "Restaurante";
            if(!email.matches("[a-zA-Z]+@[a-zA-Z]+(\\.[a-zA-Z]+)+")){
                Toast.makeText(getBaseContext(), "Digita un correo valido", Toast.LENGTH_LONG).show();
                return;
            }
            if(pwd.length() < 6){
                Toast.makeText(getBaseContext(), "Contraseña debe ser mayor a 6", Toast.LENGTH_LONG).show();
                return;
            }
            else
                postUser(email, pwd, nombre, cel, rol);
        }
    }

    private void postUser(String email, String pwd, String nombre, String cel, String rol){
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        Log.d(TAG, "email: " + email);
        user.put("password ", pwd);
        Log.d(TAG, "password " + pwd);
        user.put("nombre", nombre);
        Log.d(TAG, "nombre " + nombre);
        user.put("celular", cel);
        Log.d(TAG, "celular " + cel);
        user.put("rol", rol);
        Log.d(TAG, "rol" + rol);

        db.collection("Usuarios").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getBaseContext(), String.format("%s registrado con éxito, inicia sesión", nombre),Toast.LENGTH_LONG).show();
                doSignUp(email, pwd);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(), "Error en el registro", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void doSignUp(String email, String pwd){
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnSuccessListener(authResult -> {
                    Log.i(TAG, "doSignUp: " + authResult.getUser().getEmail() + " created.");
                }).addOnFailureListener(e -> {
                    Log.e(TAG, "doSignUp: " + e.toString());
                    Toast.makeText(getBaseContext(), "Error en el registro", Toast.LENGTH_LONG).show();
                });
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return false;
    }
}