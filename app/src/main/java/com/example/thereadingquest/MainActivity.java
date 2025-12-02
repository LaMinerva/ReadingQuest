package com.example.thereadingquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.ui.campaign.CampaignActivity;
import com.example.thereadingquest.ui.books.BooksActivity;
import com.example.thereadingquest.ui.test.ReadingTestIntroActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLibri;
    private Button btnCampagna;
    private Button btnTestLettura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLibri = findViewById(R.id.btnLibri);
        btnCampagna = findViewById(R.id.btnCampagna);
        btnTestLettura = findViewById(R.id.btnTestLettura);

        btnLibri.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BooksActivity.class);
            startActivity(intent);
        });

        btnCampagna.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CampaignActivity.class);
            startActivity(intent);
        });

        btnTestLettura.setOnClickListener(v -> {
           Intent intent = new Intent(MainActivity.this, ReadingTestIntroActivity.class);
           startActivity(intent);
        });
    }
}