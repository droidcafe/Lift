package nir.droid.lift;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import nir.droid.lift.Location.Location;
import nir.droid.lift.Retro.tuts.MovieDb.TopRated;
import nir.droid.lift.Retro.tuts.MovieDb.TopRated_TV;
import nir.droid.lift.Retro.tuts.RetroActivity1;
import nir.droid.lift.User.Portal.Login;
import nir.droid.lift.User.Portal.Register;

public class Home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.first).setOnClickListener(this);
        findViewById(R.id.second).setOnClickListener(this);
        findViewById(R.id.third).setOnClickListener(this);
        findViewById(R.id.fourth).setOnClickListener(this);
        findViewById(R.id.fifth).setOnClickListener(this);
        findViewById(R.id.sixth).setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first:
                startActivity(new Intent(this, RetroActivity1.class));
                break;
            case R.id.second:
                startActivity(new Intent(this, TopRated.class));
                break;

            case R.id.third:
                startActivity(new Intent(this, TopRated_TV.class));
                break;

            case R.id.fourth:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.fifth:
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.sixth:
                startActivity(new Intent(this, Location.class));
                break;
        }
    }
}
