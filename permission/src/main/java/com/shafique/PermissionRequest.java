package com.shafique;

public class PermissionRequest {

    private int consumerId ;
    private String language;
    private String email;
    private String phone;
    private String address;

    private Preference pref;
    private Suppression suppression;

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Preference getPref() {
        return pref;
    }

    public void setPref(Preference pref) {
        this.pref = pref;
    }

    public Suppression getSuppression() {
        return suppression;
    }

    public void setSuppression(Suppression suppression) {
        this.suppression = suppression;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
