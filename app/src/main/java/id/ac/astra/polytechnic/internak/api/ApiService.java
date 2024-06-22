package id.ac.astra.polytechnic.internak.api;

import id.ac.astra.polytechnic.internak.model.Cage;
import id.ac.astra.polytechnic.internak.model.Censor;
import id.ac.astra.polytechnic.internak.model.City;
import id.ac.astra.polytechnic.internak.model.Notification;
import id.ac.astra.polytechnic.internak.model.Province;
import id.ac.astra.polytechnic.internak.model.Schedule;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/cage/getAll")
    Call<ApiResponse<Cage>> getAllCages();

    @GET("api/schedule/getAll")
    Call<ApiResponse<Schedule>> getAllSchedules();

    @GET("api/censor/getAll")
    Call<ApiResponse<Censor>> getAllCensor();

    @GET("provinces/getProvince")
    Call<ApiResponse<Province>> getAllProvinces();

    @GET("cities/getCity")
    Call<ApiResponse<City>> getAllCities();

    @GET("cities/getCity/{id}")
    Call<SingleObjectApiResponse<City>> getCityById(@Path("id") int id);

    @GET("api/ts-notification/getall")
    Call<ApiResponse<Notification>> getAllNotifications();

    @GET("cities/getCityByPrv/{id}")
    Call<ApiResponse<City>> getCityByProvince(@Path("id") int id);

    @POST("api/cage/create")
    Call<SingleObjectApiResponse<Cage>> createCage(@Body Cage msCage);
    @POST("api/censor/create")
    Call<SingleObjectApiResponse<Censor>> createCensor(@Body Censor msCensor); // Add this method
}