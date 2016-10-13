package nir.droid.lift.Util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.google.android.gms.common.GoogleApiAvailability;

import nir.droid.lift.Location.Location;

/**
 * Created by droidcafe on 10/13/2016.
 */

public class ErrorDialogFragment extends DialogFragment {

    static Activity activity;
    public ErrorDialogFragment() {

    }

    public static ErrorDialogFragment getInstance(Activity activity) {
        ErrorDialogFragment dialogFragment;
        dialogFragment = new ErrorDialogFragment();

        Bundle bundle = new Bundle();
//        bundle.putParcelable("activity", (Parcelable) activity);

        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        Activity activity = savedInstanceState.getParcelable("activity");
        int errorCode = this.getArguments().getInt(Global.DIALOG_ERROR);
        return GoogleApiAvailability.getInstance().getErrorDialog(
                getActivity(), errorCode, Global.RESOLVE_GOOGLE_API_ERROR);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        ((Location) getActivity()).onDialogDismissed();
    }
}

