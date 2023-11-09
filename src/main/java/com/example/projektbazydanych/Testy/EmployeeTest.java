package com.example.projektbazydanych.Testy;

import com.example.projektbazydanych.employee.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee(1, 1001, "Dawid", "Jasper", "password123", "dawidkamil92@example.com", "693405213");
    }

    @Test
    public void testGetAndSetEmployeeID() {
        assertEquals(1, employee.getEMPLOYEEID());
        employee.setEMPLOYEEID(2);
        assertEquals(2, employee.getEMPLOYEEID());
    }

    @Test
    public void testGetAndSetCompanyID() {
        assertEquals(1001, employee.getCOMPANYNAME());
        employee.setCOMPANYNAME(1002);
        assertEquals(1002, employee.getCOMPANYNAME());
    }

    @Test
    public void testGetAndSetFirstName() {
        assertEquals("Dawid", employee.getFIRSTNAME());
        employee.setFIRSTNAME("Miska");
        assertEquals("Miska", employee.getFIRSTNAME());
    }

    @Test
    public void testGetAndSetLastName() {
        assertEquals("Jasper", employee.getLASTNAME());
        employee.setLASTNAME("przykadloweNaziwsko");
        assertEquals("przykadloweNaziwsko", employee.getLASTNAME());
    }

    @Test
    public void testGetAndSetPassword() {
        assertEquals("password123", employee.getPASSWORD());
        employee.setPASSWORD("newPassword456");
        assertEquals("newPassword456", employee.getPASSWORD());
    }

    @Test
    public void testGetAndSetEmail() {
        assertEquals("dawidkamil92@example.com", employee.getEMAIL());
        employee.setEMAIL("myska.jasper@example.com");
        assertEquals("myska.jasper@example.com", employee.getEMAIL());
    }

    @Test
    public void testGetAndSetPhoneNumber() {
        assertEquals("693405213", employee.getPHONENUMBER());
        //sprawdzanie sposobu przechowywania numerow
        employee.setPHONENUMBER("987-654-321");
        assertEquals("987-654-321", employee.getPHONENUMBER());
    }
}
