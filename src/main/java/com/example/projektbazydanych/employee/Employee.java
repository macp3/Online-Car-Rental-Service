package com.example.projektbazydanych.employee;

import java.io.Serializable;
public class Employee implements Serializable {
    private int EMPLOYEEID;
    private int COMPANYID;
    private String FIRSTNAME;
    private String LASTNAME;
    private String PASSWORD;
    private String EMAIL;
    private String PHONENUMBER;

    public Employee() {

    }

    public Employee(int EMPLOYEEID, int COMPANYID, String FIRSTNAME, String LASTNAME, String PASSWORD, String EMAIL, String PHONENUMBER) {
        this.EMPLOYEEID = EMPLOYEEID;
        this.COMPANYID = COMPANYID;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.PASSWORD = PASSWORD;
        this.EMAIL = EMAIL;
        this.PHONENUMBER = PHONENUMBER;
    }

    public int getCOMPANYNAME() {
        return COMPANYID;
    }

    public void setCOMPANYNAME(int COMPANYID) {
        this.COMPANYID = COMPANYID;
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

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONENUMBER() {
        return PHONENUMBER;
    }

    public void setPHONENUMBER(String PHONENUMBER) {
        this.PHONENUMBER = PHONENUMBER;
    }

    public int getEMPLOYEEID() {
        return EMPLOYEEID;
    }

    public void setEMPLOYEEID(int EMPLOYEEID) {
        this.EMPLOYEEID = EMPLOYEEID;
    }
}
