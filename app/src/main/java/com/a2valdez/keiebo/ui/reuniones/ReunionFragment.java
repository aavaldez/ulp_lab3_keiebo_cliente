package com.a2valdez.keiebo.ui.reuniones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.a2valdez.keiebo.databinding.FragmentReunionesBinding;

public class ReunionesFragment extends Fragment {

    private FragmentReunionesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReunionViewModel reunionViewModel =
                new ViewModelProvider(this).get(ReunionViewModel.class);

        binding = FragmentReunionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.etReason;
        reunionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}