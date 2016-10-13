package nir.droid.lift.Network.APIResponse;

/**
 * Created by droidcafe on 10/12/2016.
 */
public class UserResponse {

    private String email ;
    private String password ;
    private String response;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
