package edu.puj.feedi.activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import edu.puj.feedi.R;
import edu.puj.feedi.databinding.FragmentPedidosBinding;

public class PedidosFragment extends Fragment {

    FragmentPedidosBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPedidosBinding.inflate(inflater);
        //Spinner android
        llenarSpinner();
        return binding.getRoot();
    }

    private void llenarSpinner() {
        String [] campos = new String[]{getString(R.string.pse),getString(R.string.efectivo)};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.contenido_id,
                campos
        );
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) binding.medioPagoExposed;
        autoCompleteTextView.setAdapter(adapter);
    }
}