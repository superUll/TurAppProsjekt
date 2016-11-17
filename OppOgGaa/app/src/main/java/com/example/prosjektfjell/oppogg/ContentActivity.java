package com.example.prosjektfjell.oppogg;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.prosjektfjell.oppogg.adapter.CustomListAdapter;
import com.example.prosjektfjell.oppogg.gallery.app.AppController;
import com.example.prosjektfjell.oppogg.model.Mountain;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity  {

    private String TAG = ContentActivity.class.getSimpleName();
    public static String getThumbId;
    public static String getID;
    private ProgressDialog pDialog;
    private List<Mountain> mountains = new ArrayList<Mountain>();
    private ListView listView;
    private CustomListAdapter adapter;
    EditText search;
    private static String url = "http://83.243.149.205:8080/ServerUtOgOpp/services/content/mountains";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        adapter = new CustomListAdapter(this, mountains);
        listView = (ListView) findViewById(R.id.fjelListe);
        int[] colors = {0, 0xFFFFffff};
        listView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listView.setDividerHeight(5);

        search = (EditText)findViewById(R.id.search);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


        // Creating volley request obj
        final JsonArrayRequest mountain = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Mountain m = new Mountain();
                                m.setName(obj.getString("MName"));
                                m.setThumbnailUrl(obj.getString("MThumbnail"));
                                m.setHeight(obj.getString("MHeight"));
                                m.setMuni(obj.getString("MMunicipality"));
                                m.setId(obj.getString("MId"));
                                m.setMAltitide(obj.getString("MAltitude"));
                                m.setMPath(obj.getString("MPath"));
                                m.setMLength(obj.getString("MLenght"));
                                m.setMTimespan(obj.getString("MTimeSpan"));
                                m.setMDifficulty(obj.getString("MDifficulty"));
                                m.setMTerrain(obj.getString("MTerrain"));

                                mountains.add(m);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);



                       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Mountain mo = mountains.get(position);
                                Intent intent = new Intent(ContentActivity.this, DetailActivity.class);
                                intent.putExtra("MName", mo.getName());
                                intent.putExtra("MHeight", mo.getHeight());
                                intent.putExtra("MAltitude", mo.getMAltitude());
                                intent.putExtra("MPath", mo.getMPath());
                                intent.putExtra("MLenght", mo.getMLength());
                                intent.putExtra("MTimeSpan", mo.getMTimespan());
                                intent.putExtra("MTerrain", mo.getMTerrain());
                                intent.putExtra("MDifficulty", mo.getMDifficulty());
                                intent.putExtra("MId", mo.getId());
                                intent.putExtra("MThumbnail", mo.getThumbnailUrl());
                                getThumbId = mo.getThumbnailUrl();
                                getID = mo.getId();
                                startActivity(intent);



                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(mountain);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
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
