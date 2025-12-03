package com.example.thereadingquest.ui.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ReadingTestActivity extends AppCompatActivity {

    private TextView txtPagina;
    private Button btnStart;
    private Button btnAvanti;
    private Chronometer cronometro;

    private int paginaCorrente = 0;
    private List<String> testi = new ArrayList<>();
    private long tempoImpiegatoTotale = 0;

    private long tempoInizio;
    private DatabaseHelper dbHelper;

    private static final String PREFS_NAME = "readingquest_prefs";
    private static final String KEY_CURRENT_USER_ID = "current_user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test);

        txtPagina = findViewById(R.id.txtPaginaTest);
        btnStart = findViewById(R.id.btnStartTest);
        btnAvanti = findViewById(R.id.btnAvantiTest);
        cronometro = findViewById(R.id.cronometro);

        dbHelper = new DatabaseHelper(this);

        preparaTesti();

        mostraPaginaCorrente();

        btnStart.setOnClickListener(v -> {
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();
            tempoInizio = SystemClock.elapsedRealtime();
            btnStart.setEnabled(false);
        });

        btnAvanti.setOnClickListener(v -> {
            if (btnStart.isEnabled()) {
                Toast.makeText(this, "Premi START", Toast.LENGTH_SHORT).show();
                return;
            }

            long tempoPagina = SystemClock.elapsedRealtime() - tempoInizio;
            tempoImpiegatoTotale += tempoPagina;

            paginaCorrente++;

            if (paginaCorrente >= testi.size()){
                concludeTest();
            } else {
                mostraPaginaCorrente();
                btnStart.setEnabled(true);
                cronometro.stop();
                cronometro.setBase(SystemClock.elapsedRealtime());
            }
        });
    }

    private void preparaTesti() {
        testi.add("Pagina 1: Lorem ipsum dolor sit amet...");
        testi.add("Pagina 2: Nulla facilisi. Sed id sapien...");
        testi.add("Pagina 3: Lorem ipsum dolor sit amet...");
        testi.add("Pagina 4: Nulla facilisi. Sed id sapien...");
        testi.add("Pagina 5: Lorem ipsum dolor sit amet...");
    }

    private void mostraPaginaCorrente() {
        txtPagina.setText(testi.get(paginaCorrente));
    }

    private void concludeTest() {
        cronometro.stop();

        long userId = leggiUtenteCorrente();
        if (userId == -1) {
            Toast.makeText(this, "Errore: nessun utente loggato", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int paroleTotali = calcolaParoleTotali();

        double minuti = tempoImpiegatoTotale / 60000.0;
        int wpm = (int) (paroleTotali / minuti);

        dbHelper.inserisciReadingTest(userId, paroleTotali, tempoImpiegatoTotale);
        dbHelper.aggiornaMediaLettura(userId);

        double mediaGlobale = dbHelper.leggiMediaLettura(userId);
        int mediaArrontondata = (int) mediaGlobale;

        String messaggio = "TEST COMPLETATO: \n" + "Velocità test -> " +  wpm + "wpm \n" + "Media complessiva ->" + mediaArrontondata + "wpm";

        Toast.makeText(this, "Test completato: VELOCITA': " + wpm + " wpm", Toast.LENGTH_LONG).show();

        finish();
    }

    private int calcolaParoleTotali(){
        int parole = 0;
        for (String pagina : testi) {
            parole += pagina.trim().split("\\s+").length;
        }
        return parole;
    }

    private long leggiUtenteCorrente() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(KEY_CURRENT_USER_ID, -1);
    }
}
