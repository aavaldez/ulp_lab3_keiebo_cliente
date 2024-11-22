package com.a2valdez.keiebo.ui.reuniones;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.a2valdez.keiebo.R;
import com.a2valdez.keiebo.modelo.Participante;
import com.a2valdez.keiebo.request.ApiClientRetrofit;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolder> {
    private List<Participante> Participantes;
    private Context contexto;
    private LayoutInflater li;

    public ParticipanteAdapter(List<Participante> Participantes, Context contexto, LayoutInflater li) {
        this.Participantes = Participantes;
        this.contexto = contexto;
        this.li = li;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_Participante, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.direccion.setText(Participantes.get(position).getDireccion());
        holder.precio.setText(String.valueOf(Participantes.get(position).getPrecio()));
        holder.id.setText(String.valueOf(Participantes.get(position).getId()));
        String imagen = Participantes.get(position).getImagen().replace("\\","/");
        String url = ApiClientRetrofit.URLBASE+imagen;
        Glide.with(contexto)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
    }
    @Override
    public int getItemCount() {
        return Participantes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView direccion;
        private TextView precio;
        private EditText id;
        private ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvItemParticipanteDireccion);
            precio = itemView.findViewById(R.id.tvItemParticipantePrecio);
            id = itemView.findViewById(R.id.etItemParticipanteId);
            imagen = itemView.findViewById(R.id.ivItemParticipante);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.ParticipanteFragment, bundle);
                }
            });
        }
    }
}
