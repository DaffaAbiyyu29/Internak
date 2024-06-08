package id.ac.astra.polytechnic.internak.ui.cage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentCreateCageBinding;
import id.ac.astra.polytechnic.internak.databinding.FragmentHomeBinding;
import id.ac.astra.polytechnic.internak.ui.home.HomeFragment;

public class CageFragment extends Fragment {

    private CageViewModel cageViewModel;
    private FragmentCreateCageBinding binding;
    private OnCreateCageClickListener listener;

    public interface OnCreateCageClickListener {
        void onCreateCageClicked();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cage, container, false);

        cageViewModel = new ViewModelProvider(this).get(CageViewModel.class);

        TextView textView = root.findViewById(R.id.text_cage);
        TextView totalKandangTextView = root.findViewById(R.id.total_kandang);
        Button leftButton = root.findViewById(R.id.leftButton);
        Button rightButton = root.findViewById(R.id.rightButton);
        ExtendedFloatingActionButton fab = root.findViewById(R.id.fab);

        int activeBackgroundColor = ContextCompat.getColor(requireContext(), R.color.grey);
        int inactiveBackgroundColor = ContextCompat.getColor(requireContext(), R.color.white);

        cageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String text) {
                textView.setText(text);
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cageViewModel.onLeftButtonClick();
                leftButton.setBackgroundColor(activeBackgroundColor);
                rightButton.setBackgroundColor(inactiveBackgroundColor);
                totalKandangTextView.setText("Terdapat 2 kandang");
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cageViewModel.onRightButtonClick();
                leftButton.setBackgroundColor(inactiveBackgroundColor);
                rightButton.setBackgroundColor(activeBackgroundColor);
                totalKandangTextView.setText("Terdapat 1 kandang");

            }
        });
        cageViewModel.getButtonAction().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String action) {
                if (action != null) {
                    Toast.makeText(getActivity(), "Button clicked: " + action, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Menghubungkan antarmuka ke MainActivity saat fragment terhubung
        if (context instanceof OnCreateCageClickListener) {
            listener = (OnCreateCageClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement HomeFragment.OnNotificationClickListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Panggil moveToNotificationFragment saat gambar diklik
        ExtendedFloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCreateCageClicked();
                }
            }
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
        if(getActivity() instanceof MainActivity){
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnCreateCageClickListener(mainActivity);
        }
    }


    public void setOnCreateCageClickListener(OnCreateCageClickListener listener){
        this.listener = listener;
    }
}
