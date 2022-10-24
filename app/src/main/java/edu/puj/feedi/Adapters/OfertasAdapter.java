package edu.puj.feedi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.puj.feedi.R;
import edu.puj.feedi.activities.RestauranteActivity;
import edu.puj.feedi.model.Oferta;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder>{
    static ArrayList<Oferta> ofertas;

    public OfertasAdapter(ArrayList<Oferta> ofertas){
        this.ofertas = ofertas;
    }

    @NonNull
    @Override

    public OfertasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_oferta, null,false);
        return new OfertasAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertasAdapter.MyViewHolder holder, int position) {
        holder.textViewNombreProducto.setText(ofertas.get(position).getNombre());
        holder.textViewPrecio.setText(ofertas.get(position).getPrecio());
        holder.textViewCantidad.setText(ofertas.get(position).getCantidad());
        holder.imageView.setImageResource(ofertas.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewNombreProducto, textViewPrecio, textViewCantidad;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img_restaurante);
            textViewNombreProducto = (TextView) itemView.findViewById(R.id.nombre_producto);
            textViewPrecio = (TextView) itemView.findViewById(R.id.precio);
            textViewCantidad = (TextView) itemView.findViewById(R.id.cantidadProducto);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RestauranteActivity.class);
                    intent.putExtra("nombre",restaurantes.get(getAdapterPosition()).getNombreRestaurante());
                    intent.putExtra("ofertas",restaurantes.get(getAdapterPosition()).getOfertas());
                    intent.putExtra("image",restaurantes.get(getAdapterPosition()).getImage());
                    context.startActivity(intent);
                }
            });*/
        }
    }
}
