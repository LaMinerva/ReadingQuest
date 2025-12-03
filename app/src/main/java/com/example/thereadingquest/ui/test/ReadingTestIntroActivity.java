package com.example.thereadingquest.ui.test;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;

public class ReadingTestIntroActivity extends AppCompatActivity {

    private Button btnIniziaTest;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_intro);

        btnIniziaTest = findViewById(R.id.btnIniziaTest);

        btnIniziaTest.setOnClickListener(v -> {
            Intent intent = new Intent(ReadingTestIntroActivity.this, ReadingTestActivity.class);
            startActivity(intent);
        });
    }
}
