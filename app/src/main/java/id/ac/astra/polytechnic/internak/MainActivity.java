package id.ac.astra.polytechnic.internak;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.astra.polytechnic.internak.databinding.ActivityMainBinding;
import id.ac.astra.polytechnic.internak.ui.cage.CageFragment;
import id.ac.astra.polytechnic.internak.ui.home.HomeFragment;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;
import id.ac.astra.polytechnic.internak.ui.profile.ProfileFragment;
import id.ac.astra.polytechnic.internak.ui.schedule.ScheduleFragment;
import id.ac.astra.polytechnic.internak.ui.login.SplashScreenFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnNotificationClickListener{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            hideBottomNavigationView();
            // Load the SplashScreenFragment as the default fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main, new SplashScreenFragment())
                    .commit();
        }

        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.nav_cage) {
                    selectedFragment = new CageFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                } else if (item.getItemId() == R.id.nav_schedule) {
                    selectedFragment = new ScheduleFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main, selectedFragment)
                            .commit();
                }

                return true;
            }
        });
    }

    private void moveToNotificationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new NotificationFragment())
                .commit();
        hideBottomNavigationView();
    }

    private void moveToUbahProfilFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new NotificationFragment())
                .commit();
        hideBottomNavigationView();
    }

    private void backToDashboard() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new HomeFragment())
                .commit();
        showBottomNavigationView();
    }

    private void hideBottomNavigationView() {
        binding.bottomNavigationView.setVisibility(View.GONE);
    }

    private void showBottomNavigationView() {
        binding.bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNotificationClicked() {

    }
}
