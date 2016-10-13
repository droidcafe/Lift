package nir.droid.lift.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by droidcafe on 10/12/2016.
 */
public class NetworkUtil {

    public static boolean checkInternetConenction(Context context) {
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            Import.toast(context, "No Connection ! Try after sometime");
            return false;
        }
        return false;
    }

    public static boolean checkplayservice(Context context, Activity activity) {

        GoogleApiAvailability mGoogleApiAvailability = GoogleApiAvailability.getInstance();
        int result = mGoogleApiAvailability.isGooglePlayServicesAvailable(context);

        if(result!= ConnectionResult.SUCCESS) {
            if (mGoogleApiAvailability.isUserResolvableError(result)) {
                Import.showErrorDialogue(activity,result);
            } else {
                Import.toast(context, "This device isn't supported");
                activity.finish();
            }
            return false;
        }

        return true;

    }
}
