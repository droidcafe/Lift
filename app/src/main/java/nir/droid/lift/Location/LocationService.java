package nir.droid.lift.Location;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.location.LocationResult;

import nir.droid.lift.Network.APIResponse.LocationResponse;
import nir.droid.lift.Network.ApiClient;
import nir.droid.lift.Network.RestApiInterface;
import nir.droid.lift.Util.Import;
import nir.droid.lift.Util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationService extends IntentService {

    private static final String ACTION_LOCATION = "nir.droid.lift.Location.action.TRACK";
    private static final String PREV_LONGITUDE = "prev_longitude";
    private static final String PREV_LATITUDE = "prev_latitude";


    public LocationService() {
        super("LocationService");
    }


    public static void startService(Context context) {
        Intent intent = new Intent(context, LocationService.class);
        intent.setAction(ACTION_LOCATION);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        Log.d("ls", "starting service");
        if (intent != null) {
            getLocation(intent);
        } else {
            Log.d("ls", "intent is null");
        }
    }

    private void getLocation(Intent intent) {
//        Log.d("ls", "getting location");
        if (LocationResult.hasResult(intent)) {
            LocationResult result = LocationResult.extractResult(intent);
            android.location.Location location = result.getLastLocation();
            if (location == null) return;

//            Log.d("ls", "new location " + location.toString());
            Context context = getApplication().getBaseContext();

            float prev_longitude =  Import.getFloatPref(context, PREV_LONGITUDE);
            float prev_latitude =  Import.getFloatPref(context, PREV_LATITUDE);

            float longitude = (float) location.getLongitude();
            float latitude = (float) location.getLatitude();

            Log.d("ls", "" + prev_latitude + " " + latitude + " " + prev_longitude + " " + longitude);

            if ((prev_latitude == latitude) && (prev_longitude == longitude)) {
                Log.d("ls","same position");
                return;
            }
            Import.setSharedPref(context, PREV_LONGITUDE, longitude);
            Import.setSharedPref(context, PREV_LATITUDE, latitude);

            syncServer(Double.toString(latitude), Double.toString(longitude));
        }

//        android.location.Location location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);

    }

    private void syncServer(String latitude, String longitude) {
        RestApiInterface apiInterface = ApiClient.getClient().create(RestApiInterface.class);
        Call<LocationResponse> response = apiInterface.updateLocation(23, latitude, longitude);

        response.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                Log.d("ls", "updated");
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Log.d("ls", "failed " + t.toString());
            }
        });
    }
}
