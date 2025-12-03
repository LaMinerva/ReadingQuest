package com.example.thereadingquest.ui.books;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.BookRepository;
import com.example.thereadingquest.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity{

    private RecyclerView recyclerLibri;
    private Button btnAggiungiLibro;

    private BookAdapter bookAdapter;
    private BookRepository bookRepository;

    private long currentUserId = -1;

    private static final String PREFS_NAME = "readingquest_prefs";
    private static final String KEY_CURRENT_USER_ID = "current_user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

       bookRepository = new BookRepository(this);

       recyclerLibri = findViewById(R.id.recyclerLibri);
       btnAggiungiLibro = findViewById(R.id.btnAggiungiLibro);

       recyclerLibri.setLayoutManager(new LinearLayoutManager(this));
       bookAdapter = new BookAdapter(new ArrayList<>());
       recyclerLibri.setAdapter(bookAdapter);

       currentUserId = leggiUtenteCorrente();

       caricaLibri();

       btnAggiungiLibro.setOnClickListener(v -> {
           Intent intent = new Intent(BooksActivity.this, AddBookActivity.class);
           startActivity(intent);
       });
    }

    @Override
    protected void onResume(){
        super.onResume();
        caricaLibri();
    }

    private long leggiUtenteCorrente() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(KEY_CURRENT_USER_ID, -1);
    }

    private void caricaLibri() {
        if(currentUserId == -1){
            return;
        }

        List<Book> libri = bookRepository.getLibriPerUtente(currentUserId);
        bookAdapter.setLibri(libri);
    }
}
