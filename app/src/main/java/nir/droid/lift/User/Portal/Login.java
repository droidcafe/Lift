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

public class Login extends AppCompatActivity {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signup)
    void login() {

        String mail = username.getText().toString();
        String pass = password.getText().toString();

        if (!(mail.equals("") || pass.equals(""))) {
            ApiClient.getClient().create(RestApiInterface.class).loginUser(mail, pass).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    UserResponse userResponse = response.body();
                    Import.toast(context, "" + userResponse.getResponse());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("rg", "Registration failed " + t.toString());
                    Import.toast(context, "Login exception ! Please try again "+ t.toString());

                }
            });
        }
    }


}
