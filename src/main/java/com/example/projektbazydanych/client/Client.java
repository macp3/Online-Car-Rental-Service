package com.example.projektbazydanych.client;

import java.io.Serializable;

public class Client implements Serializable {
    private int CLIENTID;
    private String FIRSTNAME;
    private String LASTNAME;
    private String BILLINGADDRESS;
    private String PASSWORD;
    private String EMAIL;
    private int PREFFEREDPAYMENTID;
    private String PHONENUMBER;

    public Client() {

    }

    public Client(int CLIENTID, String FIRSTNAME, String LASTNAME, String BILLINGADDRESS, String PASSWORD, String EMAIL, int PREFFEREDPAYMENTID, String PHONENUMBER) {
        this.CLIENTID = CLIENTID;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.BILLINGADDRESS = BILLINGADDRESS;
        this.PASSWORD = PASSWORD;
        this.EMAIL = EMAIL;
        this.PREFFEREDPAYMENTID = PREFFEREDPAYMENTID;
        this.PHONENUMBER = PHONENUMBER;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPHONENUMBER() {
        return PHONENUMBER;
    }

    public void setPHONENUMBER(String PHONENUMBER) {
        this.PHONENUMBER = PHONENUMBER;
    }

    public String getBILLINGADDRESS() {
        return BILLINGADDRESS;
    }

    public void setBILLINGADDRESS(String BILLINGADDRESS) {
        this.BILLINGADDRESS = BILLINGADDRESS;
    }

    public int getPREFFEREDPAYMENTID() {
        return PREFFEREDPAYMENTID;
    }

    public int getCLIENTID() {
        return CLIENTID;
    }

    public void setCLIENTID(int CLIENTID) {
        this.CLIENTID = CLIENTID;
    }

    public void setPREFFEREDPAYMENTID(int PREFFEREDPAYMENTID) {
        this.PREFFEREDPAYMENTID = PREFFEREDPAYMENTID;
    }
}
