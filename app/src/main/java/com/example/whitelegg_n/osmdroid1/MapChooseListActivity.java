package com.example.whitelegg_n.osmdroid1;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MapChooseListActivity extends ListActivity
{
    String[] data;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        data = new String[] { "RegularMap", "CycleMap" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        // Set the cyclemap boolean variable to false by default.
        boolean cyclemap = false;

        // Get the ID of the button that was pressed. If it's the ID for the cyclemap
        // button, set the cyclemap boolean to true.
        if(view.getId() == R.id.btnCyclemap)
        {
            cyclemap = true;
        }
    }
}