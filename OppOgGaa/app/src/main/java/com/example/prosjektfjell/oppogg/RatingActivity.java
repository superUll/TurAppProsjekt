package com.example.prosjektfjell.oppogg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.prosjektfjell.oppogg.model.Rating;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.android.volley.Request.Method.POST;

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

    //private String url = "http://10.0.2.2:8080/UtOgOpp/services/content/getratings";
    private String url = "http://83.243.149.205/post.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        comment = (EditText) findViewById(R.id.edit_comment);

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




