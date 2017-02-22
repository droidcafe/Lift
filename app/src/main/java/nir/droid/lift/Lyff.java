package nir.droid.lift;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by droidcafe on 10/13/2016.
 */
public class Lyff extends Application {

    private static Context sContext = null;
    @Override
    public void onCreate() {
        super.onCreate();


        sContext = getApplicationContext();
    }

    public static Context getContext()
    {
        return sContext;
    }
}
