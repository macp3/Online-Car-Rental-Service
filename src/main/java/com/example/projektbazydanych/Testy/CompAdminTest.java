package com.example.projektbazydanych.Testy;

import com.example.projektbazydanych.CompAdmin.CompAdmin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompAdminTest {
    private CompAdmin compAdmin;

    @Before
    public void setUp() {
        compAdmin = new CompAdmin(1, 1001, "admin@example.com", "adminPassword123");
    }

    @Test
    public void testGetAndSetAdminID() {
        assertEquals(1, compAdmin.getADMINID());
        compAdmin.setADMINID(2);
        assertEquals(2, compAdmin.getADMINID());
    }

    @Test
    public void testGetAndSetCompanyID() {
        assertEquals(1001, compAdmin.getCOMPANYID());
        compAdmin.setCOMPANYID(1002);
        assertEquals(1002, compAdmin.getCOMPANYID());
    }

    @Test
    public void testGetAndSetEmail() {
        assertEquals("admin@example.com", compAdmin.getEMAIL());
        compAdmin.setEMAIL("newadmin@example.com");
        assertEquals("newadmin@example.com", compAdmin.getEMAIL());
    }

    @Test
    public void testGetAndSetPassword() {
        assertEquals("adminPassword123", compAdmin.getPASSWORD());
        compAdmin.setPASSWORD("newPassword456");
        assertEquals("newPassword456", compAdmin.getPASSWORD());
    }

}
