package com.example.thereadingquest.model;

public class ReadingTest {

    private long id;
    private long userId;
    private long tempoTotaleMs;
    private double mediaVelocita;
    private String creatoIl;

    public ReadingTest() {}

    public ReadingTest(long id, long userId, long tempoTotaleMs, double mediaVelocita, String creatoIl) {

        this.id = id;
        this.userId = userId;
        this.tempoTotaleMs = tempoTotaleMs;
        this.mediaVelocita = mediaVelocita;
        this.creatoIl = creatoIl;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public long getTempoTotaleMs() { return tempoTotaleMs; }
    public void setTempoTotaleMs(long tempoTotaleMs) { this.tempoTotaleMs = tempoTotaleMs; }
    public double getMediaVelocita() { return mediaVelocita; }
    public void setMediaVelocita(double mediaVelocita) { this.mediaVelocita = mediaVelocita; }
    public String getCreatoIl() { return creatoIl; }
    public void setCreatoIl(String creatoIl) { this.creatoIl = creatoIl; }
}
