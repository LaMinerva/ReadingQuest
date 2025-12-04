package com.example.thereadingquest.ui.books;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.BookRepository;
import com.example.thereadingquest.data.DatabaseHelper;
import com.example.thereadingquest.model.Book;

import java.util.Calendar;

public class BookMissionActivity extends AppCompatActivity {

    private TextView txtTitolo, txtAutore, txtTempoStimato;
    private EditText edtXP, edtMonete, edtDeadline;
    private Button btnAssegna;

    private long bookId;
    private DatabaseHelper db;
    private BookRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mission);

        db = new DatabaseHelper(this);
        repo = new BookRepository(this);

        bookId = getIntent().getLongExtra("book_id", -1);

        txtTitolo = findViewById(R.id.txtMissionTitle);
        txtAutore = findViewById(R.id.txtMissionAuthor);
        txtTempoStimato = findViewById(R.id.txtMissionTime);

        edtXP = findViewById(R.id.edtXPReward);
        edtMonete = findViewById(R.id.edtMoneyReward);
        edtDeadline = findViewById(R.id.edtDeadline);

        btnAssegna = findViewById(R.id.btnAssegnaMissione);

        Book libro = repo.getLibroPerId(bookId);
        if (libro == null) {
            Toast.makeText(this, "Errore: libro non trovato", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        txtTitolo.setText(libro.getTitolo());
        txtAutore.setText("di " + libro.getAutore());

        double avgSpeed = db.leggiMediaLettura(libro.getUserId());
        double minTot = repo.calcolaTempoStimato(libro.getPagineTotali(), avgSpeed);

        int ore = (int)(minTot / 60);
        int minuti = (int)(minTot % 60);

        txtTempoStimato.setText("Tempo stimato: " + ore +  "h " + minuti +"m");

        edtDeadline.setOnClickListener(v -> apriDatePicker());

        btnAssegna.setOnClickListener(v -> assegnaMissione(libro));
    }

    private void apriDatePicker() {
        Calendar c = Calendar.getInstance();

        new DatePickerDialog(this, (view, y, m, d) -> {
            edtDeadline.setText(d + "/" + (m + 1) + "/" + y);
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void assegnaMissione(Book libro) {
        String xpStr = edtXP.getText().toString().trim();
        String moneteStr = edtMonete.getText().toString().trim();
        String deadline = edtDeadline.getText().toString().trim();

        if (xpStr.isEmpty() || moneteStr.isEmpty() || deadline.isEmpty()) {
            Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        int xp = Integer.parseInt(xpStr);
        int monete = Integer.parseInt(moneteStr);

        db.creaMissione(libro.getUserId(), libro.getId(), xp, monete, deadline);

        Toast.makeText(this, "Missione assegnata", Toast.LENGTH_LONG).show();
        finish();
    }
}




/*
        TextView txt = findViewById(R.id.txtMissionTitle);
        if (txt != null) {
            txt.setText("Missione libro");
        }

         */