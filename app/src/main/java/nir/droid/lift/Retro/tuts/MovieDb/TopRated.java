package nir.droid.lift.Retro.tuts.MovieDb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import nir.droid.lift.R;
import nir.droid.lift.Retro.tuts.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRated extends AppCompatActivity {

    public final static String api_key = "d0d8f360bfabd952cbef1ea187a7997a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);
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

        getApi();
    }

    private void getApi() {

        MovieInterface movie_api = ApiClient.getClient().create(MovieInterface.class);
        Log.d("ta","starting");
        Call<MovieResponse> api_response = movie_api.getTopRated(api_key);
        Log.d("ta","starting 2 "+api_response.request().url());
        api_response.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d("tr","url "+call.request().url());
                List<MovieResponse> body = response.body().getResults();
                if (body == null) {
                    Log.d("tr","NULLLLLLLLLLLLL");
                    return;
                }

                RecyclerView list = (RecyclerView) findViewById(R.id.movie_list);
                list.setHasFixedSize(true);
                list.setAdapter(new ListAdapter(TopRated.this,body));
                list.setLayoutManager(new LinearLayoutManager(TopRated.this));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("tr","failed "+t.toString());
            }

        });
    }

}
