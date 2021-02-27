package com.apps.kunalfarmah.omrscanner;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StartActivity extends AppCompatActivity {

    Button scan,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        scan = findViewById(R.id.scan);
        answer = findViewById(R.id.setAnswers);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,MainActivity.class));
            }
        });


        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, AnswersActivity.class));
            }
        });
    }
}