package id.ac.astra.polytechnic.internak.ui.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentSplashScreenBinding;

public class SplashScreenFragment extends Fragment {
    private FragmentSplashScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.circleLayout.setOnClickListener(v -> {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(binding.circleLayout, "scaleX", 1f, 20f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(binding.circleLayout, "scaleY", 1f, 20f);
            scaleX.setDuration(500);
            scaleY.setDuration(500);
            scaleX.setInterpolator(new AccelerateInterpolator());
            scaleY.setInterpolator(new AccelerateInterpolator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(scaleX, scaleY);

            animatorSet.start();

            animatorSet.addListener(new android.animation.Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(android.animation.Animator animation) {
                }

                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main, new OnBoardingFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                @Override
                public void onAnimationCancel(android.animation.Animator animation) {
                }

                @Override
                public void onAnimationRepeat(android.animation.Animator animation) {
                }
            });
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
