package com.example.prosjektfjell.oppogg;


import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Color;

import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.prosjektfjell.oppogg.gallery.activity.GalleryActivity;
import com.example.prosjektfjell.oppogg.gallery.adapter.GalleryAdapter;
import com.example.prosjektfjell.oppogg.gallery.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    private String TAG = DetailActivity.class.getSimpleName();

    ListView listComments;
    ArrayList<HashMap<String, String>> comments;
    ImageView imageView;
    TextView tHeight,totAlt,totLenght,totTime,track,shoe,grade,detailName,rateIt,mapBtn;
    String height, altitude,path,timeSpan,terrain,difficulty,mLenght;
    String GET = "GET";
    RatingBar rBar;
    ProgressDialog pDialog;
    ListAdapter adapter;
    String totRate;
    public static String detailMId;
    public static String name;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    MapActivity mapApp;

    private static String url = "http://83.243.149.205:8080/ServerUtOgOpp/services/content/ratings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("MName");
        height = bundle.getString("MHeight");
        altitude = bundle.getString("MAltitude");
        path = bundle.getString("MPath");
        timeSpan = bundle.getString("MTimeSpan");
        terrain = bundle.getString("MTerrain");
        difficulty = bundle.getString("MDifficulty");
        mLenght = bundle.getString("MLenght");
        detailMId = bundle.getString("MId");

        mapApp = new MapActivity();




        NetworkImageView img = (NetworkImageView)findViewById(R.id.detail_image);
        if ( ContentActivity.getThumbId != null) {
            img.setImageUrl(ContentActivity.getThumbId,imageLoader);
        }
        else {
            img.setDefaultImageResId(R.drawable.fjell2);
        }

        comments = new ArrayList<>();
        new GetComments().execute();

        listComments = (ListView)findViewById(R.id.commentList);
        int[] colors = {0, 0xFFFFffff,0};
        listComments.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listComments.setDividerHeight(2);
        listComments.setFocusable(false);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });

        detailName = (TextView)findViewById(R.id.detailMname);
        detailName.setText(name);

        rateIt = (TextView)findViewById(R.id.textRate);
        rateIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,RatingActivity.class);
                startActivity(intent);
            }
        });



        tHeight = (TextView)findViewById(R.id.setHeight);
        tHeight.setText(height);

        totAlt = (TextView)findViewById(R.id.setHmeter);
        totAlt.setText(altitude);

        totLenght= (TextView)findViewById(R.id.setTotal);
        totLenght.setText(mLenght);

        totTime = (TextView)findViewById(R.id.setTtid);
        totTime.setText(timeSpan);

        track = (TextView)findViewById(R.id.setSti);
        track.setText(path);

        shoe = (TextView)findViewById(R.id.setSko);
        shoe.setText(terrain);

        grade = (TextView)findViewById(R.id.setGrad);
        grade.setText(difficulty);
        String diff = grade.getText().toString();
        if(diff.startsWith("Lett")) {
            grade.setTextColor(Color.GREEN);
        }
        if(diff.startsWith("Medium")) {
            grade.setTextColor(Color.YELLOW);
        }
        if(diff.startsWith("Vanskelig")) {
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
            JSONArray jsonStr = sh.makeServiceCall(url, GET, comments);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONArray jsonArr = new JSONArray(jsonStr);


                    // looping through All comments
                    for (int i = 0; i < jsonStr.length(); i++) {
                        JSONObject r = jsonStr.getJSONObject(i);


                        totRate = r.getString("RRatingTotal");
                        JSONObject rmid = r.getJSONObject("MId");
                        String id = rmid.getString("MId");

                        if(ContentActivity.getID == id) {
                            String comment = r.getString("RRatingComment");
                            // tmp hash map for single mountain
                            HashMap<String, String> rComment = new HashMap<>();

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
                    new int[]{R.id.userComment});
            listComments.setAdapter(adapter);

            rBar = (RatingBar)findViewById(R.id.rating_total);
            float rate = Float.parseFloat(totRate);
            rBar.setRating(rate);


        }

    }


}
