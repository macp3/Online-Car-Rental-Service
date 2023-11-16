package com.example.projektbazydanych;

import java.io.*;
import java.net.InetAddress;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

import com.example.projektbazydanych.CompAdmin.CompAdmin;
import com.example.projektbazydanych.client.Client;
import com.example.projektbazydanych.employee.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PasswordRestoreServlet", urlPatterns = "/restore_password")
public class PasswordRestoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PasswordRestoreServlet - POST");
        String email = request.getParameter("EMAIL");
        Random gen = new Random();
        int RestoreCode = gen.nextInt(1000);
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");

            String query = "select * from CLIENTS FULL JOIN HOMEUSER.COMP_ADMINS CA on CLIENTS.EMAIL = CA.EMAIL FULL JOIN HOMEUSER.EMPLOYEES E on CA.EMAIL = E.EMAIL WHERE CLIENTS.EMAIL = ? OR CA.EMAIL = ? OR E.EMAIL = ?";
            PreparedStatement stmt1 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, email);
            stmt1.setString(2, email);
            stmt1.setString(3, email);

            ResultSet checkIfClientExist = stmt1.executeQuery();

            if (checkIfClientExist.next()) {
                new SendEmail().sendMail("Przywrócenie hasła", """
                                                            
                        Aby przywrócić hasło kliknij w poniższy link:
                        http://""" + InetAddress.getLocalHost().getHostAddress().trim() + ":8080/password_restored" + "?email=" + email.trim() + "&?code=" + RestoreCode + """
                                                            
                        Pozdrawiamy
                        Zespół SUVami!
                        """, email.trim());

                request.setAttribute("error", "Na podany adres email został wysłany link do zmiany hasła");
                doGet(request, response);
            } else {
                request.setAttribute("error", "Niepoprawny adres email");
                doGet(request, response);
            }

        } catch (com.google.api.client.googleapis.json.GoogleJsonResponseException e) {
            request.setAttribute("error", "Niepoprawny adres email");
            doGet(request, response);
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("error", e);
            doGet(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/passwordRestoreForm.jsp").forward(request, response);
    }
}