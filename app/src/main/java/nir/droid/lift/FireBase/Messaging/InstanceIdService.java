package nir.droid.lift.FireBase.Messaging;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import nir.droid.lift.Util.Import;
import nir.droid.lift.Util.Log;

/**
 * Created by droidcafe on 10/14/2016.
 */
public class InstanceIdService extends FirebaseInstanceIdService {

    private static final String FIREBASE_TOKEN = "FireBaseToken";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("iis", "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
        Import.setSharedPref(getBaseContext(),FIREBASE_TOKEN,refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        //TODO send token to server using retrofit

    }


}
