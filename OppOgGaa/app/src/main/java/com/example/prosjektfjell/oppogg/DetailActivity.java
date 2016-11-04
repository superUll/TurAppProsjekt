package com.example.prosjektfjell.oppogg;


import android.content.Intent;

import android.graphics.Color;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tHeight,totAlt,totLenght,totTime,track,shoe,grade,rating;
    RatingBar rBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rBar = (RatingBar)findViewById(R.id.ratingTotal);
        float rate = Float.parseFloat(ContentActivity.getRate);
        rBar.setRating(rate);

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


        RatingBar totRating = (RatingBar) findViewById(R.id.rating_total);
        totRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivity.this, RatingActivity.class);
                startActivity(intent);

            }
        });


        String diff = grade.getText().toString();
        if(diff.equals("Lett")) {
            grade.setTextColor(Color.GREEN);
        }
        if(diff.equals("Middels")) {
            grade.setTextColor(Color.YELLOW);
        }
        if(diff.equals("Vanskelig")) {
            grade.setTextColor(Color.RED);
        }


    }

}
