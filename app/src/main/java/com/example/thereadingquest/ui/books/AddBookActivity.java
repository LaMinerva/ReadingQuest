package com.example.thereadingquest.ui.books;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.BookRepository;
import com.example.thereadingquest.model.Book;

public class AddBookActivity extends AppCompatActivity {
    private EditText edtTitoloLibro;
    private EditText edtAutoreLibro;
    private EditText edtGenereLibro;
    private EditText edtCasaEditriceLibro;
    private EditText edtIsbnLibro;
    private EditText edtPagineTotaliLibro;
    private Button btnSalvaLibro;

    private BookRepository bookRepository;

    private static final String PREFS_NAME = "readingquest_prefs";
    private static final String KEY_CURRENT_USER_ID = "current_user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        bookRepository = new BookRepository(this);

        edtTitoloLibro = findViewById(R.id.edtTitoloLibro);
        edtAutoreLibro = findViewById(R.id.edtAutoreLibro);
        edtGenereLibro = findViewById(R.id.edtGenereLibro);
        edtCasaEditriceLibro = findViewById(R.id.edtCasaEditriceLibro);
        edtIsbnLibro = findViewById(R.id.edtIsbnLibro);
        edtPagineTotaliLibro = findViewById(R.id.edtPagineTotaliLibro);
        btnSalvaLibro = findViewById(R.id.btnSalvaLibro);

        btnSalvaLibro.setOnClickListener(v -> salvaLibro());
    }

    private long leggiUtenteCorrente() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(KEY_CURRENT_USER_ID, -1);
    }

    private void salvaLibro() {
        long userId = leggiUtenteCorrente();
        if (userId == -1) {
            Toast.makeText(this, "Nessun utente loggato, impossibile salvare il libro", Toast.LENGTH_SHORT).show();
            return;
        }

        String titolo = edtTitoloLibro.getText().toString().trim();
        String autore = edtAutoreLibro.getText().toString().trim();
        String genere = edtGenereLibro.getText().toString().trim();
        String casaEditrice = edtCasaEditriceLibro.getText().toString().trim();
        String isbn = edtIsbnLibro.getText().toString().trim();
        String pagineStr = edtPagineTotaliLibro.getText().toString().trim();

        if (titolo.isEmpty() || autore.isEmpty() || pagineStr.isEmpty()) {
            Toast.makeText(this, "Titolo, autore e pagine sono obbligatori", Toast.LENGTH_SHORT).show();
            return;
        }

        int pagineTotali;
        try {
            pagineTotali = Integer.parseInt(pagineStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Numero pagine non valido", Toast.LENGTH_SHORT).show();
            return;
        }

        Book book = new Book();
        book.setTitolo(titolo);
        book.setAutore(autore);
        book.setGenere(genere);
        book.setCasaEditrice(casaEditrice);
        book.setIsbn(isbn);
        book.setPagineTotali(pagineTotali);
        book.setUserId(userId);

        long newId = bookRepository.inserisciLibro(book, userId);

        if (newId == -1) {
            Toast.makeText(this, "Errore nel salvataggio del libro", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Libro salvato", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
