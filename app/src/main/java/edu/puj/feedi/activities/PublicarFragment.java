package edu.puj.feedi.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.puj.feedi.databinding.FragmentPublicarBinding;

import edu.puj.feedi.R;

public class PublicarFragment extends Fragment {

    FragmentPublicarBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPublicarBinding.inflate(inflater);

        return binding.getRoot();
    }
}