package id.ac.astra.polytechnic.internak.ui.schedule;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;

public class CreateScheduleFragment extends Fragment {
    private OnScheduleBackClickListener listener;

    private CreateScheduleViewModel mViewModel;

    public interface OnScheduleBackClickListener {
        void OnScheduleBackClickListener();
    }

    public static CreateScheduleFragment newInstance() {
        return new CreateScheduleFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.button_back_schedule);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnScheduleBackClickListener();
                }
            }
        });
    }

    public void setOnScheduleBackClickListener(OnScheduleBackClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_schedule, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnScheduleBackClickListener(mainActivity);
//            setOnNotificationBackClickListenerCage(mainActivity);
        }
    }
}