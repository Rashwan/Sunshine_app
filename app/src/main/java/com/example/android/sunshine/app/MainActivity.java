package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
        Log.d(LOG_TAG,"create");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent ;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_showOnMap:
                showLocationOnMap();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showLocationOnMap() {
        Intent intent;SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userLocation = prefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        Uri geoLocation = Uri.parse("geo:0,0?")
                .buildUpon().appendQueryParameter("q",userLocation).build();
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + userLocation + ", no receiving apps installed!");
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "destroy");
    }
}

