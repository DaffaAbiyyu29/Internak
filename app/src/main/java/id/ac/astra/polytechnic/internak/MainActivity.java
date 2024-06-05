package id.ac.astra.polytechnic.internak;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.astra.polytechnic.internak.databinding.ActivityMainBinding;
import id.ac.astra.polytechnic.internak.ui.cage.CageFragment;
import id.ac.astra.polytechnic.internak.ui.home.HomeFragment;
import id.ac.astra.polytechnic.internak.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {
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
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main, new HomeFragment())
                            .commit();
            } else if (item.getItemId() == R.id.nav_cage) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main, new CageFragment())
                            .commit();
            } else if (item.getItemId() == R.id.nav_profile) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main, new ProfileFragment())
                            .commit();
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
}
