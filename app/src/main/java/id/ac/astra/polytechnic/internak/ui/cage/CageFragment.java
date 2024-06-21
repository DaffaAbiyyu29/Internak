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
import java.util.stream.Collectors;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.databinding.FragmentCageBinding;
import id.ac.astra.polytechnic.internak.model.Cage;

public class CageFragment extends Fragment implements CageAdapterC.OnDetailButtonClickListener {

    private CageViewModel cageViewModel;
    private FragmentCageBinding binding;
    private OnCreateCageClickListener listener;
    private OnDetailCageClickListener mlistener;
    private RecyclerView recyclerView;
    private CageAdapterC cageAdapter;
    private MaterialCardView cardRehat;
    private List<Cage> cageList = new ArrayList<>();

    private static final String TAG = "CageFragment";

    @Override
    public void onDetailButtonClick(Cage cage) {
        // Handle the detail button click here
        Log.d(TAG, "Detail button clicked for cage: " + cage.getCagName());
        // Call a method in the activity or navigate to a new fragment/ activity
        if (mlistener != null) {
            mlistener.onDetailCageClicked();
        }
    }
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
                filterAndDisplayCagesByStatus(1);
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
                filterAndDisplayCagesByStatus(0);
                recyclerView.setVisibility(View.VISIBLE);
                cardRehat.setVisibility(View.GONE);
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
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        cageAdapter = new CageAdapterC(new ArrayList<>(), apiService,this);
        recyclerView.setAdapter(cageAdapter);
        Log.d(TAG, "RecyclerView initialized with adapter.");
        setupObservers();
        filterAndDisplayCagesByStatus(1);

        return root;
    }

    private void setupObservers() {
        // Observe cageList LiveData
        cageViewModel.getCages().observe(getViewLifecycleOwner(), new Observer<List<Cage>>() {
            @Override
            public void onChanged(List<Cage> cages) {
                Log.d(TAG, "Received updated cage list.");
                cageList = cages;
                // Initially display cages with status 1
                filterAndDisplayCagesByStatus(1);
            }
        });
    }

    private void filterAndDisplayCagesByStatus(int status) {
        List<Cage> filteredCages = cageList.stream()
                .filter(cage -> cage.getCagStatus() == status)
                .collect(Collectors.toList());
        cageAdapter.updateData(filteredCages);
        updateTotalKandangText(filteredCages.size());
    }

    private void updateTotalKandangText(int totalCages) {
        TextView totalKandangTextView = binding.totalKandang;
        totalKandangTextView.setText("Terdapat " + totalCages + " kandang");
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
        Button button = view.findViewById(R.id.button_rehat);
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener != null) {
                    mlistener.onDetailCageClicked();
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
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnCreateCageClickListener(mainActivity);
            setOnDetailCageClickListener(mainActivity);
        }
    }

    public void setOnCreateCageClickListener(OnCreateCageClickListener listener) {
        this.listener = listener;
    }

    public void setOnDetailCageClickListener(OnDetailCageClickListener mlistener) {
        this.mlistener = mlistener;
    }
}
