package com.tracker.psikosos.presto;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity {

    private GoogleMap map;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        LatLng lokasi = new LatLng(-6.8934,107.6130);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 15));
        map.addMarker(new MarkerOptions().title("Lokasi").snippet("Lokasi bermain").position(lokasi));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button challenge2 = (Button) findViewById(R.id.button_2);
        challenge2.setEnabled(ChallengeActivity.completed == true);
        Button challenge3 = (Button) findViewById(R.id.button_3);
        challenge3.setEnabled(Challenge2Activity.completed == true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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

    public void goToChallengeActivity(View view) {
        startActivity(new Intent(this,ChallengeActivity.class));
    }

    public void goToChallengeActivity2(View view) {
        startActivity(new Intent(this, Challenge2Activity.class));
    }

    public void goToChallengeActivity3(View view) {
        startActivity(new Intent(this,Challenge3Activity.class));
    }
}
