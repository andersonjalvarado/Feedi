package edu.puj.feedi.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import edu.puj.feedi.R;
import edu.puj.feedi.activities.RegistroActivity;
import edu.puj.feedi.activities.RestauranteActivity;
import edu.puj.feedi.model.Restaurante;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.MyViewHolder> {

    static ArrayList<Restaurante> restaurantes;
    ArrayList<Restaurante> listResultados;

    public RestauranteAdapter(ArrayList<Restaurante> restaurantes){
        this.restaurantes = restaurantes;
        listResultados = new ArrayList<>();
        listResultados.addAll(restaurantes);
    }

    @NonNull
    @Override
    public RestauranteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_restaurant, null,false);
        return new RestauranteAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewNombre.setText(restaurantes.get(position).getNombreRestaurante());
        holder.textViewOfertas.setText(restaurantes.get(position).getOfertas());
        holder.imageView.setImageResource(restaurantes.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewNombre, textViewOfertas;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.foto_restaurante);
            textViewNombre = (TextView) itemView.findViewById(R.id.nombre_restaurante);
            textViewOfertas =(TextView) itemView.findViewById(R.id.ofertas_restaurante);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RestauranteActivity.class);
                    intent.putExtra("nombre",restaurantes.get(getAdapterPosition()).getNombreRestaurante());
                    intent.putExtra("ofertas",restaurantes.get(getAdapterPosition()).getOfertas());
                    intent.putExtra("image",restaurantes.get(getAdapterPosition()).getImage());
                    intent.putExtra("img_ubicacion",restaurantes.get(getAdapterPosition()).getImage_ubicacion());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud == 0){
            restaurantes.clear();
            restaurantes.addAll(listResultados);
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Restaurante> colleccion = restaurantes.stream().filter(i -> i.getNombreRestaurante().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                restaurantes.clear();
                restaurantes.addAll(colleccion);
            }else {
                for (Restaurante r: listResultados) {
                    if (r.getNombreRestaurante().toLowerCase().contains(txtBuscar.toLowerCase()))
                        restaurantes.add(r);
                }
            }
        }
        notifyDataSetChanged();
    }
}
