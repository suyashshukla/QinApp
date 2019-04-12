package com.nikaas.suyashshukla.qin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DataMapping {

    public static void mapData(final Context context){


        Map<String,String> map = new HashMap<>();

        String array[] = context.getResources().getStringArray(R.array.student_list);

        for(int i = 0;i<array.length;i++){

            map.put("name",array[i]);
            map.put("gender","m");
            map.put("enroll", String.valueOf(i + 1));
            map.put("ppl",String.valueOf((int)(Math.random()*100)));
            map.put("status","0");
            map.put("aca",String.valueOf((int)(Math.random()*100)));
            map.put("sepm",String.valueOf((int)(Math.random()*100)));
            map.put("cn",String.valueOf((int)(Math.random()*100)));
            map.put("ipr",String.valueOf((int)(Math.random()*100)));
            map.put("total",String.valueOf((int)(Math.random()*100)));


            new DataMapping().firestoreMapping(map,context,i);
            map.clear();

        }


    }

    public void firestoreMapping(Map<String,String> map, final Context context, final int i){


        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("students").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful())
                Toast.makeText(context,"Data Mapped Succesfully : "+i,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
