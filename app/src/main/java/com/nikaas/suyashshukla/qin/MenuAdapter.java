package com.nikaas.suyashshukla.qin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<Menu> {

    int resource;
    Context context;

    public MenuAdapter(@NonNull Context context, int resource) {

        super(context, resource);
        this.context = context;
        this.resource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
            convertView = LayoutInflater.from(context)
                    .inflate(resource,parent,false);

        Menu menu = getItem(position);

        TextView name = convertView.findViewById(R.id.menu_name);
        ImageView icon = convertView.findViewById(R.id.menu_icon);

        name.setText(menu.getName());
        icon.setImageResource(menu.getResource());


        return convertView;
    }
}

