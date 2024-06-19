package id.ac.astra.polytechnic.internak.ui.cage;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

import com.google.android.material.card.MaterialCardView;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentCageDetailBinding;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;

public class DetailCage extends Fragment {
    private OndetailBackClickListener listener;
    private FragmentCageDetailBinding binding;
    private MaterialCardView popupadd;
    private ImageView mImageView;
    private GestureDetector gestureDetector;
    private float initialY;

    public interface OndetailBackClickListener {
        void OndetailBackClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCageDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.button_back_cage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OndetailBackClickListener();
                }
            }
        });

        popupadd = view.findViewById(R.id.popup_filter);
        mImageView = view.findViewById(R.id.add_perangkat);

        popupadd.setTranslationY(1000);  // adjust this value as needed
        popupadd.setVisibility(View.GONE);

        mImageView.setOnClickListener(v -> showPopupWithAnimation());

        gestureDetector = new GestureDetector(getContext(), new SwipeGestureDetector());

        // Attach the OnTouchListener to the popup
        popupadd.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialY = event.getRawY();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float deltaY = event.getRawY() - initialY;
                    if (deltaY > 0) {
                        popupadd.setTranslationY(deltaY);
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

    private void showPopupWithAnimation() {
        if (popupadd.getVisibility() == View.GONE) {
            popupadd.setVisibility(View.VISIBLE);
            ViewPropertyAnimator animator = popupadd.animate();
            animator.translationY(0).setDuration(300).start();
        } else {
            closePopupWithAnimation();
        }
    }

    private void closePopupWithAnimation() {
        ViewPropertyAnimator animator = popupadd.animate();
        animator.translationY(1000).setDuration(300).withEndAction(() -> popupadd.setVisibility(View.GONE)).start();
    }

    private void resetPopupPosition() {
        ViewPropertyAnimator animator = popupadd.animate();
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            setOndetailBackClickListener(mainActivity);
//            setOnNotificationBackClickListenerCage(mainActivity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setOndetailBackClickListener(OndetailBackClickListener listener) {
        this.listener = listener;
    }

}
