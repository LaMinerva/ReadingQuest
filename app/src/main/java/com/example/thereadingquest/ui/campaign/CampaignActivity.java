package com.example.thereadingquest.ui.campaign;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.DatabaseHelper;

public class CampaignActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        TextView txt = findViewById(R.id.txtCampaignPlaceholder);

        DatabaseHelper db = new DatabaseHelper(this);
        int missioniAttive = db.contaMissioniAttive();

        txt.setText("Missioni attive: " + missioniAttive);
    }
}
