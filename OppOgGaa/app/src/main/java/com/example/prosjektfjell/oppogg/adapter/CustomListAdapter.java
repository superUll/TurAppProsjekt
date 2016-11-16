package com.example.prosjektfjell.oppogg.adapter;

/**
 * Created by oleandreheggdal on 12.11.2016.
 */

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
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


public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Mountain> mountains;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Mountain> mountains) {
        this.activity = activity;
        this.mountains = mountains;
    }

    @Override
    public int getCount() {
        return mountains.size();
    }

    @Override
    public Object getItem(int location) {
        return mountains.get(location);
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
            convertView = inflater.inflate(R.layout.mountain_list, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.imageView4);
        TextView name = (TextView) convertView.findViewById(R.id.mountainName);
        TextView height = (TextView) convertView.findViewById(R.id.mHeight);
        TextView muni = (TextView) convertView.findViewById(R.id.muni);
        // getting movie data for the row
        Mountain m = mountains.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);


        // title
        name.setText(m.getName());
        height.setText(m.getHeight());
        muni.setText(m.getMuni());

        return convertView;
    }


}
