package com.example.projektbazydanych.CompAdmin;

import java.io.Serializable;
public class CompAdmin implements Serializable {

    private int ADMINID;
    private int COMPANYID;
    private String EMAIL;
    private String PASSWORD;

    public CompAdmin(int ADMINID, int COMPANYID, String EMAIL, String PASSWORD) {
        this.ADMINID = ADMINID;
        this.COMPANYID = COMPANYID;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
    }

    public CompAdmin() {
    }

    public int getADMINID() {
        return ADMINID;
    }

    public void setADMINID(int ADMINID) {
        this.ADMINID = ADMINID;
    }

    public int getCOMPANYID() {
        return COMPANYID;
    }

    public void setCOMPANYID(int COMPANYID) {
        this.COMPANYID = COMPANYID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
