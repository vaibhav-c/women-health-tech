package com.vaibhav.fitnessapp;

public class Chemist {
    private String name;
    private String address;
    private String emailId;
    private String phone;
    private String password;
    private String state;
    private String city;
    private String uidChemist;
    private int moneyOwed;
    private int moneyReceived;

    public Chemist() {
        this.name = null;
        this.address = null;
        this.emailId = null;
        this.phone = null;
        this.password = null;
        this.state = null;
        this.city = null;
        this.uidChemist = null;
        this.moneyOwed = 0;
        this.moneyReceived = 0;
    }

    public Chemist(String name, String address, String emailId, String phone, String password, String state, String city, String uidDoctor, int moneyOwed, int moneyReceived) {
        this.name = name;
        this.address = address;
        this.emailId = emailId;
        this.phone = phone;
        this.password = password;
        this.state = state;
        this.city = city;
        this.uidChemist = uidDoctor;
        this.moneyReceived = moneyReceived;
        this.moneyOwed = moneyOwed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUidChemist() {
        return uidChemist;
    }

    public void setUidChemist(String uidChemist) {
        this.uidChemist = uidChemist;
    }

    public int getMoneyOwed() {
        return moneyOwed;
    }

    public void setMoneyOwed(int moneyOwed) {
        this.moneyOwed = moneyOwed;
    }

    public int getMoneyReceived() {
        return moneyReceived;
    }

    public void setMoneyReceived(int moneyReceived) {
        this.moneyReceived = moneyReceived;
    }

    @Override
    public String toString() {
        return "Chemist{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", uidChemist='" + uidChemist + '\'' +
                ", moneyOwed=" + moneyOwed +
                ", moneyReceived=" + moneyReceived +
                '}';
    }
}
