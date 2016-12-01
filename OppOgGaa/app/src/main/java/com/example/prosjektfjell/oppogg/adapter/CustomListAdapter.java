package com.example.prosjektfjell.oppogg.adapter;

/**
 * Created by oleandreheggdal on 12.11.2016.
 * Provides access to the data.
 * The adapter is also responsible for making a View for each item in the data set
 *
 */

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

import java.util.List;


public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Mountain> mountains;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    // constructor and the getView() method describe the translation between the data item and the View to display.
    public CustomListAdapter(Activity activity, List<Mountain> mountains) {
        this.activity = activity;
        this.mountains = mountains;
    }

    @Override
    public int getCount() {
        return mountains.size();
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public Object getItem(int location) {
        return mountains.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Define the adapter to describe the process of converting the Java object to a View
    //Returns the view used as a row within the ListView at a particular position.
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
