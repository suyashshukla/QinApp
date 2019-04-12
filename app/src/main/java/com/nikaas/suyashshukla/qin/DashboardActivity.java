package com.nikaas.suyashshukla.qin;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class DashboardActivity extends ReferenceActivity {

    GridView gridView;
    MenuAdapter menuAdapter;

    static Map<String, Object> studentData;

    ImageView profilePic;
    TextView profileName;
    TextView profileType;

    TextView subjects;

    static DocumentReference notifyReference;
    static String status;

    List<Menu> admin;
    List<Menu> student;
    List<Menu> teacher;

    BarChart barChart;

    final String CHANNEL_ID = "Qin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        studentData = new HashMap<>();

        FirebaseFirestore.getInstance().collection("students").whereEqualTo("enroll",MainActivity.enrollment)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful() && !task.getResult().isEmpty()){
                    studentData = task.getResult().getDocuments().get(0).getData();

                    profileName.setText(studentData.get("name").toString());
                    profileType.setText("0902".concat(studentData.get("enroll").toString()));

                    String gender = studentData.get("gender").toString();

                    if(gender.equalsIgnoreCase("m"))
                        Glide.with(DashboardActivity.this)
                                .load(R.drawable.male)
                                .apply(new RequestOptions().circleCrop())
                                .into(profilePic);
                    else
                        Glide.with(DashboardActivity.this)
                                .load(R.drawable.female)
                                .apply(new RequestOptions().circleCrop())
                                .into(profilePic);


                }
                else{
                    Toast.makeText(DashboardActivity.this,"User Not Found",Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

        profileName = findViewById(R.id.profile_name);
        profilePic = findViewById(R.id.profile_pic);
        profileType = findViewById(R.id.profile_type);
        subjects = findViewById(R.id.subjects);


        subjects.setText("ACA|SEPM|CN|PPL|IPR");

        barChart = findViewById(R.id.bar_chart);

        List<BarEntry> list = new ArrayList<>();


        list.add(new BarEntry(0f,60));
        list.add(new BarEntry(2f,50));
        list.add(new BarEntry(4f,40));
        list.add(new BarEntry(6f,75));
        list.add(new BarEntry(8f,90));

        BarDataSet barDataSet = new BarDataSet(list,"Subjects");

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.DKGRAY);

        barDataSet.setColors(colors);

        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.setDescription(new Description());
        barChart.invalidate();


        admin = new ArrayList<>();
        student = new ArrayList<>();
        teacher = new ArrayList<>();

        teacher.add(new Menu("Mark Attendance",R.drawable.mark));
        teacher.add(new Menu("Grant Attendance",R.drawable.grant));
        teacher.add(new Menu("Resolve Attendance",R.drawable.resolve));
        teacher.add(new Menu("Generate Report",R.drawable.reports));
        teacher.add(new Menu("Track Attendance",R.drawable.track));
        teacher.add(new Menu("Share Attendance",R.drawable.share));


        student.add(new Menu("Check Attendance",R.drawable.check));
        student.add(new Menu("Academic Calendar",R.drawable.academic));
        student.add(new Menu("Syllabus",R.drawable.books));
        student.add(new Menu("Time Table",R.drawable.time_table));

        admin.add(new Menu("Assign Subject",R.drawable.assign_subject));
        admin.add(new Menu("Assign Faculty",R.drawable.assign_teacher));
        admin.add(new Menu("Student Management",R.drawable.student_management));


        gridView = findViewById(R.id.menu_grid);

        menuAdapter = new MenuAdapter(this,R.layout.item_menu);

        int type = getIntent().getIntExtra("type",0);
        String name = getIntent().getStringExtra("name");

        switch (type){

            case 0:
                menuAdapter.addAll(teacher);
                profileName.setText("Prof. Vivek Gupta");
                profileType.setText("Faculty");
                Glide.with(this).load(R.drawable.vivek).apply(new RequestOptions().circleCrop()).into(profilePic);
                break;
            case 1:
                menuAdapter.addAll(student);
                profileName.setText(name);
                profileType.setText("Student");
                Glide.with(this).load(R.drawable.kid).apply(new RequestOptions().circleCrop()).into(profilePic);
                listenAttendance(name);
                break;
            case 2:
                menuAdapter.addAll(admin);
                profileName.setText("Prof. Yograj Sharma");
                profileType.setText("Head");
                Glide.with(this).load(R.drawable.exams).apply(new RequestOptions().circleCrop()).into(profilePic);
                break;


        }

        gridView.setAdapter(menuAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Menu menu = menuAdapter.getItem(position);

                switch (menu.getResource()){

                    case R.drawable.mark:
                        startActivity(new Intent(DashboardActivity.this,AttendanceActivity.class));
                        break;
                    case R.drawable.check:
                        startActivity(new Intent(DashboardActivity.this,CheckActivity.class));
                        break;
                    case R.drawable.books:
                        startActivity(new Intent(DashboardActivity.this,PDFActivity.class)
                                .putExtra("url","https://www.rgpv.ac.in/UC/frm_download_file.aspx?Filepath=CDN/PubContent/Scheme/VI%20Sem%20CSE%20_SY020118034837.pdf"));
                        break;
                    case R.drawable.time_table:
                        startActivity(new Intent(DashboardActivity.this,PDFActivity.class)
                                .putExtra("url","android.resource://".concat(DashboardActivity.this.getPackageName()).concat("/")));
                        break;
                    case R.drawable.academic:
                        startActivity(new Intent(DashboardActivity.this,PDFActivity.class)
                                .putExtra("url","http://rjit.org/Download/Download.aspx?Filepath=" +
                                        "LastestDownloads/AcademicCalendar_Academic%20calender%20-%20Even%20sem%20%202018-19_210119013032.pdf"));
                        break;



                }


            }
        });

    }

    public void listenAttendance(String name){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("students").whereEqualTo("enroll",MainActivity.enrollment)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                if (!queryDocumentSnapshots.isEmpty()) {
                    String data = queryDocumentSnapshots.getDocuments().get(0).get("status")
                            .toString();

                    String pDay = String.valueOf(Calendar.getInstance().get(Calendar.DATE));
                    String nDay = String.valueOf(Calendar.getInstance().get(Calendar.DATE));

                    notifyReference = queryDocumentSnapshots.getDocuments().get(0).getReference();
                    status = data;

                    if (data.equals(pDay))
                        triggerNotification("Attendance Marked",
                                "Your Attendance for the Current Period has been marked on " + DateFormat.getDateInstance()
                                        .format(Calendar.getInstance().getTime()));
                    else if(data.equals("-".concat(nDay)))
                        triggerNotification("Student Absent",
                                "You've been marked ABSENT for the Current Period on " + DateFormat.getDateInstance()
                                        .format(Calendar.getInstance().getTime()));
                }
            }
        });



    }


    public void triggerNotification(String data,String message){

        final NotificationCompat.Builder notificationCompat = new NotificationCompat
                .Builder(DashboardActivity.this,CHANNEL_ID);

        notificationCompat.setSmallIcon(R.drawable.notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification))
                .setContentTitle(data)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setSound(Uri.parse("android.resource://".concat(this.getPackageName()).concat("/").concat(R.raw.notification+"")))
                .setVibrate(new long[]{0,100,200,300})
                .setPriority(NotificationCompat.PRIORITY_MAX).build();

        final NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


        notifyReference.update("status",status.concat("D")).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                notificationManager.notify(1,notificationCompat.build());
            }
        });


    }
}
