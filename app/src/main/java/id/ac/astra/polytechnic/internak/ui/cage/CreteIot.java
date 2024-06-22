package id.ac.astra.polytechnic.internak.ui.cage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentIotBinding;
import id.ac.astra.polytechnic.internak.model.Censor;
import id.ac.astra.polytechnic.internak.ui.login.Login1Fragment;

public class CreteIot extends Fragment {

    private FragmentIotBinding binding;
    private OnCreateIoTBackClickListener listener;

    public interface OnCreateIoTBackClickListener {
        void OnCreateIoTBackClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreateIotViewModel censorViewModel = new ViewModelProvider(this).get(CreateIotViewModel.class);

        binding = FragmentIotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.nama; // Ensure the ID matches the one in your XML layout
        textView.setText("Nama Perangkat");

        TextView kodetextView = binding.kode; // Ensure the ID matches the one in your XML layout
        kodetextView.setText("Kode Perangkat");

        Button saveButton = root.findViewById(R.id.button_TambahIoT); // Ensure the ID matches the one in your XML layout
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nama_perangkat = root.findViewById(R.id.nama_iot);
                EditText kode_perangkat = root.findViewById(R.id.kode_perangkat);

                Censor censor = new Censor();
                censor.setCns_name(nama_perangkat.getText().toString());
                censor.setCns_description(kode_perangkat.getText().toString());
                censor.setCns_status(1);

                censorViewModel.createCensor(censor);
            }
        });

        censorViewModel.getCreateCensorSuccess().observe(getViewLifecycleOwner(), success -> {
            if (success) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new DetailCage());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.button_back_detail);
        imageView.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main, new DetailCage());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnCreateIoTBackClickListener(mainActivity);
        }
    }

    public void setOnCreateIoTBackClickListener(OnCreateIoTBackClickListener listener) {
        this.listener = listener;
    }
}
