package com.example.projektbazydanych;
import java.sql.*;
class OracleCon{
    public static void main(String args[]){
        try{

            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB","homeuser","soloQUita1");


            Statement stmt=con.createStatement();


            ResultSet rs=stmt.executeQuery("select * from CLIENTS");
            while(rs.next())
                System.out.println(rs.getString(2));


            con.close();

        }catch(Exception e){ System.out.println(e);}

    }
}