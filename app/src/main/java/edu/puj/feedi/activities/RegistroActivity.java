package edu.puj.feedi.activities;

import androidx.appcompat.app.AppCompatActivity;

import edu.puj.feedi.databinding.ActivityRegistroBinding;
import android.os.Bundle;

import edu.puj.feedi.R;

public class RegistroActivity extends AppCompatActivity {

    ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}