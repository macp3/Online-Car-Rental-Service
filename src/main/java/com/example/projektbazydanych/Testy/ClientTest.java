package com.example.projektbazydanych.Testy;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.projektbazydanych.client.Client;

public class ClientTest {
    @Test
    public void testClientConstructor() {
        Client client = new Client(0, "Ignacy", "Pytel", "debil", "test@example.com", "Address", 1, "2137");
        assertEquals("test@example.com", client.getEMAIL());
        assertEquals("Ignacy", client.getFIRSTNAME());
        assertEquals("Pytel", client.getLASTNAME());
        assertEquals("debil", client.getPASSWORD());
        assertEquals("2137", client.getPHONENUMBER());
        assertEquals("Address", client.getBILLINGADDRESS());
        assertEquals(1, client.getPREFFEREDPAYMENTID());
    }


    @Test
    public void testGettersAndSetters() {
        Client client = new Client();
        client.setEMAIL("debilHWDP@gmail.com");
        client.setFIRSTNAME("Playboi");
        client.setLASTNAME("Carti");
        client.setPASSWORD("haslomaslo");
        client.setPHONENUMBER("123456789");
        client.setBILLINGADDRESS("Chelm");
        client.setPREFFEREDPAYMENTID(1);

        assertEquals("debilHWDP@gmail.com", client.getEMAIL());
        assertEquals("Playboi", client.getFIRSTNAME());
        assertEquals("Carti", client.getLASTNAME());
        assertEquals("haslomaslo", client.getPASSWORD());
        assertEquals("123456789", client.getPHONENUMBER());
        assertEquals("Chelm", client.getBILLINGADDRESS());
        assertEquals(1, client.getPREFFEREDPAYMENTID());
    }
}