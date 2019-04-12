package com.nikaas.suyashshukla.qin;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttendanceActivity extends ReferenceActivity {

    ListView listView;
    List<String> list;
    AttendanceAdapter attendanceAdapter;
    Button submit;

    static String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.student_list)));
        attendanceAdapter = new AttendanceAdapter(this,R.layout.item_attendance);
        listView = findViewById(R.id.attendance_list);
        submit = findViewById(R.id.attendance_submit);

        attendanceAdapter.addAll(list);

        listView.setAdapter(attendanceAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

               final CheckedTextView textView = view.findViewById(R.id.attendance_name);

               AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);

               p = "11";

               builder.setTitle("Mark Attendance")
                       .setSingleChoiceItems(new CharSequence[]{"Present", "Absent"}, 0, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                                    if(which==0)
                                        p = "11";
                                    else
                                        p = "-11";
                           }
                       }).setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       FirebaseFirestore.getInstance().collection("students")
                               .whereEqualTo("name",attendanceAdapter.getItem(position)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                           @Override
                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                               if(task.isSuccessful() && !task.getResult().isEmpty()){

                                   task.getResult().getDocuments().get(0).getReference().update("status",p)
                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                   Toast.makeText(AttendanceActivity.this, "Attendance Marked Successfuly", Toast.LENGTH_SHORT).show();
                                               }
                                           });
                               }
                           }
                       });
                   }
               })

                       .create().show();
//
//                if(textView.isChecked())
//                    textView.setChecked(false);
//                else
//                    textView.setChecked(true);



            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);
//
//                builder.setMessage("Are you sure you want to submit ?")
//                        .setTitle("Confirmation")
//                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(AttendanceActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        })
//                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        }).create().show();
          }

        });



    }
}
