package com.example.projektbazydanych;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ClientLoggingServlet", urlPatterns = "/client/login")
public class ClientLoggingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");
            Statement stmt = con.createStatement();

            ResultSet checkIfUserExist = stmt.executeQuery("select * from CLIENTS WHERE EMAIL = '" +
                    "" + email + "' AND PASSWORD = '" + password + "'");

            if (checkIfUserExist.next()) {
                if (Objects.equals(checkIfUserExist.getString(10), "Verified")) {
                    Client loggedClient = new Client(checkIfUserExist.getString(6),
                            checkIfUserExist.getString(2), checkIfUserExist.getString(3),
                            checkIfUserExist.getString(5), checkIfUserExist.getString(8),
                            checkIfUserExist.getString(4), checkIfUserExist.getInt(7));

                    request.setAttribute("loggedClient", loggedClient);

                    Cookie emailcookie = new Cookie("email", email);
                    Cookie passwordcookie = new Cookie("password", password);
                    response.addCookie(emailcookie);
                    response.addCookie(passwordcookie);

                    getServletContext().getRequestDispatcher("/loggedClientMainPage.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("error", "Konto nie zostało zweryfikowane");
                    doGet(request, response);
                }

            } else {
                request.setAttribute("error", "Błędna nazwa użytkownika lub hasło");
                doGet(request, response);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("error", e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/clientLoggingForm.jsp").forward(request, response);
    }
}