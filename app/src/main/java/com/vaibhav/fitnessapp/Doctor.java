package com.vaibhav.fitnessapp;

public class Doctor {
    private String name;
    private String address;
    private String gender;
    private String emailId;
    private String phone;
    private String password;
    private String state;
    private String city;
    private String specialize;
    private int experience;
    private String uidDoctor;
    private int fee;
    private int moneyOwed;
    private int moneyReceived;

    public Doctor() {
        this.name = null;
        this.gender = null;
        this.address = null;
        this.emailId = null;
        this.phone = null;
        this.password = null;
        this.state = null;
        this.city = null;
        this.specialize = "None";
        this.experience = 0;
        this.uidDoctor = null;
        this.fee = 0;
        this.moneyOwed = 0;
        this.moneyReceived = 0;
    }

    public Doctor(String name, String gender, String address, String emailId, String phone, String password, String state, String city, String specialize, int experience, String uidDoctor, int fee, int moneyOwed, int moneyReceived) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.emailId = emailId;
        this.phone = phone;
        this.password = password;
        this.state = state;
        this.city = city;
        this.experience = experience;
        this.specialize = specialize;
        this.uidDoctor = uidDoctor;
        this.fee = fee;
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

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getUidDoctor() {
        return uidDoctor;
    }

    public void setUidDoctor(String uidDoctor) {
        this.uidDoctor = uidDoctor;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", specialize='" + specialize + '\'' +
                ", experience=" + experience +
                ", uidDoctor='" + uidDoctor + '\'' +
                ", fee=" + fee +
                ", moneyOwed=" + moneyOwed +
                ", moneyReceived=" + moneyReceived +
                '}';
    }
}
