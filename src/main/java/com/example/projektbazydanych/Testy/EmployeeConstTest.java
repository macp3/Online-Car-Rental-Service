package com.example.projektbazydanych.Testy;



import com.example.projektbazydanych.employee.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


//testowanie bezargumentowego konstruktora//
public class EmployeeConstTest {
    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, employee.getEMPLOYEEID());
        assertEquals(0, employee.getCOMPANYNAME());
        assertNull(employee.getFIRSTNAME());
        assertNull(employee.getLASTNAME());
        assertNull(employee.getPASSWORD());
        assertNull(employee.getEMAIL());
        assertNull(employee.getPHONENUMBER());
    }
}

