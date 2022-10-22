package edu.puj.feedi.activities;

import androidx.appcompat.app.AppCompatActivity;

import edu.puj.feedi.databinding.ActivityRegistroBinding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import edu.puj.feedi.R;

public class RegistroActivity extends AppCompatActivity {

    ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createButton.setOnClickListener(view -> {
            String nombre = binding.createDisplayName.getEditText().getText().toString();
            Toast.makeText(getBaseContext(), String.format("%s registrado con éxito, inicia sesión", nombre),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,LoginActivity.class);
           // intent.putExtra("nombre",nombre);
            startActivity(intent);
        });
    }
}