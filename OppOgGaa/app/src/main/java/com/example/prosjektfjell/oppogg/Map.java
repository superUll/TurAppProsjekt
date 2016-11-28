package com.example.prosjektfjell.oppogg;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by oleandreheggdal on 20.11.2016.
 */

public class Map extends FragmentActivity implements OnMapReadyCallback {

LatLng latLing;

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(62.1327800,6.0886100 ))
                .title("Marker"));
        latLing = new LatLng(62.1327800,6.0886100 );
       /* map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlocation, 16));
        map.setMaxZoomPreference(0.5f);
        map.setMinZoomPreference(5.0f);*/
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLing,13));


    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try {
            super.startActivityForResult(intent, requestCode);
        } catch (Exception ignored){}
    }

}
