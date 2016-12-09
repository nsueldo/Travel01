package com.example.uriel.login1.MySQL;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    String ip = "db4free.net";
    String port = "3306";
    String classs = "com.mysql.jdbc.Driver";
    String db = "android808";
    String un = "admin808";
    String password = "123456";
    String error ="";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);

            ConnURL = "jdbc:mysql://" + ip + ":"
                    + port + "/" + db;



            conn = DriverManager.getConnection(ConnURL,un,password);
          //  conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/android808", "admin808", "123456");

        } catch (SQLException se) {
            error = se.getMessage();
            System.out.println(conn);
            se.printStackTrace();
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}

