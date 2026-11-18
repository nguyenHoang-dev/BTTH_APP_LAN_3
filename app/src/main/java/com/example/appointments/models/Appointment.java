package com.example.appointments.models;

public class Appointment {
    private int id;
    private String title;
    private String location;
    private String date; // yyyy-MM-dd
    private String time; // HH:mm
    private int reminder; // 1 for true, 0 for false

    public Appointment(int id, String title, String location, String date, String time, int reminder) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.reminder = reminder;
    }

    public Appointment() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public int getReminder() { return reminder; }
    public void setReminder(int reminder) { this.reminder = reminder; }
}
