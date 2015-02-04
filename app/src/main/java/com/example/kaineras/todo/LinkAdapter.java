package com.example.kaineras.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created the first version by kaineras on 4/02/15.
 */
public class LinkAdapter extends ArrayAdapter<Tasks>{

    private List<Tasks> optionList;
    private Context ctx;

    public LinkAdapter(List<Tasks> optionList, Context ctx) {
        super(ctx, R.layout.options_layout, optionList);
        this.ctx = ctx;
        this.optionList = optionList;
    }

    @Override
    public int getCount() {
        return optionList.size();
    }

    @Override
    public Tasks getItem(int position) {
        return optionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return optionList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if ( v == null) {
            LayoutInflater inf = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.options_layout, null);
            TextView tName = (TextView) v.findViewById(R.id.optName);
            CheckBox cReady = (CheckBox) v.findViewById(R.id.optReady);

            tName.setText(optionList.get(position).title);
            cReady.setChecked(optionList.get(position).ready);
        }
        return v;
    }
}
