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

@WebServlet(name = "ClientEnterServlet", urlPatterns = "/client")
public class ClientEnterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if(cookies == null) {
            getServletContext().getRequestDispatcher("/unknownClient.jsp").forward(request, response);
        } else {
            String email = null;
            String password = null;
            for (Cookie c : cookies ) {
                String tname = c.getName();
                if (Objects.equals(tname, "email")) email = c.getValue();
                else if (Objects.equals(tname, "password")) password = c.getValue();
            }
            if (email != null && password != null) {
                try {
                    Class.forName("oracle.jdbc.OracleDriver");

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");
                    Statement stmt = con.createStatement();

                    ResultSet checkIfUserExist = stmt.executeQuery("select * from CLIENTS WHERE EMAIL = '" +
                            "" + email + "' AND PASSWORD = '" + password+ "'");

                    if(checkIfUserExist.next())
                    {
                        Client loggedClient = new Client(checkIfUserExist.getString(6),
                                checkIfUserExist.getString(2), checkIfUserExist.getString(3),
                                checkIfUserExist.getString(5), checkIfUserExist.getString(8),
                                checkIfUserExist.getString(4), checkIfUserExist.getInt(7));

                        request.setAttribute("loggedClient", loggedClient);

                        getServletContext().getRequestDispatcher("/loggedClientMainPage.jsp").forward(request, response);


                    } else {
                        getServletContext().getRequestDispatcher("/unknownClient.jsp").forward(request, response);
                    }

                    con.close();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
            else {
                getServletContext().getRequestDispatcher("/unknownClient.jsp").forward(request, response);
            }
        }
    }
}