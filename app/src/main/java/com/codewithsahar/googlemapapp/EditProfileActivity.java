package com.codewithsahar.googlemapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditProfileActivity extends AppCompatActivity {

    TextView Name1, Email1, City1, Age1, Gender1;
    EditText Name2, Email2, City2, Age2, Gender2;
    Button Save;
    ImageView UserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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
        UserImage = findViewById(R.id.User_img);
        Save = findViewById(R.id.save_btn);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name2.getText().toString();
                String email = Email2.getText().toString();
                String city = City2.getText().toString();
                String age = Age2.getText().toString();
                String gender = Gender2.getText().toString();

                Intent intent = new Intent(EditProfileActivity.this, MyProfileActivity.class);

                intent.putExtra("name_key", name);
                intent.putExtra("email_key", email);
                intent.putExtra("city_key", city);
                intent.putExtra("age_key", age);
                intent.putExtra("gender_key", gender);

                startActivity(intent);
            }
        });

        UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}