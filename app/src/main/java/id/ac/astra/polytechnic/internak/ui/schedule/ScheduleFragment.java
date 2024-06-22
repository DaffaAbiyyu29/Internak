package id.ac.astra.polytechnic.internak.ui.schedule;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.databinding.FragmentScheduleBinding;
import id.ac.astra.polytechnic.internak.model.Schedule;
import id.ac.astra.polytechnic.internak.ui.cage.CreateCage;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;

public class ScheduleFragment extends Fragment {
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private List<Schedule> scheduleList;
    private FragmentScheduleBinding binding;
    private OnCreateScheduleClickListener listener;
    private ScheduleAdapter mScheduleAdapter;

    public interface OnCreateScheduleClickListener {
        void onCreateScheduleClicked();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ApiService apiService = ApiClient.getClient().create(ApiService.class); // Pastikan RetrofitClient dan konfigurasi Anda telah disiapkan
        mScheduleAdapter = new ScheduleAdapter(new ArrayList<>(), apiService);
        RecyclerView recyclerViewSchedules = root.findViewById(R.id.recycler_view);
        recyclerViewSchedules.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSchedules.setAdapter(mScheduleAdapter);

        setupObservers(scheduleViewModel);

        MainActivity.showBottomNavigationView();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_sch);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (listener != null) {
//                    listener.onCreateScheduleClicked();
//                }
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new CreateScheduleFragment());
                fragmentTransaction.addToBackStack("ScheduleFragment"); // Gunakan tag yang unik untuk CageFragment
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Menghubungkan antarmuka ke MainActivity saat fragment terhubung
        if (context instanceof ScheduleFragment.OnCreateScheduleClickListener) {
            listener = (ScheduleFragment.OnCreateScheduleClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ScheduleFragment.OnNotificationClickListener");
        }
    }

    private void setupObservers(ScheduleViewModel scheduleViewModel) {
        scheduleViewModel.getSchedules().observe(getViewLifecycleOwner(), new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                if (schedules != null) {
                    Log.d("ScheduleFragment", "Schedules updated: " + schedules.size());
                    mScheduleAdapter.updateData(schedules); // Ganti scheduleAdapter dengan mScheduleAdapter
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
