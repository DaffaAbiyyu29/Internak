package id.ac.astra.polytechnic.internak.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.ac.astra.polytechnic.internak.HomeActivity;
import id.ac.astra.polytechnic.internak.MainActivity;
import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.ui.home.HomeFragment;

public class Login3Fragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login3, container, false);

        MainActivity.hideBottomNavigationView();

        Button nextButton = view.findViewById(R.id.registerButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        ImageButton backButton = view.findViewById(R.id.arrow);
        backButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main, new Login1Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

//        ImageButton backButton = view.findViewById(R.id.arrow);
//        backButton.setOnClickListener(v -> {
//            FragmentManager fragmentManager = getParentFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.main, new Login1Fragment());
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        });

        return view;
    }
}