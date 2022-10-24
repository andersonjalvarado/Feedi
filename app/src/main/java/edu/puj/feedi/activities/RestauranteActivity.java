package edu.puj.feedi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import edu.puj.feedi.Adapters.OfertasAdapter;
import edu.puj.feedi.R;
import edu.puj.feedi.databinding.ActivityRestauranteBinding;
import edu.puj.feedi.model.Oferta;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class RestauranteActivity extends AppCompatActivity {

    ActivityRestauranteBinding binding;
    private int n_ofertas, image, img_ubicacion;
    private String nombre;
    private ArrayList<Oferta> ofertas = new ArrayList<>();
    private int [] imgOfertas= {R.drawable.burrito,R.drawable.croissant,R.drawable.pescadito,R.drawable.sandwich,R.drawable.rollocanela};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestauranteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerViewOfertas.setLayoutManager(new LinearLayoutManager(this));

        this.setTitle("Restaurante");
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recuperarDatosRestaurante();
        binding.nomRestaurante.setText(nombre);
        binding.imgRestaurante.setImageResource(image);
        binding.imgUbicacionRest.setImageResource(img_ubicacion);

        setOfertas();
        OfertasAdapter adapter = new OfertasAdapter(ofertas);
        binding.recyclerViewOfertas.setAdapter(adapter);
    }

    private void recuperarDatosRestaurante() {

        Bundle extras = getIntent().getExtras();

        if(extras != null ){
            n_ofertas = extras.getInt("ofertas");
            nombre = extras.getString("nombre");
            image = extras.getInt("image");
            img_ubicacion = extras.getInt("img_ubicacion");
        }
    }

    private void setOfertas (){
        String[] nombresOfertas = getResources().getStringArray(R.array.nombres_ofertas);
        for (int i = 0; i < nombresOfertas.length; i++)
            ofertas.add(new Oferta(nombresOfertas[i],"Precio: $"+ String.valueOf(i+1) +"000",
                    "Cantidad: " + String.valueOf(i+1), imgOfertas[i]));
    }
}