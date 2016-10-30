package com.example.uriel.login1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class DataBaseStatements {
    //Declaration of Public Variables
    public String status = null;
    public String message = null;
    private ConnectionClass connectionClass;

    public void checkUser( String name,
                           String password ) {

        //Check if the user and password are initial
        if (name.equals("") || password.equals(""))
        {
            message = "Insert User and Password";
            status  = "ERROR";
            return;
        }

        //Built SELECT Query for Check the User
        String query, locked;
        query = "SELECT * FROM users WHERE user_id='"
                + name + "' AND user_pass='" + password + "'";

        try {
            //Open Connection
            connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();
            if (con == null) {
                message = "Error in connection with SQL Server";
                status = "ERROR";
            } else {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next())
                {
                    locked = null;
                    locked = rs.getString("locked");
                    if (locked == null){
                        message = "Login successfull";
                        status = "OK";

                    }else{
                        message = "User locked";
                        status = "ERROR";
                    }
                }
                else
                {
                    message = "User or Pass wrong";
                    status = "ERROR";
                }
                con.close();
            }
        } catch (Exception ex){
            message = "Exceptions";
            status = "ERROR";
        }

    };

}
