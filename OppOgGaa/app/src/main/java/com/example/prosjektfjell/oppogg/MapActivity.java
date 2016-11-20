package com.example.prosjektfjell.oppogg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.prosjektfjell.oppogg.R.drawable.map;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    MapFragment mapFragment;
    LatLng latLing;
    TextView mName;
    GoogleMap googleMap;
    RadioGroup rgViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mName = (TextView)findViewById(R.id.detailMname);
        mName.setText(DetailActivity.name);

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rgViews = (RadioGroup) findViewById(R.id.rg_views);

    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(62.1327800,6.0886100 ))
                .title("new Marker"));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.pinesmall)));
        latLing = new LatLng(62.1327800,6.0886100 );
       /* map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlocation, 16));
        map.setMaxZoomPreference(0.5f);
        map.setMinZoomPreference(5.0f);*/
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

}
