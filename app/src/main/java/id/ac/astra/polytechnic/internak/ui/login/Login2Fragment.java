package id.ac.astra.polytechnic.internak.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;

import id.ac.astra.polytechnic.internak.R;
import id.ac.astra.polytechnic.internak.api.ApiUtils;
import id.ac.astra.polytechnic.internak.api.TabUser;
import id.ac.astra.polytechnic.internak.api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login2Fragment extends Fragment {

    private EditText editEmailAddress;
    private EditText editPassword;
    private UserService userService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login2, container, false);

        userService = ApiUtils.getUserService();

        editEmailAddress = view.findViewById(R.id.editEmailAddress);
        editPassword = view.findViewById(R.id.editPassword);

        Button loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            String email = editEmailAddress.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            System.out.println("email "+email);
            System.out.println("pw "+password);

            if (!email.isEmpty() && !password.isEmpty()) {
                loginUser(email, password);
            } else {
                Toast.makeText(getContext(), "Email dan kata sandi harus diisi", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loginUser(String email, String password) {
        Call<TabUser> call = userService.getUserByEmailAndPassword(email, password);

        call.enqueue(new Callback<TabUser>() {
            @Override
            public void onResponse(Call<TabUser> call, Response<TabUser> response) {
                Object user = response.body();
                if (response.body().getStatus()!=404 && response.body() != null) {
                    // Login successful, navigate to success fragment
                    navigateToSuccessFragment();
                } else {
                    // Login failed, show error message
                    String errorMessage = "Login failed";
                    if (response.body().getStatus() == 404) {
                        errorMessage = response.body().getMessage();
                    } else if (response.errorBody() != null) {
                        try {
                            errorMessage += ": " + response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    System.out.println("Response Code: " + response.code());
                    System.out.println("Response Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TabUser> call, Throwable t) {
                // Network error, show error message
                String errorMessage = "Network error";
                if (t.getMessage() != null) {
                    errorMessage += ": " + t.getMessage();
                }
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                t.printStackTrace(); // Print stack trace for debugging
            }
        });
    }

    private void navigateToSuccessFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, new LoginSuccessFragment());
        fragmentTransaction.addToBackStack(null); // Optional: add to back stack
        fragmentTransaction.commit();
    }
}