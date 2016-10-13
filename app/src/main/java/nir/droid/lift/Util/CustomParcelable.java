package nir.droid.lift.Util;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by droidcafe on 10/13/2016.
 */
public class CustomParcelable implements Parcelable {


    Activity activity;
    protected CustomParcelable(Parcel in) {
    }

    public static final Creator<CustomParcelable> CREATOR = new Creator<CustomParcelable>() {
        @Override
        public CustomParcelable createFromParcel(Parcel in) {
            return new CustomParcelable(in);
        }

        @Override
        public CustomParcelable[] newArray(int size) {
            return new CustomParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
