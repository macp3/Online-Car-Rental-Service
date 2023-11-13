package com.example.projektbazydanych.user;

import java.io.*;
import java.sql.*;
import java.util.Objects;

import com.example.projektbazydanych.CompAdmin.CompAdmin;
import com.example.projektbazydanych.client.Client;
import com.example.projektbazydanych.employee.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UserEnterServlet", urlPatterns = "/start")
public class UserEnterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            getServletContext().getRequestDispatcher("/startPage.jsp").forward(request, response);
        } else {
            String email = null;
            String password = null;
            for (Cookie c : cookies) {
                String tname = c.getName();
                if (Objects.equals(tname, "email")) email = c.getValue();
                else if (Objects.equals(tname, "password")) password = c.getValue();
                System.out.println(tname);
                System.out.println(c.getValue());
            }
            if (email != null && password != null) {
                try {
                    Class.forName("oracle.jdbc.OracleDriver");

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");

                    String query = "select * from CLIENTS WHERE EMAIL = ? AND PASSWORD = ?";
                    PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, email);
                    stmt.setString(2, password);


                    ResultSet checkIfClientExist = stmt.executeQuery();

                    if (checkIfClientExist.next()) {
                        Client loggedClient = new Client(checkIfClientExist.getInt(1), checkIfClientExist.getString(2),
                                checkIfClientExist.getString(3), checkIfClientExist.getString(4),
                                checkIfClientExist.getString(5), checkIfClientExist.getString(6),
                                checkIfClientExist.getInt(7), checkIfClientExist.getString(8));

                        request.setAttribute("loggedClient", loggedClient);

                        getServletContext().getRequestDispatcher("/LoggedClientMainServlet").forward(request, response);


                    } else {
                        checkIfClientExist.close();
                        stmt.close();
                        query = "select * from EMPLOYEES WHERE EMAIL = ? AND PASSWORD = ?";
                        PreparedStatement stmt1 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        stmt1.setString(1, email);
                        stmt1.setString(2, password);

                        ResultSet checkIfEmployeeExist = stmt1.executeQuery();

                        if (checkIfEmployeeExist.next()) {

                            Employee loggedEmployee = new Employee(checkIfEmployeeExist.getInt(1),
                                    checkIfEmployeeExist.getInt(2), checkIfEmployeeExist.getString(3),
                                    checkIfEmployeeExist.getString(4), checkIfEmployeeExist.getString(5),
                                    checkIfEmployeeExist.getString(6), checkIfEmployeeExist.getString(7));

                            request.setAttribute("loggedEmployee", loggedEmployee);

                            getServletContext().getRequestDispatcher("/loggedEmployeeMainPage.jsp").forward(request, response);

                        } else {
                            checkIfEmployeeExist.close();
                            stmt1.close();
                            query = "select * from COMP_ADMINS WHERE EMAIL = ? AND PASSWORD = ?";
                            PreparedStatement stmt2 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                            stmt2.setString(1, email);
                            stmt2.setString(2, password);

                            ResultSet checkIfCompAdminExist = stmt2.executeQuery();

                            if (checkIfCompAdminExist.next()) {
                                CompAdmin loggedCompAdmin = new CompAdmin(checkIfCompAdminExist.getInt(1),
                                        checkIfClientExist.getInt(2), checkIfCompAdminExist.getString(3),
                                        checkIfCompAdminExist.getString(4));

                                request.setAttribute("loggedCompAdmin", loggedCompAdmin);

                                getServletContext().getRequestDispatcher("/loggedCompAdminMainPage.jsp").forward(request, response);

                            } else {
                                checkIfCompAdminExist.close();
                                stmt2.close();
                                getServletContext().getRequestDispatcher("/startPage.jsp").forward(request, response);
                            }
                        }
                    }

                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                getServletContext().getRequestDispatcher("/startPage.jsp").forward(request, response);
            }
        }
    }
}