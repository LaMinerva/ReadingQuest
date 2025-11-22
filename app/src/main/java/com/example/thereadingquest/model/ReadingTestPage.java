package com.example.thereadingquest.model;

public class ReadingTestPage {

    private long id;
    private long testId;
    private int pageIndex;
    private String genere;
    private long tempoImpiegatoMs;

    public ReadingTestPage() {}

    public ReadingTestPage(long id, long testId, int pageIndex, String genere, long tempoImpiegatoMs) {

        this.id = id;
        this.testId = testId;
        this.pageIndex = pageIndex;
        this.genere = genere;
        this.tempoImpiegatoMs = tempoImpiegatoMs;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getTestId() { return testId; }
    public void setTestId(long testId) { this.testId = testId; }
    public int getPageIndex() { return pageIndex; }
    public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; }
    public String getGenere() { return genere; }
    public void setGenere(String genere) { this.genere = genere; }
    public long getTempoImpiegatoMs() { return tempoImpiegatoMs; }
    public void setTempoImpiegatoMs(long tempoImpiegatoMs) { this.tempoImpiegatoMs = tempoImpiegatoMs; }
}
