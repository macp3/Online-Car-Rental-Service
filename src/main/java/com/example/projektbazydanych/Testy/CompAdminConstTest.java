package com.example.projektbazydanych.Testy;

import com.example.projektbazydanych.CompAdmin.CompAdmin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


//testowanie bezargumentowego konstruktora//
public class CompAdminConstTest {
    private CompAdmin compAdmin;

    @Before
    public void setUp() {
        compAdmin = new CompAdmin();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, compAdmin.getADMINID());
        assertEquals(0, compAdmin.getCOMPANYID());
        assertNull(compAdmin.getEMAIL());
        assertNull(compAdmin.getPASSWORD());
    }
}
