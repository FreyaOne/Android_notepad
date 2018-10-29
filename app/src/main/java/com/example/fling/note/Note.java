package com.example.fling.note;

public class Note {
    private String id;
    private String title;
    private String con;
    private String time;

    public Note(){

    }

    public Note(String title, String time){
        this.title = title;
        this.time = time;
    }

    public Note(String id, String title, String con){
            this.id = id;
            this.title = title;
            this.con = con;
    }


    public Note(String id, String title, String con, String time){
        this.id = id;
        this.title = title;
        this.con = con;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(int position) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return con;
    }

    public void setContext(String context) {
        this.con = con;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
