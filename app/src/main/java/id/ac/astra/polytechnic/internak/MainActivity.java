package id.ac.astra.polytechnic.internak;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.databinding.ActivityMainBinding;
import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.ui.cage.CageFragment;
import id.ac.astra.polytechnic.internak.ui.cage.CreateCage;
import id.ac.astra.polytechnic.internak.ui.cage.CreteIot;
import id.ac.astra.polytechnic.internak.ui.cage.DetailCage;
import id.ac.astra.polytechnic.internak.ui.home.HomeFragment;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;
import id.ac.astra.polytechnic.internak.ui.profile.ProfileFragment;
import id.ac.astra.polytechnic.internak.ui.schedule.CreateScheduleFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import id.ac.astra.polytechnic.internak.ui.schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnNotificationClickListener, NotificationFragment.OnNotificationBackClickListener, CageFragment.OnCreateCageClickListener,
        CreateCage.OnCreateCageBackClickListener, CageFragment.OnDetailCageClickListener, DetailCage.OndetailBackClickListener, ScheduleFragment.OnCreateScheduleClickListener, CreateScheduleFragment.OnScheduleBackClickListener,
        DetailCage.OnCreateIoTClickListener, CreteIot.OnCreateIoTBackClickListener{
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

        fetchCages();
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

//    @Override
//    public void OnNotificationBackClickListenerCage() {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main, new CageFragment())
//                .commit();
//        showBottomNavigationView();
//    }

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

    private void moveToDetailCageFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new DetailCage())
                .commit();
        hideBottomNavigationView();
    }


    @Override
    public void onCreateCageClicked() {
        // Panggil moveToNotificationFragment saat gambar diklik
        moveToCreateCageFragment();
    }

    @Override
    public void onDetailCageClicked() {
        moveToDetailCageFragment();
    }

    @Override
    public void OndetailBackClickListener() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CageFragment())
                .commit();
        showBottomNavigationView();
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

    @Override
    public void onCreateScheduleClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CreateScheduleFragment())
                .commit();
        showBottomNavigationView();
    }

    @Override
    public void OnScheduleBackClickListener() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new ScheduleFragment())
                .commit();
        showBottomNavigationView();
    }

    @Override
    public void OnCreateIoTClickListener() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new CreteIot())
                .commit();
        hideBottomNavigationView();
    }

    @Override
    public void OnCreateIoTBackClickListener() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new DetailCage())
                .commit();
        hideBottomNavigationView();
    }
}

