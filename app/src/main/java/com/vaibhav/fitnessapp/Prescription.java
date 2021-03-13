package com.vaibhav.fitnessapp;

import java.util.ArrayList;

public class Prescription {
    private String patientUid;
    private String morningMeds;
    private String afterBreakfastMeds;
    private String afterLunchMeds;
    private String eveningMeds;
    private String nightMeds;
    private int status;
    private String Id;
    private int orderValue;
    private String uidChemist;

    public Prescription() {
        this.patientUid = null;
        this.morningMeds = null;
        this.afterBreakfastMeds = null;
        this.afterLunchMeds = null;
        this.eveningMeds = null;
        this.nightMeds = null;
        this.status = 0;
        this.orderValue = 0;
        this.Id = null;
        this.uidChemist = null;
    }

    public Prescription(String patientUid, String morningMeds, String afterBreakfastMeds, String afterLunchMeds, String eveningMeds, String nightMeds, int status, String Id, int orderValue, String uidChemist) {
        this.patientUid = patientUid;
        this.morningMeds = morningMeds;
        this.afterBreakfastMeds = afterBreakfastMeds;
        this.afterLunchMeds = afterLunchMeds;
        this.eveningMeds = eveningMeds;
        this.nightMeds = nightMeds;
        this.status = status;
        this.orderValue = orderValue;
        this.Id = Id;
        this.uidChemist = uidChemist;
    }

    public String getMorningMeds() {
        return morningMeds;
    }

    public void setMorningMeds(String morningMeds) {
        this.morningMeds = morningMeds;
    }

    public String getAfterBreakfastMeds() {
        return afterBreakfastMeds;
    }

    public void setAfterBreakfastMeds(String afterBreakfastMeds) {
        this.afterBreakfastMeds = afterBreakfastMeds;
    }

    public String getAfterLunchMeds() {
        return afterLunchMeds;
    }

    public void setAfterLunchMeds(String afterLunchMeds) {
        this.afterLunchMeds = afterLunchMeds;
    }

    public String getEveningMeds() {
        return eveningMeds;
    }

    public void setEveningMeds(String eveningMeds) {
        this.eveningMeds = eveningMeds;
    }

    public String getNightMeds() {
        return nightMeds;
    }

    public void setNightMeds(String nightMeds) {
        this.nightMeds = nightMeds;
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(int orderValue) {
        this.orderValue = orderValue;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUidChemist() {
        return uidChemist;
    }

    public void setUidChemist(String uidChemist) {
        this.uidChemist = uidChemist;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "patientUid='" + patientUid + '\'' +
                ", morningMeds='" + morningMeds + '\'' +
                ", afterBreakfastMeds='" + afterBreakfastMeds + '\'' +
                ", afterLunchMeds='" + afterLunchMeds + '\'' +
                ", eveningMeds='" + eveningMeds + '\'' +
                ", nightMeds='" + nightMeds + '\'' +
                ", status=" + status +
                ", Id='" + Id + '\'' +
                ", orderValue=" + orderValue +
                ", uidChemist='" + uidChemist + '\'' +
                '}';
    }
}
