package nir.droid.lift.Network.APIResponse;

/**
 * Created by droidcafe on 10/13/2016.
 */
public class LocationResponse {

    private int user_id;
    private String longitude;
    private String latitue;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitue() {
        return latitue;
    }

    public void setLatitue(String latitue) {
        this.latitue = latitue;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
