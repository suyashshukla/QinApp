package com.nikaas.suyashshukla.qin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends ReferenceActivity {

    Spinner subject;
    SpinnerAdapter subjectAdapter;
    TextView subjectName;
    TextView percentage;
    PieChart percentageIndicator;
    Button fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final List<PieEntry> list = new ArrayList<>();

        list.add(new PieEntry(25,"PPL"));
        list.add(new PieEntry(50,"SEPM"));
        list.add(new PieEntry(40,"CN"));
        list.add(new PieEntry(75,"ACA"));
        list.add(new PieEntry(90,"IPR"));


        final List<Integer> colors = new ArrayList<>();

        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.DKGRAY);

        subject = findViewById(R.id.form_subject);
        subjectName = findViewById(R.id.form_subject_name);
        percentageIndicator = findViewById(R.id.form_ptage_bar);
        percentage = findViewById(R.id.form_ptage);
        fetch = findViewById(R.id.form_fetch);

        PieDataSet pieDataSet = new PieDataSet(list,"Subjects");
        pieDataSet.setColors(colors);
        percentageIndicator.setData(new PieData(pieDataSet));
        percentageIndicator.invalidate();

        subjectAdapter = ArrayAdapter.createFromResource(this,R.array.subject_list,android.R.layout.simple_dropdown_item_1line);
        subject.setAdapter(subjectAdapter);

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectName.setText(subjectAdapter.getItem(position).toString().split(" : ")[1]);
                subjectName.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = (int)(Math.random()*100);

                percentage.setVisibility(View.VISIBLE);
                percentage.setText(String.valueOf(p));
                percentageIndicator.setVisibility(View.VISIBLE);

                list.clear();
try {
    p = Integer.valueOf(DashboardActivity.studentData.get(subjectName.getText().toString().trim().toLowerCase()).toString());
}
catch (Exception e){
    Toast.makeText(FormActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
    p = 75;
}
                list.add(new PieEntry(p,"PRESENT"));
                list.add(new PieEntry((100-p),"ABSENT"));

                PieDataSet pieDataSet = new PieDataSet(list,"Attendance");
                pieDataSet.setColors(colors);

                PieData pieData = new PieData(pieDataSet);

                percentageIndicator.setData(pieData);
                percentage.setVisibility(View.INVISIBLE);
                percentageIndicator.invalidate();

                if(p>=75) {
                    percentage.setTextColor(Color.GREEN);
                }
                else if(p>=50){
                    percentage.setTextColor(Color.YELLOW);
                }
                else {
                    percentage.setTextColor(Color.RED);
                }

            }
        });








    }
}
