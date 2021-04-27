package com.codewithsahar.googlemapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {

    TextView Name1, Name2, Email1, Email2, City1, City2, Age1, Age2, Gender1, Gender2, Subject;
    Button Edit;
    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Subject = findViewById(R.id.activity_name_tv);
        Name1 = findViewById(R.id.Username_tv1);
        Name2 = findViewById(R.id.Username_tv2);
        Email1 = findViewById(R.id.email_tv1);
        Email2 = findViewById(R.id.email_tv2);
        City1 = findViewById(R.id.City_tv1);
        City2 = findViewById(R.id.City_tv2);
        Age1 = findViewById(R.id.Age_tv1);
        Age2 = findViewById(R.id.Age_tv2);
        Gender1 = findViewById(R.id.Gender_tv1);
        Gender2 = findViewById(R.id.Gender_tv2);
        Back = findViewById(R.id.back_btn);
        Edit = findViewById(R.id.edit_btn);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name_key");
        String email = intent.getStringExtra("email_key");
        String city = intent.getStringExtra("city_key");
        String age = intent.getStringExtra("age_key");
        String gender = intent.getStringExtra("gender_key");

        Name2.setText(name);
        Email2.setText(email);
        City2.setText(city);
        Age2.setText(age);
        Gender2.setText(gender);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
/*
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


 */
        Subject.setText("My Profile");

    }
}