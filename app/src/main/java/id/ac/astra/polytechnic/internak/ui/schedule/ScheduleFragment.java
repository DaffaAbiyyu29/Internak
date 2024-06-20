package id.ac.astra.polytechnic.internak.ui.schedule;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentProfileBinding;
import id.ac.astra.polytechnic.internak.databinding.FragmentScheduleBinding;
import id.ac.astra.polytechnic.internak.model.Schedule;
import id.ac.astra.polytechnic.internak.ui.cage.CageFragment;
import id.ac.astra.polytechnic.internak.ui.home.HomeFragment;

public class ScheduleFragment extends Fragment {
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private List<Schedule> scheduleList;
    private FragmentScheduleBinding binding;
    private OnCreateScheduleClickListener listener;

    public interface OnCreateScheduleClickListener {
        void onCreateScheduleClicked();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Gunakan getContext() di sini

        scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule("11.30 AM", "12.30 PM", "Auto Feeder", "Makan Konserat + Minum"));
        scheduleList.add(new Schedule("01.00 PM", "02.00 PM", "Auto Watering", "Minum Air + Vitamin"));
        scheduleList.add(new Schedule("01.00 PM", "02.00 PM", "Auto Watering", "Minum Air + Vitamin"));

        scheduleAdapter = new ScheduleAdapter(scheduleList);
        recyclerView.setAdapter(scheduleAdapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_sch);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCreateScheduleClicked();
                }
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
                    + " must implement HomeFragment.OnNotificationClickListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
