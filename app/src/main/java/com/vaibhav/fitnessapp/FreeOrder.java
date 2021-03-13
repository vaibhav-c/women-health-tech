package com.vaibhav.fitnessapp;

public class FreeOrder {
    private int OTP;
    private String orderTime;
    private String deliveryTime;
    private String uidChemist;
    private String uid;

    public FreeOrder(int OTP, String orderTime, String deliveryTime, String uidChemist, String uid) {
        this.OTP = OTP;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.uidChemist = uidChemist;
        this.uid = uid;
    }

    public FreeOrder() {
        this.OTP = 0;
        this.orderTime = "";
        this.deliveryTime = "";
        this.uidChemist = "";
        this.uid = "";
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getUidChemist() {
        return uidChemist;
    }

    public void setUidChemist(String uidChemist) {
        this.uidChemist = uidChemist;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "FreeOrder{" +
                "OTP=" + OTP +
                ", orderTime=" + orderTime +
                ", deliveryTime=" + deliveryTime +
                ", uidChemist='" + uidChemist + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
