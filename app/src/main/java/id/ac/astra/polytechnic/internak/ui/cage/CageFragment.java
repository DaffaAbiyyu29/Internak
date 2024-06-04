package id.ac.astra.polytechnic.internak.ui.cage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.ac.astra.polytechnic.internak.databinding.FragmentCageBinding;
import id.ac.astra.polytechnic.internak.ui.cage.CageViewModel;

public class CageFragment extends Fragment {
    private FragmentCageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CageViewModel cageViewModel =
                new ViewModelProvider(this).get(CageViewModel.class);

        binding = FragmentCageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCage;
        cageViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
