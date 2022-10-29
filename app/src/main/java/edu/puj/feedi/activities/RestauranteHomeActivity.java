package edu.puj.feedi.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.puj.feedi.R;
import edu.puj.feedi.databinding.ActivityRestauranteHomeBinding;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RestauranteHomeActivity extends AppCompatActivity {

    ActivityRestauranteHomeBinding binding;
    PerfilFragment perfilFragment = new PerfilFragment();
    PublicarFragment publicarFragment = new PublicarFragment();
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestauranteHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.setTitle("Feedi");

        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener);
        loadFagment(publicarFragment);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.publicar:
                    loadFagment(publicarFragment);
                    return true;
                case R.id.perfil_restaurante:
                    loadFagment(perfilFragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFagment(Fragment fragment) {
        Bundle extras = getIntent().getExtras();
        fragment.setArguments(extras);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentView,fragment)
                .commit();

       /* FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentView,fragment);
        transaction.commit();
*/
    }
    private void recuperarDatosRestaurante() {

        Bundle extras = getIntent().getExtras();

        if(extras != null ){
            correo = extras.getString("correo");
        }
    }
}