package id.ac.astra.polytechnic.internak.ui.notification;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentNotificationBinding;

import id.ac.astra.polytechnic.internak.model.Notification;

public class NotificationFragment extends Fragment {
    private OnNotificationBackClickListener listener;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;
    private MaterialCardView popupFilterNotification;
    private MaterialButton buttonFilterNotification;
    private GestureDetector gestureDetector;
    private FragmentNotificationBinding binding;
    private float initialY;


    public interface OnNotificationBackClickListener {
        void OnNotificationBackClickListener();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationViewModel notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.d(TAG, "onCreateView: setupRecyclerView akan dipanggil");
        setupRecyclerView();

        // Observe the notification data from NotificationViewModel
        notificationViewModel.getNotifications().observe(getViewLifecycleOwner(), new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                if (notifications != null && !notifications.isEmpty()) {
                    // Hide no_notification_card and show RecyclerView
                    binding.recyclerViewNotifications.setVisibility(View.VISIBLE);
                    binding.noNotificationCard.setVisibility(View.GONE);

                    // Update RecyclerView adapter with new notification data
                    notificationAdapter.updateData(notifications);
                } else {
                    // Hide RecyclerView and show no_notification_card
                    binding.recyclerViewNotifications.setVisibility(View.GONE);
                    binding.noNotificationCard.setVisibility(View.VISIBLE);
                }
            }
        });

        return root;
    }

    private void setupRecyclerView() {
        binding.recyclerViewNotifications.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter(new ArrayList<>());
        binding.recyclerViewNotifications.setAdapter(notificationAdapter);
    }


    @SuppressLint("ClickableViewAccessibility")
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

        // Set up the GestureDetector
        gestureDetector = new GestureDetector(getContext(), new SwipeGestureDetector());

        // Attach the OnTouchListener to the popup
        popupFilterNotification.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialY = event.getRawY();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float deltaY = event.getRawY() - initialY;
                    if (deltaY > 0) {
                        popupFilterNotification.setTranslationY(deltaY);
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    if (event.getRawY() - initialY > 300) { // Threshold for swipe down to close
                        closePopupWithAnimation();
                    } else {
                        resetPopupPosition();
                    }
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOnNotificationBackClickListener(mainActivity);
        }
    }

    public void setOnNotificationBackClickListener(OnNotificationBackClickListener listener) {
        this.listener = listener;
    }

    private void showPopupWithAnimation() {
        if (popupFilterNotification.getVisibility() == View.GONE) {
            popupFilterNotification.setVisibility(View.VISIBLE);
            ViewPropertyAnimator animator = popupFilterNotification.animate();
            animator.translationY(0).setDuration(300).start();
        } else {
            closePopupWithAnimation();
        }
    }

    private void closePopupWithAnimation() {
        ViewPropertyAnimator animator = popupFilterNotification.animate();
        animator.translationY(1000).setDuration(300).withEndAction(() -> popupFilterNotification.setVisibility(View.GONE)).start();
    }

    private void resetPopupPosition() {
        ViewPropertyAnimator animator = popupFilterNotification.animate();
        animator.translationY(0).setInterpolator(new DecelerateInterpolator()).setDuration(300).start();
    }

    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffY) > Math.abs(diffX)) {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        closePopupWithAnimation();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private void setupObservers(NotificationViewModel notificationViewModel) {
        notificationViewModel.getNotifications().observe(getViewLifecycleOwner(), new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                if (notifications != null) {
                    Log.d(TAG, "Notifications updated: " + notifications.size());
                    notificationAdapter.updateData(notifications);
                }
            }
        });
    }

//    private void setupRecyclerView() {
//        notificationAdapter = new NotificationAdapter(new ArrayList<>());
//        RecyclerView recyclerViewNotifications = binding.recyclerViewNotifications;
//        recyclerViewNotifications.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerViewNotifications.setAdapter(notificationAdapter);
//
//    }
}
