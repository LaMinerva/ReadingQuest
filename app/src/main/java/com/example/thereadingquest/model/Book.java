package com.example.thereadingquest.model;

public class Book {

    private long id;
    private long userId;
    private String titolo;
    private String autore;
    private String genere;
    private String casaEditrice;
    private String isbn;
    private int pagineTotali;

    public Book() {}

    public Book(long id, long userId, String titolo, String autore, String genere, String casaEditrice, String isbn, int pagineTotali) {

        this.id = id;
        this.userId = userId;
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.casaEditrice = casaEditrice;
        this.isbn = isbn;
        this.pagineTotali = pagineTotali;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getUserId() { return userId; }
    public void setUserId( long userId) { this.userId = userId; }
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public String getAutore() { return autore; }
    public void setAutore(String autore) { this.autore = autore; }
    public String getGenere() { return genere; }
    public void setGenere(String genere) { this.genere = genere; }
    public String getCasaEditrice() { return casaEditrice; }
    public void setCasaEditrice(String casaEditrice) { this.casaEditrice = casaEditrice; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public int getPagineTotali() { return pagineTotali; }
    public void setPagineTotali(int pagineTotali) { this.pagineTotali = pagineTotali; }
}
