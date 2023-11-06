package com.example.projektbazydanych.user;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import com.example.projektbazydanych.CompAdmin.CompAdmin;
import com.example.projektbazydanych.client.Client;
import com.example.projektbazydanych.employee.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UserLoggingServlet", urlPatterns = "/user/login")
public class UserLoggingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");
            Statement stmt1 = con.createStatement();

            ResultSet checkIfClientExist = stmt1.executeQuery("select * from CLIENTS WHERE EMAIL = '" +
                    "" + email + "' AND PASSWORD = '" + password + "'");

            if (checkIfClientExist.next()) {
                if (Objects.equals(checkIfClientExist.getString(10), "Verified")) {
                    Client loggedClient = new Client(checkIfClientExist.getInt(1), checkIfClientExist.getString(2),
                            checkIfClientExist.getString(3), checkIfClientExist.getString(4),
                            checkIfClientExist.getString(5), checkIfClientExist.getString(6),
                            checkIfClientExist.getInt(7), checkIfClientExist.getString(8));

                    request.setAttribute("loggedClient", loggedClient);

                    Cookie emailcookie = new Cookie("email", email);
                    Cookie passwordcookie = new Cookie("password", password);
                    response.addCookie(emailcookie);
                    response.addCookie(passwordcookie);

                    getServletContext().getRequestDispatcher("/client/loggedClientMainPage.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Konto nie zostało zweryfikowane");
                    doGet(request, response);
                }

            } else {
                checkIfClientExist.close();
                stmt1.close();
                Statement stmt2 = con.createStatement();
                ResultSet checkIfEmployeeExist = stmt2.executeQuery("select * from EMPLOYEES WHERE EMAIL = '" +
                        "" + email + "' AND PASSWORD = '" + password + "'");

                if (checkIfEmployeeExist.next()) {
                    Employee loggedEmployee = new Employee(checkIfEmployeeExist.getInt(1),
                            checkIfEmployeeExist.getInt(2), checkIfEmployeeExist.getString(3),
                            checkIfEmployeeExist.getString(4), checkIfEmployeeExist.getString(5),
                            checkIfEmployeeExist.getString(6), checkIfEmployeeExist.getString(7));

                    request.setAttribute("loggedEmployee", loggedEmployee);

                    Cookie emailcookie = new Cookie("email", email);
                    Cookie passwordcookie = new Cookie("password", password);
                    response.addCookie(emailcookie);
                    response.addCookie(passwordcookie);

                    getServletContext().getRequestDispatcher("/loggedEmployeeMainPage.jsp").forward(request, response);
                } else {
                    checkIfEmployeeExist.close();
                    stmt2.close();
                    Statement stmt3 = con.createStatement();
                    ResultSet checkIfCompAdminExist = stmt3.executeQuery("select * from COMP_ADMINS WHERE EMAIL = '" +
                            email + "' AND PASSWORD = '" + password + "'");

                    if (checkIfCompAdminExist.next()) {
                        //problem
                        CompAdmin loggedCompAdmin = new CompAdmin(checkIfCompAdminExist.getInt(1),
                                checkIfCompAdminExist.getInt(2), checkIfCompAdminExist.getString(3),
                                checkIfCompAdminExist.getString(4));

                        request.setAttribute("loggedCompAdmin", loggedCompAdmin);

                        Cookie emailcookie = new Cookie("email", email);
                        Cookie passwordcookie = new Cookie("password", password);
                        response.addCookie(emailcookie);
                        response.addCookie(passwordcookie);

                        getServletContext().getRequestDispatcher("/loggedCompAdminMainPage.jsp").forward(request, response);

                    } else {
                        request.setAttribute("error", "Błędna nazwa użytkownika lub hasło");
                        doGet(request, response);
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("error", e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/userLoginForm.jsp").forward(request, response);
    }
}