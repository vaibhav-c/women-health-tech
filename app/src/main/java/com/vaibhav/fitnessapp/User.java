package com.vaibhav.fitnessapp;

public class User {
    private String name;
    private String address;
    private String emailId;
    private String phone;
    private String password;
    private String state;
    private String city;
    private String preExist;
    private int age;
    private int wallet;
    private String gender;

    public User() {
        this.name = null;
        this.address = null;
        this.emailId = null;
        this.phone = null;
        this.password = null;
        this.state = null;
        this.city = null;
        this.preExist = "None";
        this.age = 0;
        this.wallet = 0;
        this.gender = null;
    }

    public User(String name, String address, String emailId, String phone, String password, String state, String city, String preExist, int age, int wallet, String gender) {
        this.name = name;
        this.address = address;
        this.emailId = emailId;
        this.phone = phone;
        this.password = password;
        this.state = state;
        this.city = city;
        this.age = age;
        this.preExist = preExist;
        this.gender = gender;
        this.wallet = wallet;
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

    public String getPreExist() {
        return preExist;
    }

    public void setPreExist(String preExist) {
        this.preExist = preExist;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", preExist='" + preExist + '\'' +
                ", age=" + age +
                ", wallet=" + wallet +
                ", gender=" + gender +
                '}';
    }
}
