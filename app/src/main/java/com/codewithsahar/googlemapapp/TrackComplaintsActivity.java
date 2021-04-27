package com.codewithsahar.googlemapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TrackComplaintsActivity extends AppCompatActivity {

    TextView Enter, Subject;
    EditText Code;
    Button Track;
    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_complaints);

        Enter = findViewById(R.id.subject_tv);
        Code = findViewById(R.id.code_et);
        Track = findViewById(R.id.track_btn);
        Subject = findViewById(R.id.activity_name_tv);
        Subject.setText("Track Complaints");
        Back = findViewById(R.id.back_btn);
        /*
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackComplaintsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

         */

    }
}