package com.example.projektbazydanych;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "VerificationServlet", urlPatterns = "/Verification")
public class VerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");
            Statement stmt = con.createStatement();

            ResultSet checkIfUserExist = stmt.executeQuery("UPDATE CLIENTS SET STATUS='Verified' WHERE EMAIL = '" + request.getParameter("email") + "'");

            request.setAttribute("response", "Udało ci się zweryfikować konto, możesz teraz powrócić na stronę główną");

            getServletContext().getRequestDispatcher("/verification.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("response", e);
            getServletContext().getRequestDispatcher("/verification.jsp").forward(request, response);
        }
    }
}