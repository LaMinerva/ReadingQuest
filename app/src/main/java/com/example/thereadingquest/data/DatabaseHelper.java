package com.example.thereadingquest.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "readingquest.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_BOOK_PROGRESS = "book_progress";
    public static final String TABLE_READING_TESTS = "reading_tests";
    public static final String TABLE_READING_TEST_PAGES = "reading_test_pages";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){

        String createUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "cognome TEXT," +
                "data_nascita TEXT," +
                "username TEXT UNIQUE," +
                "password TEXT," +
                "avg_reading_speed REAL," +
                "alter_ego_title TEXT," +
                "monete INTEGER DEFAULT 0," +
                "xp INTEGER DEFAULT 0," +
                "badge TEXT" +
                ");";

        String createBooks = "CREATE TABLE " + TABLE_BOOKS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "titolo TEXT," +
                "autore TEXT," +
                "genere TEXT," +
                "casa_editrice TEXT," +
                "isbn TEXT," +
                "pagine_totali INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id)" +
                ");";

        String createBookProgress = "CREATE TABLE " + TABLE_BOOK_PROGRESS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "book_id INTEGER NOT NULL," +
                "status TEXT," +
                "start_date TEXT," +
                "end_date TEXT," +
                "missione TEXT," +
                "xp_reward INTEGER," +
                "monete_reward INTEGER," +
                "pages_read INTEGER DEFAULT 0," +
                "FOREIGN KEY(book_id) REFERENCES " + TABLE_BOOKS +  "(id)" +
                ");";

        String createReadingTests = "CREATE TABLE " + TABLE_READING_TESTS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "tempo_totale_ms INTEGER," +
                "media_velocita REAL," +
                "created_at TEXT," +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id)" +
                ");";

        String createReadingTestPages =  "CREATE TABLE " + TABLE_READING_TEST_PAGES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "test_id INTEGER NOT NULL," +
                "page_index INTEGER," +
                "genere TEXT," +
                "tempo_impiegato_ms INTEGER," +
                "FOREIGN KEY(test_id) REFERENCES " + TABLE_READING_TESTS + "(id)" +
                ");";

        db.execSQL(createUsers);
        db.execSQL(createBooks);
        db.execSQL(createBookProgress);
        db.execSQL(createReadingTests);
        db.execSQL(createReadingTestPages);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_READING_TEST_PAGES);
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_READING_TESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }

    public long inserisciReadingTest(long userId, int paroleTotali, long tempoTotaleMs) {
        double minuti = tempoTotaleMs / 60000.0;
        double mediaVelocita = paroleTotali / minuti;

        String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .format(new Date());

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("tempo_totale_ms", tempoTotaleMs);
        values.put("media_velocita", mediaVelocita);
        values.put("created_at", createdAt);

        long newId = db.insert(TABLE_READING_TESTS, null, values);
        db.close();
        return newId;
    }

    public void aggiornaMediaLettura(long userId) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT AVG(media_velocita) AS avg_vel FROM " + TABLE_READING_TESTS + " WHERE user_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{ String.valueOf(userId) });

        double nuovaMedia = 0.0;

        if (cursor != null) {
            if (cursor.moveToFirst() && !cursor.isNull(0)) {
                nuovaMedia = cursor.getDouble(0);
            }
            cursor.close();
        }

        db.close();

        SQLiteDatabase dbWrite = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("avg_reading_speed", nuovaMedia);

        dbWrite.update(TABLE_USERS, values, "id = ?", new String[]{ String.valueOf(userId) });
        dbWrite.close();
    }

    public double leggiMediaLettura(long userId) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT avg_reading_speed FROM " + TABLE_USERS + " WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{ String.valueOf(userId) });

        double media = 0.0;

        if (cursor != null) {
            if (cursor.moveToFirst() && !cursor.isNull(0)) {
                media = cursor.getDouble(0);
            }
            cursor.close();
        }

        db.close();
        return media;
    }

}
