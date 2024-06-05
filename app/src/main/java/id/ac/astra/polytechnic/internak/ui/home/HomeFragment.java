package id.ac.astra.polytechnic.internak.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private OnNotificationClickListener listener;

    // Antarmuka untuk memberi tahu MainActivity saat gambar diklik
    public interface OnNotificationClickListener {
        void onNotificationClicked();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Menghubungkan antarmuka ke MainActivity saat fragment terhubung
        if (context instanceof OnNotificationClickListener) {
            listener = (OnNotificationClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement HomeFragment.OnNotificationClickListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Panggil moveToNotificationFragment saat gambar diklik
        ImageView imageView = view.findViewById(R.id.buttonNotification);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNotificationClicked();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}