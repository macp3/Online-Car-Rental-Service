package com.example.projektbazydanych.client;

import java.io.*;
import java.net.InetAddress;
import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.example.projektbazydanych.SendEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ClientRegistrationServlet", urlPatterns = "/register")
public class ClientRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String EMAIL = request.getParameter("EMAIL");
        String FIRSTNAME = request.getParameter("FIRSTNAME");
        String LASTNAME = request.getParameter("LASTNAME");
        String PASSWORD = request.getParameter("PASSWORD");
        String CONFIRMPASSWORD = request.getParameter("CONFIRMPASSWORD");
        String PHONENUMBER = request.getParameter("PHONENUMBER");
        String CITYCODE = request.getParameter("CITYCODE");
        String CITY = request.getParameter("CITY");
        String STREETNAME = request.getParameter("STREETNAME");
        String HOUSENUM = request.getParameter("HOUSENUM");


        String BILLINGADDRESS = CITYCODE + " " + CITY + " " + STREETNAME + " " + HOUSENUM;

        if (EMAIL == null || FIRSTNAME == null || LASTNAME == null || PASSWORD == null
                || CONFIRMPASSWORD == null || PHONENUMBER == null) {
            request.setAttribute("error", "Nie wszystkie obowiązkowe pola zostały uzupełnione");
            doGet(request, response);
        } else {
            if (!PASSWORD.equals(CONFIRMPASSWORD)) {
                request.setAttribute("error", "Hasła się nie pokrywają");
                doGet(request, response);
            } else {
                try {
                    Class.forName("oracle.jdbc.OracleDriver");

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");

                    String query = "select * from CLIENTS WHERE EMAIL = ?";
                    PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, EMAIL.trim());


                    ResultSet checkIfUserExist = stmt.executeQuery();


                    if (checkIfUserExist.next()) {
                        request.setAttribute("error", "Taki użytkownik już istnieje w bazie");
                        con.close();
                        doGet(request, response);
                    } else {
                        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

                        String SQL = "INSERT INTO CLIENTS(CLIENTID, FIRSTNAME, LASTNAME, " +
                                "BILLINGADDRESS, PASSWORD, EMAIL, PREFFEREDPAYMENTID, PHONENUMBER, ENTRYDATE, STATUS) " +
                                "VALUES (CLIENT_ID_SEQUENCE.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

                        pstmt.setString(1, FIRSTNAME.trim());
                        pstmt.setString(2, LASTNAME.trim());
                        pstmt.setString(3, BILLINGADDRESS.trim());
                        pstmt.setString(4, PASSWORD.trim());
                        pstmt.setString(5, EMAIL.trim());
                        pstmt.setInt(6, 1);
                        pstmt.setString(7, PHONENUMBER.trim());
                        pstmt.setString(8, timeStamp);
                        pstmt.setString(9, "Waiting");

                        int affectedRows = pstmt.executeUpdate();

                        con.close();

                        if (affectedRows > 0) {
                            new SendEmail().sendMail("Witamy na pokładzie!", """
                                    Jesteś od teraz użytkownikiem SUVami!
                                                                        
                                    Aby zweryfikować swoje konto kliknij w poniższy link:
                                    http://""" + InetAddress.getLocalHost().getHostAddress().trim() + ":8080/Verification" + "?email=" + EMAIL.trim() + """
                                                                        
                                    Pozdrawiamy
                                    Zespół SUVami!
                                    """, EMAIL.trim());
                        }
                    }
                } catch (com.google.api.client.googleapis.json.GoogleJsonResponseException e) {
                    request.setAttribute("error", "Niepoprawny adres email");
                    doGet(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", e);
                    doGet(request, response);
                }
            }
        }

        request.setAttribute("error", "Udało ci się pomyślnie zarejestrować, potwierdź teraz swój" +
                " email klikając w wiadomości przysłanej na twój adres");
        doGet(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/clientRegisterForm.jsp").forward(request, response);
    }

}