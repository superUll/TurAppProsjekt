package com.example.prosjektfjell.oppogg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Rate and comment on current mountain.
 * Get the average of the rating
 */

public class RatingActivity extends AppCompatActivity {
    private String TAG = RatingActivity.class.getSimpleName();


    EditText comment;
    ProgressDialog pDialog;
    float rateUtsikt, rateTursti, rateTilgjeng, rateTotal;
    String getComment, kommentar, id, finalRate, rate;
    private int success = 0;
    HashMap<String, String> ratingHash;

    String POST = "POST";
    ArrayList<HashMap<String, String>> userRate;
    AddressHandlerPost sh = new AddressHandlerPost();

    //private String url = "http://83.243.149.205:8080/ServerUtOgOpp/services/content/setrating";
    private String url = "http://83.243.149.205/post.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        comment = (EditText) findViewById(R.id.edit_comment);

        //set rating on rating bar
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

        //Get the average of the ratings, and the comment for the current mountain and send it as an JSON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rateUtsikt = ratingBarUtsikt.getRating();
                rateTursti = ratingBarTursti.getRating();
                rateTilgjeng = ratingBarTilgjengelighet.getRating();
                rateTotal = ((rateUtsikt + rateTursti + rateTilgjeng) / 3);
                finalRate = Float.toString(rateTotal);
                kommentar = comment.getText().toString();
                id = DetailActivity.detailMId;
                RatingBar ratingBarTotal = (RatingBar) findViewById(R.id.rating_total);
                ratingBarTotal.setRating(rateTotal);
                new postRatings().execute();
                Toast.makeText(getApplicationContext(), "SNITT" + rateTotal, Toast.LENGTH_LONG).show();


            }
        });
    }

    //Get Values from variables and put them in a HashMap.
    private class postRatings extends AsyncTask<String,String,JSONObject> {


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
        protected JSONObject doInBackground(String... arg) {
            try{
            rate = String.valueOf(rateTotal);
            ratingHash = new HashMap<>();
            ratingHash.put("RRatingTotal", rate);
            ratingHash.put("RRatingComment", kommentar);
            ratingHash.put("MId", id);

            Log.d("request", "starting " + rate);

            JSONObject jsonStr = sh.makeHttpRequest(url, POST, ratingHash);


            if (jsonStr != null) {
                Log.d("JSON result: ", jsonStr.toString());
            }



        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
                Toast.makeText(RatingActivity.this, "Grats mate!", Toast.LENGTH_LONG).show();
            }


        }
    }
}




