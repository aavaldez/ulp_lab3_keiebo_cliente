package com.a2valdez.keiebo.ui.reuniones;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.a2valdez.ulp_lab3_inmobiliaria_cliente.R;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.modelo.Participante;
import com.a2valdez.ulp_lab3_inmobiliaria_cliente.request.ApiClientRetrofit;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ViewHolder> {
    private List<Participante> Participantes;
    private Context contexto;
    private LayoutInflater li;

    public ReunionAdapter(List<Participante> Participantes, Context contexto, LayoutInflater li) {
        this.Participantes = Participantes;
        this.contexto = contexto;
        this.li = li;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_contrato, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.direccion.setText(Participantes.get(position).getDireccion());
        holder.id.setText(String.valueOf(Participantes.get(position).getId()));
        String imagen = Participantes.get(position).getImagen().replace("\\","/");
        String url = ApiClientRetrofit.URLBASE +imagen;
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
        private EditText id;
        private ImageView imagen;
        private Button ver;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvItemContratoDireccion);
            id = itemView.findViewById(R.id.etItemContratoParticipanteId);
            ver = itemView.findViewById(R.id.btItemContratoVer);
            imagen = itemView.findViewById(R.id.ivItemContrato);
            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.contratoFragment, bundle);
                }
            });
        }
    }
}
