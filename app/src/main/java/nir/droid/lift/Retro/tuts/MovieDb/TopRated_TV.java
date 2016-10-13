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

public class TopRated_TV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated__tv);
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
        MovieInterface movieInterface = ApiClient.getClient().create(MovieInterface.class);
        Call<MovieResponse> call = movieInterface.getTopTV(TopRated.api_key);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d("tr", "url " + call.request().url());
                List<MovieResponse> body = response.body().getResults();
                if (body == null) {
                    Log.d("tr","NULLLLLLLLLLLLL");
                    return;
                }

                RecyclerView list = (RecyclerView) findViewById(R.id.movie_list);
                list.setHasFixedSize(true);
                list.setAdapter(new ListAdapter(TopRated_TV.this,body));
                list.setLayoutManager(new LinearLayoutManager(TopRated_TV.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

}
