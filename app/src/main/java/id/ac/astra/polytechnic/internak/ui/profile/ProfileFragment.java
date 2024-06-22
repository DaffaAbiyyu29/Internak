package id.ac.astra.polytechnic.internak.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.card.MaterialCardView;

import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.databinding.FragmentProfileBinding;
import id.ac.astra.polytechnic.internak.ui.notification.NotificationFragment;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MainActivity.showBottomNavigationView();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Panggil moveToNotificationFragment saat gambar diklik
        ImageView imageView = view.findViewById(R.id.buttonNotification);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onNotificationClicked();
//                }
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new NotificationFragment());
                fragmentTransaction.addToBackStack("ProfileFragment"); // Gunakan tag yang unik untuk CageFragment
                fragmentTransaction.commit();
            }
        });

        LinearLayout ubahprofil = view.findViewById(R.id.ubahprofil);
        ubahprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new UbahProfilFragment());
                fragmentTransaction.addToBackStack("ProfileFragment");
                fragmentTransaction.commit();
            }
        });

        LinearLayout ubahPassword = view.findViewById(R.id.ubahPassword);
        ubahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new UbahSandiFragment());
                fragmentTransaction.addToBackStack("ProfileFragment");
                fragmentTransaction.commit();
            }
        });

        LinearLayout kebijakanPrivasi = view.findViewById(R.id.kebijakanPrivasi);
        kebijakanPrivasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new KebijakanPrivasiFragment());
                fragmentTransaction.addToBackStack("ProfileFragment");
                fragmentTransaction.commit();
            }
        });

        LinearLayout layananAplikasi = view.findViewById(R.id.layananAplikasi);
        layananAplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new LayananAplikasiFragment());
                fragmentTransaction.addToBackStack("ProfileFragment");
                fragmentTransaction.commit();
            }
        });

        MaterialCardView cardview_keluar = view.findViewById(R.id.cardview_keluar);
        cardview_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, new LogoutFragment());
                fragmentTransaction.addToBackStack("ProfileFragment");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
