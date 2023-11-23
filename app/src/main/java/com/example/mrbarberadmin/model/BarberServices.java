package com.example.mrbarberadmin.model;

public class BarberServices {
    private int barberId;
    private int serviceId;

    public  BarberServices(){}

    public  BarberServices(int barberId, int serviceId){
        this.barberId = barberId;
        this.serviceId = serviceId;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId ) {
        this.barberId = barberId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}