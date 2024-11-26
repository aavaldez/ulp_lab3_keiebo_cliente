package com.a2valdez.keiebo.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.a2valdez.keiebo.databinding.FragmentPerfilBinding;
import com.a2valdez.keiebo.modelo.Participante;
import com.a2valdez.keiebo.request.ApiClientRetrofit;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel mv;
    Participante participanteActual = null;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = binding.getRoot();
        mv.getMPropietario().observe(getViewLifecycleOwner(), new Observer<Participante>() {
            @Override
            public void onChanged(Participante participante) {
                participanteActual = participante;
                binding.etPerfilId.setText(String.valueOf(participante.getId()));
                binding.etPerfilDni.setText(String.valueOf(participante.getDni()));
                binding.etPerfilApellido.setText(participante.getApellido());
                binding.etPerfilNombre.setText(participante.getNombre());
                binding.etPerfilEmail.setText(participante.getEmail());
                binding.etPerfilPassword.setText(participante.getPassword());
                binding.etPerfilTelefono.setText(participante.getTelefono());

                String imagen = participante.getAvatar().replace("\\","/");
                String url = ApiClientRetrofit.URLBASE+imagen;
                Glide.with(getActivity())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivPerfilAvatar);
            }
        });
        mv.getMEsEditable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean esEditable) {
                binding.etPerfilDni.setEnabled(esEditable);
                binding.etPerfilApellido.setEnabled(esEditable);
                binding.etPerfilNombre.setEnabled(esEditable);
                binding.etPerfilTelefono.setEnabled(esEditable);
                //binding.etPerfilEmail.setEnabled(esEditable);
                //binding.etPerfilPassword.setEnabled(esEditable);

                binding.btPerfilEditar.setVisibility(esEditable ? View.GONE : View.VISIBLE);
                binding.btPerfilGuardar.setVisibility(esEditable ? View.VISIBLE : View.GONE);
            }
        });
        binding.btPerfilGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crear Participante
                Participante p = new Participante(Integer.parseInt(binding.etPerfilId.getText().toString()),
                        Long.parseLong(binding.etPerfilDni.getText().toString()),
                        binding.etPerfilNombre.getText().toString(),
                        binding.etPerfilApellido.getText().toString(),
                        binding.etPerfilEmail.getText().toString(),
                        binding.etPerfilPassword.getText().toString(),
                        binding.etPerfilTelefono.getText().toString(),
                        participanteActual.getAvatar());
                mv.GuardarParticipante(p);
            }
        });
        binding.btPerfilEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.CambiarEstadoEdicion();
            }
        });
        mv.LeerUsuario();
        return root;
    }
}