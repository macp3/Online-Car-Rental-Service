package com.example.projektbazydanych;

import java.io.*;
import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String EMAIL = request.getParameter("EMAIL");
        String FIRSTNAME = request.getParameter("FIRSTNAME");
        String LASTNAME = request.getParameter("LASTNAME");
        String PASSWORD = request.getParameter("PASSWORD");
        String CONFIRMPASSWORD = request.getParameter("CONFIRMPASSWORD");
        String PHONENUMBER = request.getParameter("PHONENUMBER");
        String BILLINGADDRESS = request.getParameter("BILLINGADDRESS");
        String PREFFEREDPAYMENTIDstring = request.getParameter("PREFFEREDPAYMENTID");

        int PREFFEREDPAYMENTID = Integer.parseInt(PREFFEREDPAYMENTIDstring);

        if (EMAIL == null || FIRSTNAME == null || LASTNAME == null || PASSWORD == null
                || CONFIRMPASSWORD == null || PHONENUMBER == null) {
            request.setAttribute("error", "Nie wszystkie obowiązkowe pola zostały uzupełnione");
            doGet(request, response);
        } else {
            if (!PASSWORD.equals(CONFIRMPASSWORD)) {
                request.setAttribute("error", "Hasła się nie pokrywają");
                doGet(request, response);
            } else {
                Client client = new Client(EMAIL, FIRSTNAME, LASTNAME, PASSWORD, PHONENUMBER, BILLINGADDRESS, PREFFEREDPAYMENTID);
                try {
                    Class.forName("oracle.jdbc.OracleDriver");

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "homeuser", "soloQUita1");
                    Statement stmt = con.createStatement();

                    ResultSet checkIfUserExist = stmt.executeQuery("select * from CLIENTS WHERE EMAIL = '" + client.getEMAIL() + "'");


                    if (checkIfUserExist.next()) {
                        request.setAttribute("error", "Taki użytkownik już istnieje w bazie");
                        con.close();
                        doGet(request, response);
                    } else {
                        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

                        String SQL = "INSERT INTO CLIENTS(CLIENTID, FIRSTNAME, LASTNAME, " +
                                "BILLINGADDRESS, PASSWORD, EMAIL, PREFFEREDPAYMENTID, PHONENUMBER, ENTRYDATE, STATUS) " +
                                "VALUES (CLIENT_ID_SEQUENCE.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

                        pstmt.setString(1, client.getFIRSTNAME());
                        pstmt.setString(2, client.getLASTNAME());
                        pstmt.setString(3, client.getBILLINGADDRESS());
                        pstmt.setString(4, client.getPASSWORD());
                        pstmt.setString(5, client.getEMAIL());
                        pstmt.setInt(6, client.getPREFFEREDPAYMENTID());
                        pstmt.setString(7, client.getPHONENUMBER());
                        pstmt.setString(8, timeStamp);
                        pstmt.setString(9, "Waiting");

                        int affectedRows = pstmt.executeUpdate();

                        con.close();

                        if (affectedRows > 0) {
                            new SendEmail().sendMail("Witamy na pokładzie!", """
                                    Jesteś od teraz użytkownikiem SUVami!
                                                                        
                                    Aby zweryfikować swoje konto kliknij w poniższy link:
                                    http://localhost:8080/Verification""" + "?email=" + client.getEMAIL() + """
                                                                        
                                    Pozdrawiamy
                                    Zespół SUVami!
                                    """, client.getEMAIL());
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    request.setAttribute("error", e);
                    doGet(request, response);
                }
            }
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

}