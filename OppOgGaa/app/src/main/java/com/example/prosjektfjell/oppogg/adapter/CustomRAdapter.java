package com.example.prosjektfjell.oppogg.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.prosjektfjell.oppogg.R;
import com.example.prosjektfjell.oppogg.gallery.app.AppController;
import com.example.prosjektfjell.oppogg.model.Mountain;
import com.example.prosjektfjell.oppogg.model.Rating;

import java.util.List;

/**
 * Created by oleandreheggdal on 18.11.2016.
 */

public class CustomRAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Rating> ratings;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomRAdapter(Activity activity, List<Rating> ratings) {
        this.activity = activity;
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return ratings.size();
    }

    @Override
    public Object getItem(int location) {
        return ratings.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.comment, null);

        TextView comment = (TextView) convertView.findViewById(R.id.userComment);
        // getting movie data for the row
        Rating r = ratings.get(position);

        comment.setText(r.getComment());


        return convertView;
    }


}


