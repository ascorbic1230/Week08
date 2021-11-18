package com.example.week08;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> mssv;

    public CustomListViewAdapter(@NonNull Context context, int layout, ArrayList<String> mssv) {
        super(context, layout, mssv);
        this.context = context;
        this.mssv = mssv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.list_item_layout, null);
        ImageView image = (ImageView) row.findViewById(R.id.avatar);
        TextView txtMaSo3 = (TextView) row.findViewById(R.id.txtMaSo3);
        image.setImageResource(R.drawable.ic_bach);
        txtMaSo3.setText(mssv.get(position));
        return row;
    }
}
