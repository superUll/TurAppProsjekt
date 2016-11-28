package com.example.prosjektfjell.oppogg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Welcome screen. sends the user to the ContentActivity class. Will provide more functions later.
 */

public class MainActivity extends AppCompatActivity {

    // Declaring of button
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the layout of the MainActivity
        setContentView(R.layout.activity_main);

        // Finds the button in the xml file and makes a instance of it.
        startBtn = (Button)findViewById(R.id.startBtn);
        // onClickListener that will listen for a click on the button.
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // New Intent is created, that is used to start a new Activity. The ContentActivity
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                startActivity(intent);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
