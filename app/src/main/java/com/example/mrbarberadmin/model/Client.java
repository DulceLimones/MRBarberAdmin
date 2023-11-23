package com.example.mrbarberadmin.model;

public class Client {

    private int id;
    private String firstName;
    private String lastName;
    private int phone;
    private int distiguished;
    private String lastVisit;
    private int amountVisits;

    public Client(){}
    public Client(String firstName, String lastName,int phone, int distiguished) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.distiguished = distiguished;
    }

    public Client(String firstName, String lastName,int phone, int distiguished, String lastVisit, int amountVisits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.distiguished = distiguished;
        this.lastVisit = lastVisit;
        this.amountVisits = amountVisits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int isDistiguished() {
        return distiguished;
    }

    public void setDistiguished(int distiguished) {
        this.distiguished = distiguished;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getAmountVisits() {
        return amountVisits;
    }

    public void setAmountVisits(int amountVisits) {
        this.amountVisits = amountVisits;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String toString(){
        return firstName+" "+lastName;
    }
}
