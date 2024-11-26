package com.a2valdez.keiebo.ui.reuniones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a2valdez.keiebo.R;
import com.a2valdez.keiebo.databinding.FragmentReunionesBinding;
import com.a2valdez.keiebo.modelo.Reunion;

import java.util.List;

public class ReunionesFragment extends Fragment {

    ReunionesViewModel mv;
    private FragmentReunionesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(ReunionesViewModel.class);
        binding = FragmentReunionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.fabCrearReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                //navController.navigate(R.id.action_nav_reuniones_to_nuevaReunionFragment);
            }
        });
        mv.getmLista().observe(getViewLifecycleOwner(), new Observer<List<Reunion>>() {
            @Override
            public void onChanged(List<Reunion> listaActual) {
                RecyclerView rv = root.findViewById(R.id.rv_reuniones);
                GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(glm);
                ReunionAdapter rad = new ReunionAdapter(listaActual, getContext(), getLayoutInflater());
                rv.setAdapter(rad);
            }
        });
        mv.obtenerReuniones();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}