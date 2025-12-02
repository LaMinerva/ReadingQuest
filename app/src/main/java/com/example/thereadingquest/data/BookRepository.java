package com.example.thereadingquest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thereadingquest.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

        private final DatabaseHelper dbHelper;

        public BookRepository(Context context){
            this.dbHelper = new DatabaseHelper(context);
        }

        public long inserisciLibro(Book book, long userId){
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("user_id", userId);
            values.put("titolo", book.getTitolo());
            values.put("autore", book.getAutore());
            values.put("genere", book.getGenere());
            values.put("casa_editricce", book.getCasaEditrice());
            values.put("isbn", book.getIsbn());
            values.put("pagine_totali", book.getPagineTotali());

            long newId = db.insert(DatabaseHelper.TABLE_BOOKS, null, values);
            db.close();
            return newId;
        }

        public List<Book> getLibriPerUtente(long userId){
            List<Book> libri = new ArrayList<>();

            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] columns = {
                    "id",
                    "user_id",
                    "titolo",
                    "autore",
                    "genere",
                    "casa_editrice",
                    "isbn",
                    "pagine_totali"
            };

            String selection = "user_id = ?";
            String[] selectionArgs = { String.valueOf(userId) };

            Cursor cursor = db.query(
              DatabaseHelper.TABLE_BOOKS,
              columns,
              selection,
              selectionArgs,
              null,
              null,
              "titolo ASC"
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    Book book = new Book();
                    book.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
                    book.setUserId(cursor.getLong(cursor.getColumnIndexOrThrow("user_id")));
                    book.setTitolo(cursor.getString(cursor.getColumnIndexOrThrow("titolo")));
                    book.setAutore(cursor.getString(cursor.getColumnIndexOrThrow("autore")));
                    book.setGenere(cursor.getString(cursor.getColumnIndexOrThrow("genere")));
                    book.setCasaEditrice(cursor.getString(cursor.getColumnIndexOrThrow("casa_editrice")));
                    book.setIsbn(cursor.getString(cursor.getColumnIndexOrThrow("isbn")));
                    book.setPagineTotali(cursor.getInt(cursor.getColumnIndexOrThrow("pagine_totali")));

                    libri.add(book);
                }
                cursor.close();
            }

            db.close();
            return libri;
        }

        public Book getLibroPerId(long bookId){
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String [] columns = {
                    "id",
                    "user_id",
                    "titolo",
                    "genere",
                    "casa_editrice",
                    "isbn",
                    "pagine_totali",
            };

            String selection = "id = ?";
            String[] selectionArgs = { String.valueOf(bookId)};

            Cursor cursor = db.query(
                    DatabaseHelper.TABLE_BOOKS,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            Book book = null;

            if (cursor != null && cursor.moveToFirst()) {
                book = new Book();
                book.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
                book.setUserId(cursor.getLong(cursor.getColumnIndexOrThrow("user_id")));
                book.setTitolo(cursor.getString(cursor.getColumnIndexOrThrow("titolo")));
                book.setAutore(cursor.getString(cursor.getColumnIndexOrThrow("autore")));
                book.setGenere(cursor.getString(cursor.getColumnIndexOrThrow("genere")));
                book.setCasaEditrice(cursor.getString(cursor.getColumnIndexOrThrow("casa_editrice")));
                book.setIsbn(cursor.getString(cursor.getColumnIndexOrThrow("isbn")));
                book.setPagineTotali(cursor.getInt(cursor.getColumnIndexOrThrow("pagine_totali")));
            }

            if (cursor != null) {
                cursor.close();
            }

            db.close();
            return book;
        }

        public boolean aggiornaLibro(Book book){
            if (book.getId() <= 0) {
                return false;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("titolo", book.getTitolo());
            values.put("autore", book.getAutore());
            values.put("genere", book.getGenere());
            values.put("casa_editrice", book.getCasaEditrice());
            values.put("isbn", book.getIsbn());
            values.put("pagine_totali", book.getPagineTotali());

            String whereClause = "id = ?";
            String[] whereArgs = { String.valueOf(book.getId()) };

            int rowsUpdated = db.update(DatabaseHelper.TABLE_BOOKS, values, whereClause, whereArgs);
            db.close();

            return rowsUpdated > 0;
        }

        public boolean cancellaLibro(long bookId) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String whereClause = "id = ?";
            String[] whereArgs = { String.valueOf(bookId) };

            int rowsDeleted = db.delete(DatabaseHelper.TABLE_BOOKS, whereClause, whereArgs);
            db.close();

            return rowsDeleted > 0;
        }
}
