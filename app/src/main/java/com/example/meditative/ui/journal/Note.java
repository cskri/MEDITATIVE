package com.example.meditative.ui.journal;

public class Note {
    private Long ID;
    private int mood;
    private String content;
    private String date;
    private String time;

    public Note(){

    }
    public Note(int mood, String content, String date, String time){
        this.mood = mood;
        this.content = content;
        this.date = date;
        this.time = time;
    }
    public Note(long id, int mood, String content, String date, String time){
        this.ID = id;
        this.mood = mood;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
