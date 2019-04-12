package com.nikaas.suyashshukla.qin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class AttendanceAdapter extends ArrayAdapter<String> {

    Context context;
    int resource;

    public AttendanceAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);

        CheckedTextView textView = convertView.findViewById(R.id.attendance_name);
        ImageView imageView = convertView.findViewById(R.id.attendance_pic);

        String name = getItem(position);

        textView.setText(name);

        ColorGenerator colorGenerator = ColorGenerator.MATERIAL;

        int color = colorGenerator.getColor("QIN");

        TextDrawable textDrawable = TextDrawable.builder().buildRound(name.substring(0,1),color);

        imageView.setImageDrawable(textDrawable);

        return convertView;
    }
}
