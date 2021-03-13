package com.vaibhav.fitnessapp;

public class ReviewModel {
    private String best;
    private String worst;
    private String complaint;
    private String name;
    private String email;
    private String uid;

    public ReviewModel() {
        this.best = null;
        this.worst = null;
        this.complaint = null;
        this.name = null;
        this.email = null;
        this.uid = null;
    }

    public ReviewModel(String best, String worst, String complaint, String name, String email, String uid) {
        this.best = best;
        this.worst = worst;
        this.complaint = complaint;
        this.name = name;
        this.email = email;
        this.uid = uid;
    }

    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }

    public String getWorst() {
        return worst;
    }

    public void setWorst(String worst) {
        this.worst = worst;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "best='" + best + '\'' +
                ", worst='" + worst + '\'' +
                ", complaint='" + complaint + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
