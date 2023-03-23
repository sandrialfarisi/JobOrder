package id.sandri.joborder.ApiHelper;

import id.sandri.joborder.Model.Register;
import id.sandri.joborder.Model.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BaseApiService {
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("signin")
    Call<User> getUser(@Header("Authorization") String token, @Body RequestBody body);

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("signup")
    Call<Register> registerRequest(@Body RequestBody body);

    @GET("1")
    Call<String> getDataPrint1(@Header("x-access-token") String token);
    @GET("2")
    Call<String> getDataPrint2(@Header("x-access-token") String token);
    @GET("3")
    Call<String> getDataPrint3(@Header("x-access-token") String token);

    @GET("1")
    Call<String> getDataDry1(@Header("x-access-token") String token);
    @GET("2")
    Call<String> getDataDry2(@Header("x-access-token") String token);
    @GET("3")
    Call<String> getDataDry3(@Header("x-access-token") String token);

    @GET("1")
    Call<String> getDataSliting1(@Header("x-access-token") String token);
    @GET("2")
    Call<String> getDataSliting2(@Header("x-access-token") String token);
    @GET("3")
    Call<String> getDataSliting3(@Header("x-access-token") String token);
    @GET("4")
    Call<String> getDataSliting4(@Header("x-access-token") String token);
    @GET("5")
    Call<String> getDataSliting5(@Header("x-access-token") String token);

    @GET("1")
    Call<String> getDataBag1(@Header("x-access-token") String token);
    @GET("2")
    Call<String> getDataBag2(@Header("x-access-token") String token);

    @GET("1")
    Call<String> getDataExtru1(@Header("x-access-token") String token);
}
