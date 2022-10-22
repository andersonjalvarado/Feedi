package edu.puj.feedi.activities;

import androidx.appcompat.app.AppCompatActivity;
import edu.puj.feedi.databinding.ActivityLoginBinding;

import android.content.Intent;
import android.os.Bundle;

import edu.puj.feedi.R;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signupButton.setOnClickListener(view -> startActivity(new Intent(this, RegistroActivity.class)));
    }
}