package id.ac.astra.polytechnic.internak.api;

import java.util.List;

import id.ac.astra.polytechnic.internak.api.TabUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
public interface UserService {
    @GET("user")
    Call<TabUser> getUserById(@Query("usr_id") Integer usr_id);
    @GET("getAllUsersVo")
    Call<List<TabUser>> getUsers();
    @POST("saveUser")
    Call<TabUser> addUser(@Body TabUser user);
    @PUT("updateUser")
    Call<TabUser> updateUser(@Body TabUser user);
    @DELETE("deleteUser")
    Call<TabUser> deleteUserById(@Query("usr_id") Integer usr_id);
    @GET("/users/getUserByEmailAndPassword")
    Call<TabUser> getUserByEmailAndPassword(@Query("usr_email") String email, @Query("usr_password") String password);
}
