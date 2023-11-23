package com.example.mrbarberadmin.model;

public class TimeSlot {

    private int id;
    private String time;
    private boolean available;

    public TimeSlot(){}

    public TimeSlot(String time, boolean available){
        this.time = time;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}