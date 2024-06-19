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
import id.ac.astra.polytechnic.internak.ui.cage.CreateCage;
import id.ac.astra.polytechnic.internak.ui.home.HomeFragment;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;
import id.ac.astra.polytechnic.internak.ui.profile.ProfileFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import id.ac.astra.polytechnic.internak.ui.schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnNotificationClickListener, NotificationFragment.OnNotificationBackClickListener, CageFragment.OnCreateCageClickListener, CreateCage.OnCreateCageBackClickListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            // Load the default fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main, new HomeFragment())
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
                } else if (item.getItemId() == R.id.nav_schedule) {
                    selectedFragment = new ScheduleFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
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

    private void backToDashboard() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new HomeFragment())
                .commit();
        showBottomNavigationView();
    }

    @Override
    public void onNotificationClicked() {
        // Panggil moveToNotificationFragment saat gambar diklik
        moveToNotificationFragment();
    }



    @Override
    public void OnNotificationBackClickListener() {
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
    public void onCreateCageClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CreateCage())
                .commit();
        showBottomNavigationView();
    }

    @Override
    public void OnCreateCageBackClickListener() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CageFragment())
                .commit();
        showBottomNavigationView();
    }

    private void moveToCreateCageFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CreateCage())
                .commit();
        hideBottomNavigationView();
    }


    @Override
    public void onCreateCageClicked() {
        // Panggil moveToNotificationFragment saat gambar diklik
        moveToCreateCageFragment();
    }

    private void hideBottomNavigationView() {
        binding.bottomNavigationView.setVisibility(View.GONE);
    }

    private void showBottomNavigationView() {
        binding.bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void fetchCages() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse<Cage>> call = apiService.getAllCages();
        call.enqueue(new Callback<ApiResponse<Cage>>() {
            @Override
            public void onResponse(Call<ApiResponse<Cage>> call, Response<ApiResponse<Cage>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Cage> cages = response.body().getData();
                    for (Cage cage : cages) {
                        Log.d("Cage", "Name: " + cage.getCagName() + ", Type: " + cage.getCtyId());
                    }
                } else {
                    Log.e("Cage", "Response failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Cage>> call, Throwable t) {
                Log.e("Cage", "Error: " + t.getMessage());
            }
        });
    }
}

