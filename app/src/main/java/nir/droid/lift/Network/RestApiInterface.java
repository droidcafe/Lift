package nir.droid.lift.Network;

import nir.droid.lift.Network.APIResponse.LocationResponse;
import nir.droid.lift.Network.APIResponse.UserResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by droidcafe on 10/12/2016.
 */
public interface RestApiInterface {

    @FormUrlEncoded
    @POST("/location")
    Call<LocationResponse>  updateLocation(
            @Field("user_id") int user_id,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude );

    @FormUrlEncoded
    @POST("/register")
    Call<UserResponse> registerUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password")String password );

    @FormUrlEncoded
    @POST("/login")
    Call<UserResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    /**
     * THIS IS HOW TO CONVERT FILE/IMAGE TO REQUESTBODY TYPE
     *
     * MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
     file = new File("/storage/emulated/0/Pictures/MyApp/test.png");
     RequestBody requestBody = RequestBody.create(MEDIA_TYPE_PNG, file);

     Call<Response> call = apiService.uploadImage("test", requestBody);
     */

    @Multipart
    @POST("some/endpoint")
    Call<Response> uploadImage(@Part("description") String description, @Part("image") RequestBody image);




}
