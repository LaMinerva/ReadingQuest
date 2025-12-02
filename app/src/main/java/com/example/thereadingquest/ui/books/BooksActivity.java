package com.example.thereadingquest.ui.books;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
public class BooksActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        TextView txt = findViewById(R.id.txtBooksPlaceholder);
        txt.setText("Sezione libri");
    }
}
