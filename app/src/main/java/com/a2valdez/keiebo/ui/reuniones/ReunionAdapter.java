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

import com.a2valdez.keiebo.R;
import com.a2valdez.keiebo.modelo.Reunion;

import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ViewHolder> {
    private List<Reunion> reuniones;
    private Context contexto;
    private LayoutInflater li;

    public ReunionAdapter(List<Reunion> reuniones, Context contexto, LayoutInflater li) {
        this.reuniones = reuniones;
        this.contexto = contexto;
        this.li = li;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_reunion, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.direccion.setText(reuniones.get(position).getDesde());
        holder.id.setText(String.valueOf(reuniones.get(position).getId()));
        /*
        String imagen = reuniones.get(position).getImagen().replace("\\","/");
        String url = ApiClientRetrofit.URLBASE +imagen;
        Glide.with(contexto)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
         */
    }
    @Override
    public int getItemCount() {
        return reuniones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView direccion;
        private EditText id;
        private ImageView imagen;
        private Button ver;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvItemReunionDireccion);
            //id = itemView.findViewById(R.id.etItemReunionId);
            ver = itemView.findViewById(R.id.btnItemReunionVer);
            //imagen = itemView.findViewById(R.id.ivItemReunionImagen);
            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.nav_reunion_detalle, bundle);
                }
            });
        }
    }
}
