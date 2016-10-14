package nir.droid.lift.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by droidcafe on 10/12/2016.
 */
public class Import {

    public static void toast(Context context, String text) {
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

    public static void settypefaces(Context context,String typefaceName, TextView textview) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefaceName);
        textview.setTypeface(typeface);

    }

    public static boolean isGoodCursor(Cursor cursor) {
        if (cursor.moveToNext())
            return true;
        else
            return false;
    }

    public static String[] getStringArray(Context context, int array) {
        return context.getResources().getStringArray(array);
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }


    public static void showErrorDialogue( Activity activity ,int errorCode) {
        FragmentActivity fragmentActivity = (FragmentActivity) activity;
        ErrorDialogFragment dialogFragment = ErrorDialogFragment.getInstance(activity);
        Bundle args = new Bundle();
        args.putInt(Global.DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);

        dialogFragment.show(fragmentActivity.getSupportFragmentManager(), "errordialog");
    }

    public static int getSharedPref(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.SHARED_PREF, 0);
        return sharedPreferences.getInt(key, -1);
    }

    public static float getFloatPref(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.SHARED_PREF, 0);
        return sharedPreferences.getFloat(key, -1);
    }

    public static String getSharedPref(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.SHARED_PREF, 0);
        return sharedPreferences.getString(key, null);
    }

    public static void setSharedPref(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.SHARED_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(key, value);
        editor.apply();
    }

    public static void setSharedPref(Context context, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.SHARED_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat(key, value);
        editor.apply();
    }

    public static void setSharedPref(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.SHARED_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);
        editor.apply();
    }


    public static void setStatusBarColor(Context context, Activity activity, int color) {
        Window window = activity.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(context.getResources().getColor(color));

        }
    }


    /**
     * helper function to get resource id of a resource
     * @param context
     * @param resourceId the id of resource
     * @param prefix - prefix like drawable, string,array - where is resource is
     * @return the id
     */
    public static int getResource(Context context, String resourceId,String prefix) {

        String uri = prefix +"/" + resourceId;
        return context.getResources().getIdentifier(uri, null, context.getPackageName());
    }


}
