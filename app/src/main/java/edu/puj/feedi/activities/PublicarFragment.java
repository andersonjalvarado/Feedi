package edu.puj.feedi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import edu.puj.feedi.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import edu.puj.feedi.databinding.FragmentPublicarBinding;

public class PublicarFragment extends Fragment {

    FragmentPublicarBinding binding;
    private static final String ARG_PARAM ="correo";
    private String correo;
    String storage_path ="productos/*";
    private static final int CODE_SEL_STORAGE = 200;
    private static final int CODE_SEL_IMAGE = 300;
    private Uri img_url;
    String photo = "photo";
    String rute_storage;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();;
    StorageReference storageReference;

    public static PublicarFragment newInstance (String correo){
        PublicarFragment fragment = new PublicarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM,correo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            correo = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPublicarBinding.inflate(inflater);
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        binding.btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarFoto();
            }
        });
        binding.btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subirProducto(img_url);
            }
        });

        return binding.getRoot();
    }

    private void cargarFoto(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,CODE_SEL_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_SEL_IMAGE){
            img_url = data.getData();
            binding.imageThumbnail.setImageURI(img_url);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void subirProducto(Uri img_url){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        rute_storage = storage_path + "" + photo + "" + correo + timeStamp;
        StorageReference reference = storageReference.child(rute_storage);
        reference.putFile(img_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful()){
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            CollectionReference productos = firestore.collection("productos");
                            String download_uri = uri.toString();
                            HashMap<String,Object> map = new HashMap<>();
                            map.put("foto",download_uri);
                            map.put("nombre",binding.Nombre.getEditText().getText().toString());
                            map.put("cantidad",binding.cantidad.getEditText().getText().toString());
                            map.put("precio",binding.precio.getEditText().getText().toString());
                            map.put("correoRestaurante",correo);
                            productos.document(correo).set(map);
                            limpiardatos();
                            Toast.makeText(getContext(), "Producto Publicado", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al cargar el Producto", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void limpiardatos(){
        binding.Nombre.getEditText().setText("");
        binding.cantidad.getEditText().setText("");
        binding.precio.getEditText().setText("");
        Uri uri = Uri.parse("android.resource://edu.puj.feedi/" + R.drawable.logo);
        binding.imageThumbnail.setImageURI(uri);
    }


}