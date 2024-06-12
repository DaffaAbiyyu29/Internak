package id.ac.astra.polytechnic.internak.api;

import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.model.City;
import id.ac.astra.polytechnic.internak.model.Notification;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/cage/getAll")
    Call<ApiResponse<Cage>> getAllCages();

    @GET("cities/getCity/{id}")
    Call<ApiResponse<City>> getCityById(@Path("id") int id);

    @GET("api/ts-notification/getall")
    Call<ApiResponse<Notification>> getAllNotifications();
}
