package com.example.projektbazydanych.Testy;

import com.example.projektbazydanych.SendEmail;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class EmailTest {
    @Test
    public void testSendEmail() throws Exception {
        SendEmail sendEmail = new SendEmail();
                /*
                wywali blad bo wiadomosc nie moze byc nullem, sprawdzane bylo i dziala dla reszty sytuacji
                sprawdz maciek u siebie czy dla jakiejkolwiek innego message nie ma nullPointer'a
                bo jak jest to znaczy ze cos nie tak jest z konstruktorem SendEmail


                ok 0.0
                */
        try {
            sendEmail.sendMail("Test", "testset", "suvami611@gmail.com");
        } catch (GeneralSecurityException | IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
