package com.example.prosjektfjell.oppogg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.HashMap;

public class RatingActivity extends AppCompatActivity {

    EditText comment;
    ProgressDialog pDialog;
    float rateUtsikt, rateTursti,rateTilgjeng, rateTotal;
    String getComment,kommentar,id,rate;
    private int success=0;

    private String url = "http://10.0.2.2:8080/UtOgOpp/services/list/getratingss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        comment = (EditText)findViewById(R.id.edit_comment);

        final RatingBar ratingBarUtsikt = (RatingBar) findViewById(R.id.rating_utsikt);
        ratingBarUtsikt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarUtsikt.setRating(rating);
            }
        });

        final RatingBar ratingBarTursti = (RatingBar) findViewById(R.id.rating_tursti);
        ratingBarTursti.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarTursti.setRating(rating);
            }
        });

        final RatingBar ratingBarTilgjengelighet = (RatingBar) findViewById(R.id.rating_tilgjengelighet);
        ratingBarTilgjengelighet.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarTilgjengelighet.setRating(rating);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rateUtsikt = ratingBarUtsikt.getRating();
                rateTursti = ratingBarTursti.getRating();
                rateTilgjeng = ratingBarTilgjengelighet.getRating();
                rateTotal = ((rateUtsikt + rateTursti + rateTilgjeng)/3);
                kommentar = comment.getText().toString();
                id = DetailActivity.detailMId;
                RatingBar ratingBarTotal = (RatingBar) findViewById(R.id.rating_total);
                ratingBarTotal.setRating(rateTotal);
                Toast.makeText(getApplicationContext(),"SNITT" + rateTotal,Toast.LENGTH_LONG).show();
                new postRatings().execute();

            }
        });
    }
    private class postRatings extends AsyncTask<Void, Void, Void> {

        String response = "";
        HashMap<String, String> rating;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(RatingActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            AddressHandlerPost sh = new AddressHandlerPost();
/*
            // Making a request to url
            String jsonStr = sh.makePostCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);*/
            String jsonStr = sh.makePostCall(url);


            rate = String.valueOf(rateTotal);
            rating = new HashMap<String, String>();
            rating.put("RRatingTotal", rate);
            rating.put("RRatongComment", kommentar);
            rating.put("RM_ID", id);
            response = jsonStr;




            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(success==1) {
                Toast.makeText(getApplicationContext(), "Rating Added successfully..!", Toast.LENGTH_LONG).show();
            }




        }

    }


}
