package id.ac.astra.polytechnic.internak.ui.cage;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.model.City;
import id.ac.astra.polytechnic.internak.model.Province;
import id.ac.astra.polytechnic.internak.model.Province;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvinceViewModel extends ViewModel {
    private final MutableLiveData<List<Province>> provinces;
    private final MutableLiveData<List<City>> cities;
    private static final String TAG = "ProvinceViewModel";

    public ProvinceViewModel() {
        provinces = new MutableLiveData<>();
        cities = new MutableLiveData<>();
        fetchProvinces();
    }

    public LiveData<List<Province>> getProvinces() {
        return provinces;
    }

    public LiveData<List<City>> getCityByProvinces() {
        return cities;
    }

    private void fetchProvinces() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse<Province>> call = apiService.getAllProvinces();
        call.enqueue(new Callback<ApiResponse<Province>>() {
            @Override
            public void onResponse(Call<ApiResponse<Province>> call, Response<ApiResponse<Province>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<Province> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getStatus() == 200) {
                        provinces.setValue(apiResponse.getData());
                    } else {
                        Log.e(TAG, "Failed to get provinces. Response message: " + response.message());
                    }
                } else {
                    Log.e(TAG, "Response failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Province>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }

    public void getCityByProvince(int prv_id) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse<City>> call = apiService.getCityByProvince(prv_id);
        call.enqueue(new Callback<ApiResponse<City>>() {
            @Override
            public void onResponse(Call<ApiResponse<City>> call, Response<ApiResponse<City>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<City> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getStatus() == 200) {
                        cities.setValue(apiResponse.getData());
                    } else {
                        Log.e(TAG, "Failed to get provinces. Response message: " + response.message());
                    }
                } else {
                    Log.e(TAG, "Response failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<City>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}
