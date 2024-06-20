package id.ac.astra.polytechnic.internak.ui.schedule;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.model.Schedule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleViewModel extends ViewModel {
    private final MutableLiveData<List<Schedule>> schedules;
    private static final String TAG = "ScheduleViewModel";

    public ScheduleViewModel() {
        schedules = new MutableLiveData<>();
        fetchSchedules();
    }

    public LiveData<List<Schedule>> getSchedules() {
        return schedules;
    }

    private void fetchSchedules() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse<Schedule>> call = apiService.getAllSchedules();
        call.enqueue(new Callback<ApiResponse<Schedule>>() {
            @Override
            public void onResponse(Call<ApiResponse<Schedule>> call, Response<ApiResponse<Schedule>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    schedules.setValue(response.body().getData());
                } else {
                    Log.e(TAG, "Response failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Schedule>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}
