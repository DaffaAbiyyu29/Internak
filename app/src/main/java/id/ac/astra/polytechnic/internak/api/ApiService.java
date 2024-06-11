package id.ac.astra.polytechnic.internak.api;

import id.ac.astra.polytechnic.internak.model.Cage;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/cage/getAll")
    Call<ApiResponse<Cage>> getAllCages();
}
