package com.example.thereadingquest.ui.books;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.DatabaseHelper;
import com.example.thereadingquest.model.Book;
import com.example.thereadingquest.ui.books.BookMissionActivity;

public class BookDetailActivity extends AppCompatActivity {

    private long BookId;
    private DatabaseHelper dbHelper;

    private TextView txtTitolo, txtAutore, txtInfo;
    private Spinner spinnerStato;
    private Button btnSalvaStato, btnImpostaMissione;



}
