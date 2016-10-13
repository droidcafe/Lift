package nir.droid.lift.User.Portal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nir.droid.lift.Network.RestApiInterface;
import nir.droid.lift.Network.APIResponse.UserResponse;
import nir.droid.lift.Network.ApiClient;
import nir.droid.lift.R;
import nir.droid.lift.Util.Import;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    @Bind(R.id.mailid)
    EditText mail;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.username)
    EditText username;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;
        ButterKnife.bind(this);

    }

    @OnClick(R.id.signup)
    void register(){
        Log.d("rg","Register");
        String email = mail.getText().toString();
        String pass = password.getText().toString();
        String name = username.getText().toString();

        if  (!(email.equals("") || pass.equals("") || name.equals(""))){
            RestApiInterface restApiInterface = ApiClient.getClient().create(RestApiInterface.class);
            Call<UserResponse> response = restApiInterface.registerUser(name,email,pass);

            response.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    UserResponse userResponse = response.body();
                    Import.toast(context, ""+userResponse.getResponse());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("rg", "Registration failed "+t.toString());
                    Import.toast(context, "Registration failed! Please try again");
                }
            });
        }

    }

}
