package com.vaibhav.fitnessapp;

public class AppointmentModel {
    private String id;
    private int confirmStatus;
    private String patientName;
    private String uidPatient;
    private String doctorUid;
    private String timeSlot;
    private int payment;
    private Prescription prescription;
    private boolean remove;
    private boolean paid;
    private String link;

    public AppointmentModel() {
        this.uidPatient = null;
        this.doctorUid = null;
        this.timeSlot = null;
        this.payment = 0;
        this.confirmStatus = 0;
        this.patientName = null;
        this.remove = false;
        this.prescription = null;
        this.id = null;
        this.paid = false;
        link = null;
    }

    public AppointmentModel(int confirmStatus, String patientName, String uidPatient, String doctorUid, String timeSlot, int payment, boolean remove, Prescription prescription, String id, boolean paid, String link) {
        this.confirmStatus = confirmStatus;
        this.patientName = patientName;
        this.uidPatient = uidPatient;
        this.doctorUid = doctorUid;
        this.timeSlot = timeSlot;
        this.payment = payment;
        this.remove = remove;
        this.prescription = prescription;
        this.id = id;
        this.paid = paid;
        this.link = link;
    }

    public String getUidPatient() {
        return uidPatient;
    }

    public void setUidPatient(String uidPatient) {
        this.uidPatient = uidPatient;
    }

    public String getDoctorUid() {
        return doctorUid;
    }

    public void setDoctorUid(String doctorUid) {
        this.doctorUid = doctorUid;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(int confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "AppointmentModel{" +
                "id='" + id + '\'' +
                ", confirmStatus=" + confirmStatus +
                ", patientName='" + patientName + '\'' +
                ", uidPatient='" + uidPatient + '\'' +
                ", doctorUid='" + doctorUid + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", payment=" + payment +
                ", prescription=" + prescription +
                ", remove=" + remove +
                ", paid=" + paid +
                ", link='" + link + '\'' +
                '}';
    }
}
