package com.example.thereadingquest.model;

public class User {

    private long id;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String username;
    private String password;
    private double avgReadingSpeed;
    private String alterEgoTitle;
    private int monete;
    private int xp;
    private String badge;

    public User(){}

    public User(long id, String nome, String cognome, String dataNascita, String username, String password, double avgReadingSpeed, String alterEgoTitle, int monete, int xp, String badge) {

        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.username = username;
        this.password = password;
        this.avgReadingSpeed = avgReadingSpeed;
        this.alterEgoTitle = alterEgoTitle;
        this.monete = monete;
        this.xp = xp;
        this.badge = badge;
    }

    public long getId(){ return id; }
    public void setId(long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getDataNascita() { return dataNascita; }
    public void setDataNascita(String dataNascita) { this.dataNascita = dataNascita; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public double getAvgReadingSpeed() { return avgReadingSpeed; }
    public void setAvgReadingSpeed(double avgReadingSpeed) { this.avgReadingSpeed = avgReadingSpeed; }
    public String getAlterEgoTitle() { return alterEgoTitle; }
    public void setAlterEgoTitle(String alterEgoTitle) { this.alterEgoTitle = alterEgoTitle; }
    public int getMonete() {return monete; }
    public void setMonete(int monete) { this.monete = monete; }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public String getBadge() { return badge; }
    public void setBadge(String badge) { this.badge = badge; }

}
