package nir.droid.lift.Retro.tuts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import nir.droid.lift.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RetroActivity1 extends AppCompatActivity implements View.OnClickListener {


    //Root URL of our web service
    public static final String ROOT_URL = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getApi(1);
        findViewById(R.id.button).setOnClickListener(this);
    }

    private void getApi(int id) {

        InterfaceApi api = ApiClient.getClient().create(InterfaceApi.class);
        Call<Example1> api_return;

//        if(id != 0) {
//        api_return = api.getComments(id);
//        }else
            api_return = api.getNotes();

        api_return.enqueue(new Callback<Example1>() {
            @Override
            public void onResponse(Call<Example1> call, Response<Example1> response) {

                ((TextView)findViewById(R.id.email)).setText(response.body().mail);
                ((TextView)findViewById(R.id.body)).setText(response.body().body);
                Log.d("ra"," "+response.body().getResult());
            }

            @Override
            public void onFailure(Call<Example1> call, Throwable t) {
                ((TextView)findViewById(R.id.email)).setText("Failed "+t.toString());
            }
        });



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                int id = Integer.parseInt(((EditText) findViewById(R.id.count)).getText().toString());
                getApi(id);
                break;
        }
    }
}
