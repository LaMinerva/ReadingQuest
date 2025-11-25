package com.example.thereadingquest.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thereadingquest.R;
import com.example.thereadingquest.data.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCognome;
    private EditText edtUsernameReg;
    private EditText edtPasswordReg;
    private EditText edtConfermaPasswordReg;
    private Button btnRegistrati;

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRepository = new UserRepository(this);

        edtNome = findViewById(R.id.edtNome);
        edtCognome = findViewById(R.id.edtCognome);
        edtUsernameReg = findViewById(R.id.edtUsernameReg);
        edtPasswordReg = findViewById(R.id.edtPasswordReg);
        edtConfermaPasswordReg = findViewById(R.id.edtConfermaPasswordReg);
        btnRegistrati = findViewById(R.id.btnRegistrati);

        btnRegistrati.setOnClickListener(v -> registraNuovoUtente());
    }

    private void registraNuovoUtente() {
        String nome = edtNome.getText().toString().trim();
        String cognome = edtCognome.getText().toString().trim();
        String username = edtUsernameReg.getText().toString().trim();
        String password = edtPasswordReg.getText().toString().trim();
        String confermaPassword = edtConfermaPasswordReg.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || confermaPassword.isEmpty()){
            Toast.makeText(this, "Username e password sono obbligatori", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confermaPassword)){
            Toast.makeText(this, "Le password non coincidono", Toast.LENGTH_SHORT).show();
            return;
        }

        long newId = userRepository.creaUtente(nome, cognome, username, password);

        if (newId == -1) {
            Toast.makeText(this, "ERRORE: Username già in uso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registrazione completata!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
