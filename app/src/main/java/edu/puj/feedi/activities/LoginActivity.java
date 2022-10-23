package edu.puj.feedi.activities;

import androidx.appcompat.app.AppCompatActivity;
import edu.puj.feedi.databinding.ActivityLoginBinding;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import edu.puj.feedi.R;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.setTitle("Inicio de Sesión");
        binding.signupButton.setOnClickListener(view -> startActivity(new Intent(this, RegistroActivity.class)));
    }
}