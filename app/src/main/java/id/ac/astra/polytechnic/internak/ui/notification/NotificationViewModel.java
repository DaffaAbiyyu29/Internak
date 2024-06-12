package id.ac.astra.polytechnic.internak.ui.notification;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.astra.polytechnic.internak.api.ApiClient;
import id.ac.astra.polytechnic.internak.api.ApiService;
import id.ac.astra.polytechnic.internak.api.ApiResponse;
import id.ac.astra.polytechnic.internak.model.Notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationViewModel extends ViewModel {
    private final MutableLiveData<List<Notification>> notifications;
    private static final String TAG = "NotificationViewModel";

    public NotificationViewModel() {
        notifications = new MutableLiveData<>();
        fetchNotifications();
    }

    public LiveData<List<Notification>> getNotifications() {
        return notifications;
    }

    private void fetchNotifications() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse<Notification>> call = apiService.getAllNotifications();
        call.enqueue(new Callback<ApiResponse<Notification>>() {
            @Override
            public void onResponse(Call<ApiResponse<Notification>> call, Response<ApiResponse<Notification>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    notifications.setValue(response.body().getData());
                } else {
                    Log.e(TAG, "Response failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Notification>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}
