package nir.droid.lift.Location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import butterknife.Bind;
import butterknife.ButterKnife;
import nir.droid.lift.Network.ApiClient;
import nir.droid.lift.R;
import nir.droid.lift.Util.Global;
import nir.droid.lift.Util.Import;
import nir.droid.lift.Util.LocationUtil;
import nir.droid.lift.Util.Log;
import nir.droid.lift.Util.NetworkUtil;

public class Location extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleApiClient.ConnectionCallbacks loc_listener;
    GoogleApiClient.OnConnectionFailedListener f_loc_listener;
    GoogleApiClient mGoogleApiClient;

    @Bind(R.id.latitude)
    TextView latitude;
    @Bind(R.id.longitude)
    TextView longitude;

    Context context;
    Activity activity;

    private static boolean mResolvingError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (savedInstanceState != null) {
            mResolvingError = savedInstanceState.getBoolean(Global.STATE_RESOLVING_ERROR);
            Log.d("lo", "boo " + mResolvingError);
        }

        ButterKnife.bind(this);

        loc_listener = this;
        f_loc_listener = this;

        context = this;
        activity = this;
        getApiClient();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (NetworkUtil.checkplayservice(this, this))
            getApiClient();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putBoolean(Global.STATE_RESOLVING_ERROR, mResolvingError);
    }

    private void getApiClient() {
        Log.d("lo", "get loc");
        mGoogleApiClient = ApiClient.getGoogleClient(this, loc_listener, f_loc_listener);
        mGoogleApiClient.connect();

    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.d("lo", "connected ");
        if (LocationUtil.checkPermission(this)) {
            Log.d("lo", "No permission granted");
            LocationUtil.handlePermission(this);
            return;
        }
        doProceed();
    }


    private void doProceed() {

        getLocation();
        final LocationRequest mLocationRequest = LocationUtil.createLocationRequest();
        PendingResult<LocationSettingsResult> result = LocationUtil.checkSettings(mLocationRequest, mGoogleApiClient);

        final LocationListener mListener = this;
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();
                final LocationSettingsStates states = locationSettingsResult.getLocationSettingsStates();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        startLocationUpdate(mLocationRequest);
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(
                                    Location.this, Global.REQUEST_LOCATION_ERROR);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                }
            }
        });
    }

    private void getLocation() {

        android.location.Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            Log.d("lo", "connected " + mLastLocation.toString());
            latitude.setText(String.valueOf(mLastLocation.getLatitude()));
            longitude.setText(String.valueOf(mLastLocation.getLongitude()));
        } else {
            Log.d("lo", "location null");
            latitude.setText("No location available");
        }
    }

    protected void startLocationUpdate(LocationRequest mLocationRequest){
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Global.PERMISSION_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doProceed();
                } else {
                    //TODO: Dhandle error;
                }
                break;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("lo", "Suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d("lo", "Failed " + connectionResult.toString());
        if (mResolvingError) {
            return;
        } else if (connectionResult.hasResolution()) {
            try {
                mResolvingError = true;
                connectionResult.startResolutionForResult(this, Global.RESOLVE_GOOGLE_API_ERROR);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        } else {
            Import.showErrorDialogue(this, connectionResult.getErrorCode());
            mResolvingError = true;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("loc", requestCode + " " + resultCode);

        if (requestCode == Global.RESOLVE_GOOGLE_API_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
        else if (requestCode == Global.REQUEST_LOCATION_ERROR) {
            if (resultCode == RESULT_OK) {
                Log.d("loc","result ok");
                getLocation();
                startLocationUpdate(LocationUtil.createLocationRequest());
            }
        }

    }

    public void onDialogDismissed() {
        mResolvingError = false;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        Log.d("loc","Loc changed "+location.toString());
    }
}
