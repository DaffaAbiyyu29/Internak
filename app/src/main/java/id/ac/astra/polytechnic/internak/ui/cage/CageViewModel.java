package id.ac.astra.polytechnic.internak.ui.cage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.List;

import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.SingleObjectApiResponse;
import id.ac.astra.polytechnic.internak.model.Cage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CageViewModel extends ViewModel {
    private final MutableLiveData<List<Cage>> cages = new MutableLiveData<>();
    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> buttonAction;
    private static final String TAG = "CageViewModel";
    private final MutableLiveData<Boolean> createCageSuccess = new MutableLiveData<>();

    public LiveData<Boolean> getCreateCageSuccess() {
        return createCageSuccess;
    }

    public CageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Kandang");

        buttonAction = new MutableLiveData<>();
        fetchCages();  // Ensure cages are fetched when the ViewModel is created
    }

    public CageViewModel(Cage cage) {
        this();
        createCage(cage);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getButtonAction() {
        return buttonAction;
    }

    public LiveData<List<Cage>> getCages() {
        return cages;
    }

    public void onLeftButtonClick() {
        buttonAction.setValue("Aktif");
    }

    public void onRightButtonClick() {
        buttonAction.setValue("Rehat");
    }

    public void createCage(Cage cage) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<SingleObjectApiResponse<Cage>> call = apiService.createCage(cage);
        call.enqueue(new Callback<SingleObjectApiResponse<Cage>>() {
            @Override
            public void onResponse(Call<SingleObjectApiResponse<Cage>> call, Response<SingleObjectApiResponse<Cage>> response) {
                if (response.isSuccessful()) {
                    SingleObjectApiResponse<Cage> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getStatus() == 200) {
                        Log.d(TAG, "Success to create Cage. Response message: " + apiResponse.getMessage());
                        createCageSuccess.setValue(true);
                        fetchCages();  // Refresh the list after creating a new cage
                    } else {
                        String errorMessage = "Failed to create Cage. ";
                        if (apiResponse != null) {
                            errorMessage += "Response message: " + apiResponse.getMessage();
                        }
                        Log.e(TAG, errorMessage);
                        createCageSuccess.setValue(false);
                    }
                } else {
                    Log.e(TAG, "Response failed: " + response.message());
                    createCageSuccess.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<SingleObjectApiResponse<Cage>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                createCageSuccess.setValue(false);
            }
        });
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
