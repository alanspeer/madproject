package com.example.whitelegg_n.osmdroid1;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import java.util.ArrayList;

import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends Activity
{
    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);
        mv = (MapView) findViewById(R.id.map1);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(50.89898324108728, -1.3979222900151895));

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            public boolean onItemLongPress(int i, OverlayItem item) {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item) {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
        OverlayItem hoglands = new OverlayItem("Hoglands Park", "Hoglands Park", new GeoPoint(50.90383461446549, -1.3994135979044617));
        items.addItem(hoglands);
        mv.getOverlays().add(items);
    }



    // onCreateOptionsMenu()
    // Runs automatically on startup, loads the menu XML file into a Menu object.
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    // onOptionsItemSelected()
    // Responds to menu items.
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // If the menu item was the one with the ID of "choosemap"...
        if (item.getItemId() == R.id.choosemap)
        {
            // Create an Intent to launch the MapChooseActivity
            Intent intent = new Intent (this, MapChooseActivity.class);

            // Start the Activity using the Intent.
            // Use a request code of 0 so that onActivityResult() knows which secondary
            // activity is sending the data back - in a full app, there would be more than
            // one secondary activity and each would be launched with its own request code.
            startActivityForResult(intent, 0);
            return true;
        }
        if (item.getItemId() == R.id.setlocation)
        {
            Intent intent = new Intent (this, SetLocation.class);

            startActivityForResult(intent, 1);
            return true;
        }
        if (item.getItemId() == R.id.choosemapstyle)
        {
            Intent intent = new Intent (this, MapChooseListActivity.class);

            startActivityForResult(intent , 0);
        }
        return false;
    }

    // onActivityResult()
    // Runs when we get a response back from the secondary activity.
    // Parameters:
    // - requestCode: the request code used to launch the secondary activity (see above)
    // - responseCode: the response code sent back from the secondary activity (e.g. RESULT_OK)
    // - intent: the Intent sent back from the secondary activity

    public void onActivityResult(int requestCode, int responseCode,Intent intent)
    {
        // If the secondary activity returned a result of RESULT_OK...
        if(responseCode == RESULT_OK)
        {
            // If the request code used to launch the secondary activity was 0, it means we launched
            // MapChooseActivity (see above).
            if(requestCode == 0)
            {
                // Get the Bundle from the Intent.
                Bundle bundle = intent.getExtras();

                // Get the cyclemap entry from the Bundle
                boolean cyclemap = bundle.getBoolean("com.example.cyclemap");

                // Set the map to either the cycle map, or the default map (MAPNIK) depending
                // on the value of the boolean retrieved from the Bundle.
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
            else if (requestCode == 1)
            {
                // Get the Bundle from the Intent.
                Bundle bundle = intent.getExtras();

                // Get the latitude entry from the Bundle
                double latitude = bundle.getDouble("com.example.latitude");

                // Get the longitude entry from the Bundle
                double longitude = bundle.getDouble("com.example.longitude");

                mv.getController().setCenter(new GeoPoint(latitude, longitude));
            }
        }
    }
}
