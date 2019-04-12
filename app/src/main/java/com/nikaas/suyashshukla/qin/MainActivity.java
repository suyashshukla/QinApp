package com.nikaas.suyashshukla.qin;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ReferenceActivity {

    ViewPager viewPager;
    LoginAdapter loginAdapter;
    List<Login> list;

    static String enrollment;

    EditText userName;
    EditText password;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_login);

//        list = new ArrayList<>();
//
//          DataMapping.mapData(this);
//
//        list.add(new Login("Teacher",R.drawable.teacher));
//        list.add(new Login("Student",R.drawable.student));
//        list.add(new Login("Head",R.drawable.admin));
//
//        viewPager = findViewById(R.id.login_viewpager);
//        loginAdapter = new LoginAdapter(this,list);
//
//        viewPager.setAdapter(loginAdapter);
//
//        viewPager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MainActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

        userName = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        button = findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userName.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }
                else{

                    if(password.getText().toString().matches("sjsqinyog")) {
                        enrollment = userName.getText().toString().toUpperCase().substring(4);
                        startActivity(new Intent(MainActivity.this, OnBoarderActivity.class));
                        password.setText("");
                        userName.setText("");
                    }
                    else
                        Toast.makeText(MainActivity.this,"Incorrect Password",Toast.LENGTH_SHORT).show();

                }

            }
        });





    }
}
