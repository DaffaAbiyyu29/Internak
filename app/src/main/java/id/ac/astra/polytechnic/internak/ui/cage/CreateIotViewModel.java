package id.ac.astra.polytechnic.internak.ui.cage;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.SingleObjectApiResponse;
import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.model.Censor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateIotViewModel extends ViewModel {
    private final MutableLiveData<List<Censor>> censor;
    private static final String TAG = "CreateIotViewModel";
    private final MutableLiveData<Boolean> createCensorSuccess = new MutableLiveData<>(); // LiveData for success status

    public CreateIotViewModel() {
        censor = new MutableLiveData<>();
        fetchCensors();
    }

    public LiveData<List<Censor>> getCensor() {
        return censor;
    }

    public LiveData<Boolean> getCreateCensorSuccess() {
        return createCensorSuccess;
    }

    private void fetchCensors() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse<Censor>> call = apiService.getAllCensor();
        call.enqueue(new Callback<ApiResponse<Censor>>() {
            @Override
            public void onResponse(Call<ApiResponse<Censor>> call, Response<ApiResponse<Censor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    censor.setValue(response.body().getData());
                } else {
                    Log.e(TAG, "Response failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Censor>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }

    public void createCensor(Censor censor) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<SingleObjectApiResponse<Censor>> call = apiService.createCensor(censor);
        call.enqueue(new Callback<SingleObjectApiResponse<Censor>>() {
            @Override
            public void onResponse(Call<SingleObjectApiResponse<Censor>> call, Response<SingleObjectApiResponse<Censor>> response) {
                if (response.isSuccessful()) {
                    SingleObjectApiResponse<Censor> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getStatus() == 200) {
                        Log.d(TAG, "Success to create Cage. Response message: " + apiResponse.getMessage());
                        createCensorSuccess.setValue(true);
                        fetchCensors();  // Refresh the list after creating a new cage
                    } else {
                        String errorMessage = "Failed to create Cage. ";
                        if (apiResponse != null) {
                            errorMessage += "Response message: " + apiResponse.getMessage();
                        }
                        Log.e(TAG, errorMessage);
                        createCensorSuccess.setValue(false);
                    }
                } else {
                    String errorMessage = "Response failed: " + response.message();
                    Log.e(TAG, errorMessage);
                    createCensorSuccess.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<SingleObjectApiResponse<Censor>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                createCensorSuccess.setValue(false);
            }
        });
    }
}
