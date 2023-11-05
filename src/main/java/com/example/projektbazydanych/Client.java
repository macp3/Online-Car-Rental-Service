package com.example.projektbazydanych;

import java.io.Serializable;

public class Client implements Serializable {
    private String EMAIL;
    private String FIRSTNAME;
    private String LASTNAME;
    private String PASSWORD;
    private String PHONENUMBER;
    private String BILLINGADDRESS;
    private int PREFFEREDPAYMENTID;

    public Client() {
    }

    public Client(String EMAIL, String FIRSTNAME, String LASTNAME, String PASSWORD, String PHONENUMBER, String BILLINGADDRESS, int PREFFEREDPAYMENTID) {
        this.EMAIL = EMAIL;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.PASSWORD = PASSWORD;
        this.PHONENUMBER = PHONENUMBER;
        this.BILLINGADDRESS = BILLINGADDRESS;
        this.PREFFEREDPAYMENTID = PREFFEREDPAYMENTID;
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

    public void setPREFFEREDPAYMENTID(Integer PREFFEREDPAYMENTID) {
        this.PREFFEREDPAYMENTID = PREFFEREDPAYMENTID;
    }
}
