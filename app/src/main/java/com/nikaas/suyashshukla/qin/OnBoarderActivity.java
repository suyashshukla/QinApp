package com.nikaas.suyashshukla.qin;

import android.content.Intent;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class OnBoarderActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("No Tension!",
                "Get ready to experience an altogether different student management system", R.drawable.exams);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setTitleColor(R.color.white);
        ahoyOnboarderCard1.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(8, this));
        //ahoyOnboarderCard1.setIconLayoutParams(iconWidth, iconHeight, marginTop, marginLeft, marginRight, marginBottom);


        AhoyOnboarderCard attendance = new AhoyOnboarderCard("Paperless!",
                "Marking student attendance was never this simple before.", R.drawable.attendance);
        attendance.setBackgroundColor(R.color.black_transparent);
        attendance.setTitleColor(R.color.white);
        attendance.setDescriptionColor(R.color.grey_200);
        attendance.setTitleTextSize(dpToPixels(10, this));
        attendance.setDescriptionTextSize(dpToPixels(8, this));
        //ahoyOnboarderCard1.setIconLayoutParams(iconWidth, iconHeight, marginTop, marginLeft, marginRight, marginBottom);


        AhoyOnboarderCard happy = new AhoyOnboarderCard("Satisfaction!", "We assure you the best of our services to make you feel relieved.", R.drawable.kid);
        happy.setBackgroundColor(R.color.black_transparent);
        happy.setTitleColor(R.color.white);
        happy.setDescriptionColor(R.color.grey_200);
        happy.setTitleTextSize(dpToPixels(10, this));
        happy.setDescriptionTextSize(dpToPixels(8, this));
        //ahoyOnboarderCard1.setIconLayoutParams(iconWidth, iconHeight, marginTop, marginLeft, marginRight, marginBottom);


        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(attendance);
        pages.add(happy);

        setOnboardPages(pages);

        setGradientBackground();

    }


    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(OnBoarderActivity.this,DashboardActivity.class).putExtra("type",1));
        finish();
    }
}
