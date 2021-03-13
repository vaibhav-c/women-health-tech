package com.vaibhav.fitnessapp;

public class Cycle {
    private String dateStart;
    private String endDate;
    private boolean pads;
    private String uid;
    private String id;

    public Cycle() {
        this.dateStart = null;
        this.endDate = null;
        this.pads = false;
        this.uid = null;
        this.id = null;
    }

    public Cycle(String dateStart, String endDate, String id, boolean pads, String uid) {
        this.dateStart = dateStart;
        this.endDate = endDate;
        this.pads = pads;
        this.uid = uid;
        this.id = id;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public boolean isPads() {
        return pads;
    }

    public void setPads(boolean pads) {
        this.pads = pads;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "dateStart='" + dateStart + '\'' +
                ", endDate='" + endDate + '\'' +
                ", pads=" + pads +
                ", uid='" + uid + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
