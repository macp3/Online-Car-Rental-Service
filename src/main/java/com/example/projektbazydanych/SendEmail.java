package com.example.projektbazydanych;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;

import java.io.*;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;

import static javax.mail.Message.RecipientType.TO;

public class SendEmail {
    private static final String CREDENTIALS_FILE_PATH = "/client_secret_874541274443-8q60c40k0j5ps2o1bh5upr5e37g8uvka.apps.googleusercontent.com.json";
    private static final String FROM_ADRESS = "suvami611@gmail.com";

    private final Gmail service;

    public SendEmail() throws Exception {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, JSON_FACTORY))
                .setApplicationName("ProjektBazyDanych")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, GsonFactory JSON_FACTORY)
            throws IOException {

        /*  propozycja
            mozna swapnac to na bo to bedzie handlowalo nullExpecta w razie wypadku

             GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(Objects.requireNonNull(SendEmail.class.getResourceAsStream(CREDENTIALS_FILE_PATH))));

        */


        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(SendEmail.class.getResourceAsStream(CREDENTIALS_FILE_PATH)));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void sendMail(String subject, String message, String toAdress) throws GeneralSecurityException, IOException, AddressException, MessagingException {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(FROM_ADRESS));
        email.addRecipient(TO, new InternetAddress(toAdress));
        email.setSubject(subject);
        email.setText(message);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            msg = service.users().messages().send("me", msg).execute();
            System.out.println("Message id: " + msg.getId());
            System.out.println(msg.toPrettyString());

        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }

}