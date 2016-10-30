package com.example.prosjektfjell.oppogg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tHeight,totAlt,totLenght,totTime,track,shoe,grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tHeight = (TextView)findViewById(R.id.setHeight);
        tHeight.setText(ContentActivity.getMheight);

        totAlt = (TextView)findViewById(R.id.setHmeter);
        totAlt.setText(ContentActivity.getMAltidude);

        totLenght= (TextView)findViewById(R.id.setTotal);
        totLenght.setText(ContentActivity.getMLenght);

        totTime = (TextView)findViewById(R.id.setTtid);
        totTime.setText(ContentActivity.getMtimespan);

        track = (TextView)findViewById(R.id.setSti);
        track.setText(ContentActivity.getMPath);

        shoe = (TextView)findViewById(R.id.setSko);
        shoe.setText(ContentActivity.getMterrain);

        grade = (TextView)findViewById(R.id.setGrad);
        grade.setText(ContentActivity.getMgrade);


    }

}
