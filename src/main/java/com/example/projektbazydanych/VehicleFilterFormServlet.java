package com.example.projektbazydanych;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "VehicleFilterFormServlet", urlPatterns = "/SearchVehicle")
public class VehicleFilterFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/VehicleFilterForm.jsp").forward(request,response);
    }
}