package id.ac.astra.polytechnic.internak.ui.notification;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.model.Notification;

public class NotificationFragment extends Fragment {
    private OnNotificationBackClickListener listener;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;
    private MaterialCardView popupFilterNotification;
    private MaterialButton buttonFilterNotification;

    public interface OnNotificationBackClickListener {
        void OnNotificationBackClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize your notification list here
        notificationList = new ArrayList<>();
//        notificationList.add(new Notification("Waspada, Temperature Tinggi!", "Terjadi kenaikan suhu pada kandang...", false));
//        notificationList.add(new Notification("Peringatan Hujan Deras", "Hujan deras diperkirakan akan terjadi...", true));

        notificationAdapter = new NotificationAdapter(notificationList);
        recyclerView.setAdapter(notificationAdapter);


        recyclerView2 = view.findViewById(R.id.recycler_view_notifications);
        MaterialCardView noNotificationCard = view.findViewById(R.id.no_notification_card);

        if (notificationList != null && !notificationList.isEmpty()) {
            recyclerView.setVisibility(View.VISIBLE);
            noNotificationCard.setVisibility(View.GONE);
            // Set adapter untuk RecyclerView dan tampilkan notifikasi
            NotificationAdapter adapter = new NotificationAdapter(notificationList);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setVisibility(View.GONE);
            noNotificationCard.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Panggil moveToNotificationFragment saat gambar diklik
        ImageView imageView = view.findViewById(R.id.button_back_notification);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnNotificationBackClickListener();
                }
            }
        });

        popupFilterNotification = view.findViewById(R.id.popup_filter_notification);
        buttonFilterNotification = view.findViewById(R.id.button_filter_notification);

        // Set the initial position of the popup to be outside of the screen
        popupFilterNotification.setTranslationY(1000);  // adjust this value as needed
        popupFilterNotification.setVisibility(View.GONE);

        buttonFilterNotification.setOnClickListener(v -> showPopupWithAnimation());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnNotificationBackClickListener(mainActivity);
        }
    }

    private void showPopupWithAnimation() {
        if (popupFilterNotification.getVisibility() == View.GONE) {
            popupFilterNotification.setVisibility(View.VISIBLE);
            ViewPropertyAnimator animator = popupFilterNotification.animate();
            animator.translationY(0).setDuration(300).start();
        } else {
            ViewPropertyAnimator animator = popupFilterNotification.animate();
            animator.translationY(1000).setDuration(300).withEndAction(() -> popupFilterNotification.setVisibility(View.GONE)).start();
        }
    }

    public void setOnNotificationBackClickListener(OnNotificationBackClickListener listener) {
        this.listener = listener;
    }
}
