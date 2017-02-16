package com.example.whitelegg_n.osmdroid1;

/**
 * Created by 2speea07 on 13/02/2017.
 */
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class SetLocation extends Activity implements View.OnClickListener{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setl);

        Button setbutton = (Button)findViewById(R.id.set);
        setbutton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        // Create an Intent to send information back to the main activity.
        Intent intent = new Intent();

        // Create a Bundle to store data to be sent back.
        Bundle bundle = new Bundle();

        EditText et = (EditText)findViewById(R.id.latitude);
        double latitude = Double.parseDouble(et.getText().toString());

        EditText et1 = (EditText)findViewById(R.id.longitude);
        double longitude = Double.parseDouble(et1.getText().toString());


        // Add the boolean to the Bundle.
        bundle.putDouble("com.example.latitude", latitude);
        // Add the boolean to the Bundle.
        bundle.putDouble("com.example.longitude", longitude);
        // Add the Bundle to the Intent.
        intent.putExtras(bundle);

        // Set an OK result, meaning that everything was successful.
        setResult(RESULT_OK, intent);

        // Close the second activity (this will return to the first)
        finish();
    }
}
