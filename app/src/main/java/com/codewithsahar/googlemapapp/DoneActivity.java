package com.codewithsahar.googlemapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoneActivity extends AppCompatActivity {

    TextView Thanks, Code_tv1, Code_tv2;
    Button Back_Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        Thanks = findViewById(R.id.thanks_tv);
        Code_tv1 = findViewById(R.id.code_tv1);
        Code_tv2 = findViewById(R.id.code_tv2);
        Back_Home = findViewById(R.id.back_btn);

        Back_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoneActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });




    }
}