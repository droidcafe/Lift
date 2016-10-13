package nir.droid.lift.Retro.tuts;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by droidcafe on 10/9/2016.
 */
public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org";
    public static final String BASE_URL_LOCAL = "http://192.168.0.103:3000";
    private static final String HOST_URL = "http://192.168.0.103";
    private static final String NODE_PORT = "3000";
//    public static final String BASE_URL_LOCAL = HOST_URL + ":" + NODE_PORT;
    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500_and_h281_bestv2";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_LOCAL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
