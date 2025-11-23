package com.example.thereadingquest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thereadingquest.model.User;
public class UserRepository {

    private final DatabaseHelper dbHelper;

    public UserRepository(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public long creaUtente(String nome, String cognome, String username, String password) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("cognome", cognome);
        values.put("username", username);
        values.put("password", password);

        long newId = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        return newId;
    }

    public User trovaUtentePerLogin(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                "id", "nome", "cognome", "data_nascita", "username", "password",
                "avg_reading_speed", "alter_ego_title", "monete", "xp", "badge"
        };

        String selection = "username = ? AND password  = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        User user = null;

        if (cursor != null && cursor.moveToFirst()){
            user = new User();
            user.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
            user.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            user.setCognome(cursor.getString(cursor.getColumnIndexOrThrow("cognome")));
            user.setDataNascita(cursor.getString(cursor.getColumnIndexOrThrow("data_nascita")));
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
            user.setAvgReadingSpeed(cursor.getDouble(cursor.getColumnIndexOrThrow("avg_reading_speed")));
            user.setMonete(cursor.getInt(cursor.getColumnIndexOrThrow("monete")));
            user.setXp(cursor.getInt(cursor.getColumnIndexOrThrow("xp")));
            user.setBadge(cursor.getString(cursor.getColumnIndexOrThrow("badge")));
        }

        if (cursor != null){
            cursor.close();
        }
        db.close();

        return user;
    }

}
