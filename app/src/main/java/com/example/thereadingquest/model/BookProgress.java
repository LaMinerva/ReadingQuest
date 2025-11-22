package com.example.thereadingquest.model;

public class BookProgress {

    private long id;
    private long bookId;
    private String status;
    private String startDate;
    private String endDate;
    private String missione;
    private int xpReward;
    private int moneteReward;
    private int pagesRead;

    public BookProgress() {}

    public BookProgress(long id, long bookId, String status, String startDate, String endDate, String missione, int xpReward, int moneteReward, int pagesRead) {

        this.id = id;
        this.bookId = bookId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.missione = missione;
        this.xpReward = xpReward;
        this.moneteReward = moneteReward;
        this.pagesRead = pagesRead;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getBookI() { return bookId; }
    public void setBookId(long bookId) { this.bookId = bookId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate;}
    public String getMissione() { return missione; }
    public void setMissione(String missione) { this.missione = missione; }
    public int getXpReward() { return xpReward; }
    public void setXpReward(int xpReward) { this.xpReward = xpReward; }
    public int getMoneteReward() { return moneteReward; }
    public void setMoneteReward(int moneteReward) { this.moneteReward = moneteReward; }
    public int getPagesRead() { return pagesRead; }
    public void setPagesRead(int pagesRead) { this.pagesRead = pagesRead; }
}
