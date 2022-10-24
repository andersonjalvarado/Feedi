package edu.puj.feedi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.puj.feedi.R;
import edu.puj.feedi.databinding.ActivityClienteHomeBinding;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClienteHomeActivity extends AppCompatActivity {

    ActivityClienteHomeBinding binding;
    InicioFragment inicioFragment = new InicioFragment();
    PerfilFragment perfilFragment = new PerfilFragment();
    PedidosFragment pedidosFragment = new PedidosFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.setTitle("Feedi");

        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener);
        loadFagment(inicioFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.carrito:
                    loadFagment(pedidosFragment);
                    return true;
                case R.id.home:
                    loadFagment(inicioFragment);
                    return true;
                case R.id.perfil_usuario:
                    loadFagment(perfilFragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFagment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentView,fragment);
        transaction.commit();
    }
}