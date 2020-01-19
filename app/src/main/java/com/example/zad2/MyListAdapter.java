package com.example.zad2;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> maintitle;
    private final List<String> subtitle;
    private final List<Uri> imgid;

    public MyListAdapter(Activity context, List<String> maintitle, List<String> subtitle, List<Uri> imgid) {
        super(context, R.layout.mylist, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        titleText.setText(maintitle.get(position));
        imageView.setImageURI(imgid.get(position));
        subtitleText.setText(subtitle.get(position));
        return rowView;
    };
}