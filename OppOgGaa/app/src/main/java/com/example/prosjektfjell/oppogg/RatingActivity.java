package com.example.prosjektfjell.oppogg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RatingBar ratingBarUtsikt = (RatingBar) findViewById(R.id.ratingBar_utsikt);
        ratingBarUtsikt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarUtsikt.setRating(rating);
            }
        });

        final RatingBar ratingBarTursti = (RatingBar) findViewById(R.id.ratingBar_tursti);
        ratingBarTursti.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarTursti.setRating(rating);
            }
        });

        final RatingBar ratingBarTilgjengelighet = (RatingBar) findViewById(R.id.ratingBar_tilgjengelighet);
        ratingBarTilgjengelighet.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarTilgjengelighet.setRating(rating);
            }
        });

        final RatingBar ratingBarOppmerking = (RatingBar) findViewById(R.id.ratingBar_oppmerking);
        ratingBarTursti.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarOppmerking.setRating(rating);
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO send the rating values to the database and goes back to DetailActivity
                //"RatingBarName".getRating();



            }
        });



    }

}
