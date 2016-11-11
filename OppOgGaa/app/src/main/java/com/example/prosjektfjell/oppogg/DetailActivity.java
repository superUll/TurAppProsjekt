package com.example.prosjektfjell.oppogg;


import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Color;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    private String TAG = DetailActivity.class.getSimpleName();

    ListView listComments;
    ArrayList<HashMap<String, String>> comments;
    TextView tHeight,totAlt,totLenght,totTime,track,shoe,grade,detailName,rateIt;
    RatingBar rBar;
    ProgressDialog pDialog;
    ListAdapter adapter;
    String totRate;
    public static String id;
    private static String url = "http://10.0.2.2:8080/UtOgOpp/services/content/ratings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        comments = new ArrayList<>();
        new GetComments().execute();
        listComments = (ListView)findViewById(R.id.commentList);


        detailName = (TextView)findViewById(R.id.detailMname);
        detailName.setText(ContentActivity.getMname);

        rateIt = (TextView)findViewById(R.id.textRate);
        rateIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,RatingActivity.class);
                startActivity(intent);
            }
        });

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
    private class GetComments extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DetailActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            AddressHandler sh = new AddressHandler();

            // Making a request to url
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonArr = new JSONArray(jsonStr);


                    // looping through All comments
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject r = jsonArr.getJSONObject(i);

                        totRate = r.getString("RRatingTotal");
                        JSONObject rmid = r.getJSONObject("rmId");
                        id = rmid.getString("MTId");

                        if(ContentActivity.getID == id) {
                            String comment = r.getString("RRatingComment");
                            // tmp hash map for single mountain
                            HashMap<String, String> rComment = new HashMap<>();

                            // adding each child node to HashMap key => value

                            rComment.put("comment", comment);


                            // adding comment to list
                            comments.add(rComment);
                        }
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            adapter = new SimpleAdapter(
                    DetailActivity.this, comments,
                    R.layout.comment, new String[]{"comment"},
                    new int[]{R.id.textComment});
            listComments.setAdapter(adapter);

            rBar = (RatingBar)findViewById(R.id.ratingTotal);
            float rate = Float.parseFloat(totRate);
            rBar.setRating(rate);


        }

    }

}
