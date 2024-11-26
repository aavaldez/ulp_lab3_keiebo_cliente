package com.a2valdez.keiebo.ui.reuniones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a2valdez.keiebo.R;
import com.a2valdez.keiebo.databinding.FragmentReunionBinding;
import com.a2valdez.keiebo.modelo.Participante;

import java.util.List;

public class ReunionFragment extends Fragment {
    ReunionViewModel mv;
    private FragmentReunionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(ReunionViewModel.class);
        binding = FragmentReunionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getmLista().observe(getViewLifecycleOwner(), new Observer<List<Participante>>() {
            @Override
            public void onChanged(List<Participante> listaActual) {
                RecyclerView rv = root.findViewById(R.id.rv_participantes);
                GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(glm);
                ParticipanteAdapter pad = new ParticipanteAdapter(listaActual, getContext(), getLayoutInflater());
                rv.setAdapter(pad);
            }
        });
        mv.obtenerParticipantes(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}