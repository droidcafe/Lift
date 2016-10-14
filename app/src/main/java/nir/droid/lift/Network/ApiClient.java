package nir.droid.lift.Network;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import nir.droid.lift.Util.Global;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by droidcafe on 10/12/2016.
 */
public class ApiClient {

    public final static String KEY_LOCATION_CHANGED = "location_changed";

    public static Retrofit retrofit = null;
    private static GoogleApiClient mGoogleApiClient = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Global.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GoogleApiClient getGoogleClient(Context context, GoogleApiClient.ConnectionCallbacks listener,
                                                  GoogleApiClient.OnConnectionFailedListener f_listener){
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(listener)
                    .addOnConnectionFailedListener(f_listener)
                    .build();
        }

        return mGoogleApiClient;
    }


}
