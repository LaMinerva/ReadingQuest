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

    private long bookId;
    private DatabaseHelper dbHelper;

    private TextView txtTitolo, txtAutore, txtInfo;
    private Spinner spinnerStato;
    private Button btnSalvaStato, btnImpostaMissione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        dbHelper = new DatabaseHelper(this);

        bookId = getIntent().getLongExtra("book_id", -1);
        if(bookId == -1) {
            Toast.makeText(this, "Errore: libro non valido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        txtTitolo = findViewById(R.id.txtTitoloLibro);
        txtAutore = findViewById(R.id.txtAutoreLibro);
        txtInfo = findViewById(R.id.txtInfoLibro);
        spinnerStato = findViewById(R.id.spinnerStato);
        btnSalvaStato = findViewById(R.id.btnSalvaStato);
        btnImpostaMissione = findViewById(R.id.btnImpostaMissione);

        Book libro = dbHelper.getLibroPerId(bookId)
    }

}
