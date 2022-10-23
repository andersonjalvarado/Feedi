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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.puj.feedi.R;

public class RegistroActivity extends AppCompatActivity {
    public static final String TAG = RegistroActivity.class.getName();
    ActivityRegistroBinding binding;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore = FirebaseFirestore.getInstance();

        this.setTitle("Crear Nuevo Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.createButton.setOnClickListener(view -> doSignUp());
    }

    private void doSignUp() {
        String email = binding.createEmail.getEditText().getText().toString();
        String pwd = binding.createPass.getEditText().getText().toString();
        String nombre = binding.createDisplayName.getEditText().getText().toString();
        String cel = binding.createPhone.getEditText().getText().toString();
        String rol = " ";

        if(email != null && !email.isEmpty() && pwd != null && !pwd.isEmpty() && nombre != null && !nombre.isEmpty() && cel != null && !cel.isEmpty() &&
                (binding.radioClient.isChecked() || binding.radioRestaurant.isChecked()) ){

            if (binding.radioClient.isChecked())
                rol = "Cliente";

            else if (binding.radioRestaurant.isChecked())
                rol = "Restaurante";

            postUser(email, pwd, nombre, cel, rol);
        }
        else{
            Toast.makeText(getBaseContext(), "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show();
            return;
        }


    }

    private void postUser(String email, String pwd, String nombre, String cel, String rol){
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("password", pwd);
        map.put("nombre", nombre);
        map.put("celular", cel);
        map.put("rol", rol);

        firestore.collection("Usuario").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getBaseContext(), String.format("%s registrado con éxito, inicia sesión", nombre),Toast.LENGTH_LONG).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(), "Error en los registros", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return false;
    }
}