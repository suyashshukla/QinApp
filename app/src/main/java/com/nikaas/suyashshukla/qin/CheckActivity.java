package com.nikaas.suyashshukla.qin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;

public class CheckActivity extends ReferenceActivity {


    FloatingActionButton next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        next = findViewById(R.id.check_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckActivity.this,FormActivity.class));
            }
        });

    }
}
