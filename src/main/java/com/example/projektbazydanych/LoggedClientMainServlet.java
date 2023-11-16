package com.example.projektbazydanych;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.example.projektbazydanych.*;

@WebServlet(name = "LoggedClientMainServlet", urlPatterns = "/clientMainServlet")
public class LoggedClientMainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getAttribute("loggedClient") == null)
        {
            getServletContext().getRequestDispatcher("/start").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/loggedClientMainPage.jsp").forward(request, response);
        }
    }
}