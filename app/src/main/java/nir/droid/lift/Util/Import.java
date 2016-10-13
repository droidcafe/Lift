package nir.droid.lift.Util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


/**
 * Created by droidcafe on 10/12/2016.
 */
public class Import {

    public static void toast(Context context, String text) {
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

    public static void showErrorDialogue( Activity activity ,int errorCode) {
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        ErrorDialogFragment dialogFragment = ErrorDialogFragment.getInstance(activity);
        Bundle args = new Bundle();
        args.putInt(Global.DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);

        dialogFragment.show(fragmentActivity.getSupportFragmentManager(), "errordialog");
    }
}
