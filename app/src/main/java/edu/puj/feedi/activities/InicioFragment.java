package edu.puj.feedi.activities;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import edu.puj.feedi.databinding.FragmentInicioBinding;
import edu.puj.feedi.Adapters.RestauranteAdapter;
import edu.puj.feedi.R;
import edu.puj.feedi.model.Restaurante;

public class InicioFragment extends Fragment implements SearchView.OnQueryTextListener {

    FragmentInicioBinding binding;
    private ArrayList <Restaurante> restuarantes = new ArrayList<>();
    private int [] imgRestaurantes= {R.drawable.mirador,R.drawable.kiosko,R.drawable.sauce,R.drawable.crepera,R.drawable.urgencias};
    private int [] imgUbicRest= {R.drawable.ubic_mirador,R.drawable.ubic_kiosko,R.drawable.ubic_sauce,R.drawable.ubic_creperia,R.drawable.ubic_urgencias};
    RestauranteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInicioBinding.inflate(inflater);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setRestaurantes();

        adapter = new RestauranteAdapter(restuarantes);
        binding.recyclerView.setAdapter(adapter);

        binding.search.setOnQueryTextListener(this);

        return binding.getRoot();
    }

    private void setRestaurantes (){
        String[] nombresRestuarantes = getResources().getStringArray(R.array.nombres_restaurantes);
        for (int i = 0; i < nombresRestuarantes.length; i++)
            restuarantes.add(new Restaurante(nombresRestuarantes[i],
                    "Ofertas: " + String.valueOf(i+1), imgRestaurantes[i], imgUbicRest[i]));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }
}