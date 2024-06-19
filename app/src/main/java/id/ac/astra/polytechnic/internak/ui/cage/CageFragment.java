package id.ac.astra.polytechnic.internak.ui.cage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentCageBinding;
import id.ac.astra.polytechnic.internak.model.Cage;

public class CageFragment extends Fragment {

    private CageViewModel cageViewModel;
    private FragmentCageBinding binding;
    private OnCreateCageClickListener listener;
    private OnDetailCageClickListener mlistener;
    private RecyclerView recyclerView;
    private CageAdapterC cageAdapter;
    private MaterialCardView cardRehat;
    private List<Cage> cageList = new ArrayList<>();

    private static final String TAG = "CageFragment";

    public interface OnCreateCageClickListener {
        void onCreateCageClicked();
        void onNotificationClicked();
    }

    public interface OnDetailCageClickListener{
        void onDetailCageClicked();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cageViewModel = new ViewModelProvider(this).get(CageViewModel.class);

        TextView textView = binding.textCage;
        TextView totalKandangTextView = binding.totalKandang;
        Button leftButton = binding.leftButton;
        Button rightButton = binding.rightButton;
        cardRehat = binding.cardRehat;

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
                recyclerView.setVisibility(View.VISIBLE);
                cardRehat.setVisibility(View.GONE);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cageViewModel.onRightButtonClick();
                leftButton.setBackgroundColor(inactiveBackgroundColor);
                rightButton.setBackgroundColor(activeBackgroundColor);
                totalKandangTextView.setText("Terdapat 1 kandang");
                recyclerView.setVisibility(View.GONE);
                cardRehat.setVisibility(View.VISIBLE);
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

        // Initialize RecyclerView and set adapter
        Log.d(TAG, "Initializing RecyclerView and setting adapter.");
        recyclerView = binding.recyclerViewCages1;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cageAdapter = new CageAdapterC(new ArrayList<>());
        recyclerView.setAdapter(cageAdapter);
        Log.d(TAG, "RecyclerView initialized with adapter.");
        setupObservers(cageViewModel);

        return root;
    }

    private void setupObservers(CageViewModel cageViewModel) {
        cageViewModel.getCages().observe(getViewLifecycleOwner(), new Observer<List<Cage>>() {
            @Override
            public void onChanged(List<Cage> cages) {
                if (cages != null) {
                    Log.d(TAG, "Cages updated: " + cages.size());
                    cageAdapter.updateData(cages);
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCreateCageClickListener) {
            listener = (OnCreateCageClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnCreateCageClickListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExtendedFloatingActionButton fab = view.findViewById(R.id.fab);
        ImageView imageView = view.findViewById(R.id.buttonNotification);
        Button button = view.findViewById(R.id.Btn_detail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCreateCageClicked();
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNotificationClicked();
                }
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mlistener != null) {
//                    mlistener.onDetailCageClicked();
//                }
//            }
//        });
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
            setOnCreateCageClickListener(mainActivity);
//            setOnDetailCageClickListener(mainActivity);
        }
    }

    public void setOnCreateCageClickListener(OnCreateCageClickListener listener) {
        this.listener = listener;
    }

    public void setOnDetailCageClickListener(OnDetailCageClickListener mlistener) {
        this.mlistener = mlistener;
    }
}
