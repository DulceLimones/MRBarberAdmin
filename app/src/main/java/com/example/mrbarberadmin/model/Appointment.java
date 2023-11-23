package com.example.mrbarberadmin.model;

public class Appointment {
    private int id;
    private int clientId;
    private int serviceId;
    private int barberId;
    private String timeSlot;
    private String date;

    public Appointment(){}

    public Appointment(int clientId, int serviceId, int barberId) {
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.barberId = barberId;
    }

    public Appointment(int clientId, int serviceId, int barberId, String timeSlot, String date) {
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.barberId = barberId;
        this.timeSlot = timeSlot;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
