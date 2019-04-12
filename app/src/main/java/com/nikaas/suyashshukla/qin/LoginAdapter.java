package com.nikaas.suyashshukla.qin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LoginAdapter extends PagerAdapter {

    LayoutInflater inflater;
    Context context;
    List<Login> list;

public LoginAdapter(Context context,List<Login> list){
    this.context = context;
    this.list = list;
    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.item_login,null);

        ImageView imageView = viewGroup.findViewById(R.id.login_icon);
        TextView textView = viewGroup.findViewById(R.id.login_type);
        final EditText userName = viewGroup.findViewById(R.id.login_username);
        EditText password = viewGroup.findViewById(R.id.login_password);

        Login login = list.get(position);

        textView.setText(login.getType());
        imageView.setImageResource(login.getResource());

        viewGroup.setTag(textView.getText());

        Button button = viewGroup.findViewById(R.id.login_button);

        final int type;

        if(login.getResource()==R.drawable.teacher)
            type = 0;
        else if(login.getResource()==R.drawable.student)
            type = 1;
        else
            type = 2;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DashboardActivity.class)
                        .putExtra("type",type)
                        .putExtra("name",userName.getText().toString()));
            }
        });

        container.addView(viewGroup);

        return viewGroup;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
