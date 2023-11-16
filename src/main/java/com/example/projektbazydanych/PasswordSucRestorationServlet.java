package com.example.projektbazydanych;

import java.io.*;
import java.net.InetAddress;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PasswordSucRestorationServlet", urlPatterns = "/password_restored")
public class PasswordSucRestorationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}