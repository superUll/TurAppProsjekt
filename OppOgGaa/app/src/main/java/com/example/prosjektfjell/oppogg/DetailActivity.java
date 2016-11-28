package com.example.prosjektfjell.oppogg;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.prosjektfjell.oppogg.gallery.activity.GalleryActivity;
import com.example.prosjektfjell.oppogg.gallery.app.AppController;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Gets mountain attributes and show them
 */

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
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
    //Singleton class in which we initialize the volley’s core objects.
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    MapActivity mapApp;

    private static String url = "http://83.243.149.205:8080/ServerUtOgOpp/services/content/ratings";

    MapFragment mapFragment;
    LatLng latLing;
    TextView mName;
    GoogleMap googleMap;
    RadioGroup rgViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Instance of bundle to get the values that was stored in the Intent
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

        mName = (TextView)findViewById(R.id.detailMname);
        mName.setText(DetailActivity.name);

        //sett fragment that are adjacent to the xml file
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Map buttons
        rgViews = (RadioGroup) findViewById(R.id.rg_views);

        //mapApp = new MapActivity();

        //Get and sets the images into the NetworkImageView
        NetworkImageView img = (NetworkImageView)findViewById(R.id.detail_image);
        if ( ContentActivity.getThumbId != null) {
            img.setImageUrl(ContentActivity.getThumbId,imageLoader);
        }
        else {
            img.setDefaultImageResId(R.drawable.fjell2);
        }
        // instance of ArrayList
        comments = new ArrayList<>();
        //execute the GetCommand class
        new GetComments().execute();

        listComments = (ListView)findViewById(R.id.commentList);
        int[] colors = {0, 0xFFFFffff,0};
        listComments.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listComments.setDividerHeight(2);
        listComments.setFocusable(false);

        //Listener on top image. goes to gallery
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
        //Listener on rating textView. Goes to RatingActivity
        rateIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,RatingActivity.class);
                startActivity(intent);
            }
        });
//        mapBtn = (TextView)findViewById(R.id.kart);
//        mapBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DetailActivity.this,MapActivity.class);
//                startActivity(intent);
//            }
//        });



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
    //Adds latitude and longitude and a marker.
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(62.1327800,6.0886100 ))
                .title("new Marker"));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.pinesmall)));
        latLing = new LatLng(62.1327800,6.0886100 );
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLing,13));
        rgViews.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_normal){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }else if(checkedId == R.id.rb_satellite){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }else if(checkedId == R.id.rb_terrain){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }else if(checkedId == R.id.rb_full_size){
                    Intent intent = new Intent(DetailActivity.this,MapActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try {
            super.startActivityForResult(intent, requestCode);
        } catch (Exception ignored){}
    }

    //Get JSON array from URL. gets the JSON Objects and adds them to a ArrayList.
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
            JSONArray jsonStr = sh.makeServiceCall(url, GET);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {

                    // looping through All objects
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

            adapter = new SimpleAdapter(DetailActivity.this, comments, R.layout.comment, new String[]{"comment"},
                    new int[]{R.id.userComment});
            listComments.setAdapter(adapter);

            rBar = (RatingBar)findViewById(R.id.rating_total);
            float rate = Float.parseFloat(totRate);
            rBar.setRating(rate);


        }

    }


}
