package nir.droid.lift.Location.Map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nir.droid.lift.Network.ApiClient;
import nir.droid.lift.R;
import nir.droid.lift.Util.Global;
import nir.droid.lift.Util.LocationUtil;
import nir.droid.lift.Util.Log;

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final String TAG = "map";
    GoogleMap map;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;


    @Bind(R.id.place)
    TextView placeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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

        ButterKnife.bind(this);
        setMaps();

    }

    @OnClick(R.id.search)
    public void search() {
        openAutoComopleteFragment();
    }

    private void openAutoComopleteFragment() {

        try {

            LatLngBounds bounds = new LatLngBounds(
                    new LatLng(8.1746847, 77.4440221),
                    new LatLng(8.6753485, 77.0852258));

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setBoundsBias(bounds)
                    .build(this);

            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }


    private void setMaps() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        mGoogleApiClient = ApiClient.getGoogleClient(this, this, this);
        mGoogleApiClient.connect();

        if (LocationUtil.checkPermission(this)) {
            Log.d(TAG, "No permission granted");
            LocationUtil.handlePermission(this);
            return;
        }
        customiseMap();
    }

    private void customiseMap() {
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        map.setTrafficEnabled(false);

    }

    private void setLatLng(LatLng location, String title) {
        map.addMarker(new MarkerOptions().position(location).title(title));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)
                .zoom(17)
                .bearing(0)
                .tilt(30)
                .build();


        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 4000, null);
        map.setOnInfoWindowClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                LatLng latLng = place.getLatLng();

                placeText.setText(place.getName() + " " + place.getAddress());

                setLatLng(latLng, "" + place.getName());
                Log.i(TAG, "Place: " + place.getName() + " " + place.getLatLng().toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Global.PERMISSION_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    customiseMap();
                }
                break;
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d(TAG, " marker clicked");
    }

    @Override
    public void onConnected(Bundle bundle) {
        mCurrentLocation = LocationUtil.getCurrentLocation(mGoogleApiClient);
        if (mCurrentLocation != null) {
            setLatLng(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()), "Current Location");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
