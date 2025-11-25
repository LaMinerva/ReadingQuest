package com.example.thereadingquest.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.UserRepository;
import com.example.thereadingquest.model.User;
import com.example.thereadingquest.MainActivity;

public class LoginActivity extends AppCompatActivity{

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnAccedi;
    private Button btnVaiARegistrazione;

    private UserRepository userRepository;

    private static final String PREFS_NAME = "readingquest_prefs";
    private static final String KEY_CURRENT_USER_ID = "current_user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = new UserRepository(this);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnAccedi = findViewById(R.id.btnAccedi);
        btnVaiARegistrazione = findViewById(R.id.btnVaiARegistrazione);

        btnAccedi.setOnClickListener(v -> effettuaLogin());

        btnVaiARegistrazione.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void effettuaLogin(){
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Inserisci username e password", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = userRepository.trovaUtentePerLogin(username, password);

        if (user == null) {
            Toast.makeText(this, "Credenziali non valide", Toast.LENGTH_SHORT).show();
        } else {
            salvaUtenteCorrente(user.getId());

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void salvaUtenteCorrente(long userId) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .putLong(KEY_CURRENT_USER_ID, userId)
                .apply();
    }

}
