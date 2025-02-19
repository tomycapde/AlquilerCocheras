package com.example.alquilercocheras.models;

import java.util.Date;

public class Stay {

    private int idStay;
    private int idVehicle;
    private Date startDate;
    private Date endDate;
    private boolean payed;

    public Stay() {
    }

    public int getIdStay() {
        return idStay;
    }

    public void setIdStay(int idStay) {
        this.idStay = idStay;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
