package com.example.projektbazydanych;

import java.io.*;
import java.net.InetAddress;
import java.sql.*;
import java.util.Objects;

import com.example.projektbazydanych.CompAdmin.CompAdmin;
import com.example.projektbazydanych.client.Client;
import com.example.projektbazydanych.employee.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.example.projektbazydanych.*;

@WebServlet(name = "UserLoggingServlet", urlPatterns = "/login")
public class UserLoggingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");

            String query = "select * from CLIENTS WHERE EMAIL = ? AND PASSWORD = ?";
            PreparedStatement stmt1 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, email);
            stmt1.setString(2, password);

            ResultSet checkIfClientExist = stmt1.executeQuery();

            if (checkIfClientExist.next()) {
                if (Objects.equals(checkIfClientExist.getString(10), "Verified")) {
                    Client loggedClient = new Client(checkIfClientExist.getInt(1), checkIfClientExist.getString(2),
                            checkIfClientExist.getString(3), checkIfClientExist.getString(4),
                            checkIfClientExist.getString(5), checkIfClientExist.getString(6),
                            checkIfClientExist.getInt(7), checkIfClientExist.getString(8));

                    Cookie emailcookie = new Cookie("email", email);
                    Cookie passwordcookie = new Cookie("password", password);
                    response.addCookie(emailcookie);
                    response.addCookie(passwordcookie);

                    request.setAttribute("loggedClient", loggedClient);

                    getServletContext().getRequestDispatcher("/loggedClientMainPage.jsp").forward(request, response);

                    con.close();
                    return;
                } else {
                    request.setAttribute("error", "Konto nie zostało zweryfikowane");
                    doGet(request, response);
                }

            } else {
                checkIfClientExist.close();
                stmt1.close();

                query = "select * from EMPLOYEES WHERE EMAIL = ? AND PASSWORD = ?";
                PreparedStatement stmt2 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt2.setString(1, email);
                stmt2.setString(2, password);

                ResultSet checkIfEmployeeExist = stmt2.executeQuery();

                if (checkIfEmployeeExist.next()) {
                    Employee loggedEmployee = new Employee(checkIfEmployeeExist.getInt(1),
                            checkIfEmployeeExist.getInt(2), checkIfEmployeeExist.getString(3),
                            checkIfEmployeeExist.getString(4), checkIfEmployeeExist.getString(5),
                            checkIfEmployeeExist.getString(6), checkIfEmployeeExist.getString(7));

                    request.setAttribute("loggedEmployee", loggedEmployee);

                    Cookie emailcookie = new Cookie("email", email);
                    response.addCookie(emailcookie);
                    Cookie passwordcookie = new Cookie("password", password);
                    response.addCookie(passwordcookie);

                    getServletContext().getRequestDispatcher("/loggedEmployeeMainPage.jsp").forward(request, response);

                    con.close();
                    return;
                } else {
                    checkIfEmployeeExist.close();
                    stmt2.close();

                    query = "select * from COMP_ADMINS WHERE EMAIL = ? AND PASSWORD = ?";
                    PreparedStatement stmt3 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    stmt3.setString(1, email);
                    stmt3.setString(2, password);

                    ResultSet checkIfCompAdminExist = stmt3.executeQuery();

                    if (checkIfCompAdminExist.next()) {

                        CompAdmin loggedCompAdmin = new CompAdmin(checkIfCompAdminExist.getInt(1),
                                checkIfCompAdminExist.getInt(2), checkIfCompAdminExist.getString(3),
                                checkIfCompAdminExist.getString(4));

                        request.setAttribute("loggedCompAdmin", loggedCompAdmin);

                        Cookie emailcookie = new Cookie("email", email);
                        response.addCookie(emailcookie);
                        Cookie passwordcookie = new Cookie("password", password);
                        response.addCookie(passwordcookie);

                        getServletContext().getRequestDispatcher("/loggedCompAdminMainPage.jsp").forward(request, response);

                        con.close();
                        return;

                    } else {
                        request.setAttribute("error", "Błędna nazwa użytkownika lub hasło");
                        doGet(request, response);
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/userLoginForm.jsp").forward(request, response);
    }
}