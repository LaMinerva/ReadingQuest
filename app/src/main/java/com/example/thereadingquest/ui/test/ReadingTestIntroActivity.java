package com.example.thereadingquest.ui.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;

public class ReadingTestIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_intro);

        TextView txt = findViewById(R.id.txtTestIntroPlaceholder);
        txt.setText("Test di lettura");
    }
}
