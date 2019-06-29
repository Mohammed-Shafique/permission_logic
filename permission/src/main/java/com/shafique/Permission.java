package com.shafique;

public class Permission {
    private int consumerId;
    private String OK_TO_CALL;
    private String OK_TO_EMAIL;
    private String OK_TO_MAIL;
    private String OK_TO_CONTACT;

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public String getOK_TO_CALL() {
        return OK_TO_CALL;
    }

    public void setOK_TO_CALL(String OK_TO_CALL) {
        this.OK_TO_CALL = OK_TO_CALL;
    }

    public String getOK_TO_EMAIL() {
        return OK_TO_EMAIL;
    }

    public void setOK_TO_EMAIL(String OK_TO_EMAIL) {
        this.OK_TO_EMAIL = OK_TO_EMAIL;
    }

    public String getOK_TO_MAIL() {
        return OK_TO_MAIL;
    }

    public void setOK_TO_MAIL(String OK_TO_MAIL) {
        this.OK_TO_MAIL = OK_TO_MAIL;
    }

    public String getOK_TO_CONTACT() {
        return OK_TO_CONTACT;
    }

    public void setOK_TO_CONTACT(String OK_TO_CONTACT) {
        this.OK_TO_CONTACT = OK_TO_CONTACT;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "consumerId=" + consumerId +
                ", OK_TO_CALL='" + OK_TO_CALL + '\'' +
                ", OK_TO_EMAIL='" + OK_TO_EMAIL + '\'' +
                ", OK_TO_MAIL='" + OK_TO_MAIL + '\'' +
                ", OK_TO_CONTACT='" + OK_TO_CONTACT + '\'' +
                '}';
    }
}
