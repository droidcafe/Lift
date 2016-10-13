package nir.droid.lift.Retro.tuts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by droidcafe on 10/6/2016.
 */
public class Example1 {

    @SerializedName(value = "body" ,alternate="notes")
    public String body;

    @SerializedName("email")
    public String mail;



    public String getResult(){

        return  body+" "+mail;
    }


}
