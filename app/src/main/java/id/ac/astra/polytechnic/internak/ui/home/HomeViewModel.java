package id.ac.astra.polytechnic.internak.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.model.Cage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<List<Cage>> cages;
    private static final String TAG = "HomeViewModel";

    public HomeViewModel() {
        cages = new MutableLiveData<>();
        fetchCages();
    }

    public LiveData<List<Cage>> getCages() {
        return cages;
    }

    private void fetchCages() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse<Cage>> call = apiService.getAllCages();
        call.enqueue(new Callback<ApiResponse<Cage>>() {
            @Override
            public void onResponse(Call<ApiResponse<Cage>> call, Response<ApiResponse<Cage>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cages.setValue(response.body().getData());
                } else {
                    Log.e(TAG, "Response failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Cage>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}
