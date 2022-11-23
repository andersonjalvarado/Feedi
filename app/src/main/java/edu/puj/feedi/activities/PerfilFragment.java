package edu.puj.feedi.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import edu.puj.feedi.databinding.FragmentPerfilBinding;
import edu.puj.feedi.R;


public class PerfilFragment extends Fragment {

    FragmentPerfilBinding binding;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater);
        //binding.textEsp.setText(mAuth.getCurrentUser().getProviderId());
        binding.textCorreo.setText(mAuth.getCurrentUser().getEmail());
        //binding.TextCelular.setText(mAuth.getCurrentUser().getMetadata().toString());
        binding.btnCerrarSesion.setOnClickListener((view) -> {
            Context context = view.getContext();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            Toast.makeText(view.getContext(), "Sesión Cerrada", Toast.LENGTH_LONG).show();
        });
        return binding.getRoot();
    }
}