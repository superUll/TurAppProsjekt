package com.example.prosjektfjell.oppogg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ContentActivity extends AppCompatActivity {

    private String TAG = ContentActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    ListView listFjell;

    private static String url = "http://10.0.2.2:8080/UtOgOpp/services/list/mountains";

    ArrayList<HashMap<String, String>> fjellist;
    public static String getMname;
    public static String getMheight;
    public static String getMAltidude;
    public static String getMLenght;
    public static String getMtimespan;
    public static String getMPath;
    public static String getMterrain;
    public static String getMgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fjellist = new ArrayList<>();
        new GetMountain().execute();

        listFjell = (ListView) findViewById(R.id.fjelListe);
        int[] colors = {0, 0xFFFFffff};
        listFjell.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listFjell.setDividerHeight(5);

    }

    private class GetMountain extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ContentActivity.this);
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


                    // looping through All mountains
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject f = jsonArr.getJSONObject(i);

                        //String picture = f.getString("picture");
                        String name = f.getString("MName");
                        String height = f.getString("MHeight");
                        String muni = f.getString("MMunicipality");
                        String altitude = f.getString("MAltitude");
                        String lenght = f.getString("MLenght");
                        String timeSpane = f.getString("MTimeSpan");
                        String path = f.getString("MPath");
                        String terrain = f.getString("MTerrain");
                        String grade = f.getString("MDifficulty");
                        // tmp hash map for single mountain
                        HashMap<String, String> mountain = new HashMap<>();

                        // adding each child node to HashMap key => value

                        // mountain.put("Picture", picture);
                        mountain.put("name",name);
                        mountain.put("height",height);
                        mountain.put("muni",muni);
                        mountain.put("alti",altitude);
                        mountain.put("lenght",lenght);
                        mountain.put("span",timeSpane);
                        mountain.put("path",path);
                        mountain.put("terrain",terrain);
                        mountain.put("grade",grade);

                        // adding mountain to mountain list
                        fjellist.add(mountain);
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
            ListAdapter adapter = new SimpleAdapter(
                    ContentActivity.this, fjellist,
                    R.layout.mountain_list, new String[]{"name", "height", "muni"},
                    new int[]{R.id.mountainName, R.id.mHeight, R.id.muni});
            listFjell.setAdapter(adapter);
            listFjell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String,String> map =(HashMap<String,String>)listFjell.getItemAtPosition(position);
                    getMname = map.get("name");
                    getMheight = map.get("height");
                    getMLenght = map.get("lenght");
                    getMAltidude = map.get("alti");
                    getMPath = map.get("path");
                    getMtimespan = map.get("span");
                    getMterrain = map.get("terrain");
                    getMgrade = map.get("grade");

                    Intent intent = new Intent(ContentActivity.this, DetailActivity.class);
                    startActivity(intent);


                }
            });

        }

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
        if (id == R.id.action_quit) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
