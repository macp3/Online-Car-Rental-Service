package com.example.projektbazydanych;

import java.io.*;
import java.net.InetAddress;
import java.sql.*;
import java.util.Objects;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PasswordSucRestorationServlet", urlPatterns = "/password_restored")
public class PasswordSucRestorationServlet extends HttpServlet {
    private String email;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("PASSWORD");
        String confirmPassword = request.getParameter("CONFIRMPASSWORD");

        if (password.equals(confirmPassword)) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");

                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");

                String query1 = "UPDATE CLIENTS SET PASSWORD = ?, RESTORECODE=null WHERE EMAIL=?";
                String query2 = "UPDATE EMPLOYEES SET PASSWORD = ?, RESTORECODE=null WHERE EMAIL=?";
                String query3 = "UPDATE COMP_ADMINS SET PASSWORD = ?, RESTORECODE=null WHERE EMAIL=?";
                PreparedStatement stmt1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                stmt1.setString(1, password);
                stmt1.setString(2, email);
                stmt1.executeQuery();
                stmt1.close();
                PreparedStatement stmt2 = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
                stmt2.setString(1, password);
                stmt2.setString(2, email);
                stmt2.executeQuery();
                stmt2.close();
                PreparedStatement stmt3 = con.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
                stmt3.setString(1, password);
                stmt3.setString(2, email);
                stmt3.executeQuery();
                stmt3.close();
                con.close();

                request.setAttribute("error", "Hasło zostało zmienione");
                getServletContext().getRequestDispatcher("/passwordSucRestoration.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/passwordSucRestoration.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Hasła nie są takie same");
            getServletContext().getRequestDispatcher("/passwordSucRestoration.jsp").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        email = request.getParameter("email");
        String codeString = request.getParameter("code");

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");

            String query = "select CA.RESTORECODE, CLIENTS.RESTORECODE, E.RESTORECODE  from CLIENTS FULL JOIN HOMEUSER.COMP_ADMINS CA on CLIENTS.EMAIL = CA.EMAIL FULL JOIN HOMEUSER.EMPLOYEES E on CA.EMAIL = E.EMAIL WHERE CLIENTS.EMAIL = ? OR CA.EMAIL = ? OR E.EMAIL = ?";
            PreparedStatement stmt1 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, email);
            stmt1.setString(2, email);
            stmt1.setString(3, email);

            ResultSet checkIfClientExist = stmt1.executeQuery();

            if (checkIfClientExist.next()) {
                if ((Objects.equals(checkIfClientExist.getString(1), codeString) || (Objects.equals(checkIfClientExist.getString(2), codeString) )|| (Objects.equals(checkIfClientExist.getString(3), codeString) ))&& codeString != null) {
                    response.setHeader("email", email);
                    getServletContext().getRequestDispatcher("/passwordRestoreFormFinal.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Niepoprawny adres email");
                    getServletContext().getRequestDispatcher("/passwordSucRestoration.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Niepoprawny adres email");
                getServletContext().getRequestDispatcher("/passwordSucRestoration.jsp").forward(request, response);
            }
            con.close();
        } catch (Exception e) {
            request.setAttribute("error", e);
        }

    }
}