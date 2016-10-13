package nir.droid.lift.Retro.tuts;

import nir.droid.lift.Retro.tuts.MovieDb.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by droidcafe on 10/6/2016.
 */
public interface InterfaceApi {

    @GET("/comments/{id}")
    Call<Example1> getComments(@Path("id") int id);

//    public void getComment(Callback<Example1> response);

    @GET("/notes/")
    Call<Example1> getNotes();
}
