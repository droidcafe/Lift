package nir.droid.lift.Retro.tuts.MovieDb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by droidcafe on 10/9/2016.
 */
public interface MovieInterface {

    @GET("/3/movie/top_rated")
    Call<MovieResponse> getTopRated(@Query("api_key") String api_Key);

    @GET("/3/tv/top_rated")
    Call<MovieResponse> getTopTV(@Query("api_key") String api_Key);


}
