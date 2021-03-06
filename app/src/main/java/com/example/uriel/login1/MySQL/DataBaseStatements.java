package com.example.uriel.login1.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;


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
            message = "Complete todos los campos";
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
                    locked = rs.getString("locked");
                    if (locked == null){
                        message = "Logueo Exitoso";
                        status = "OK";

                    }else{
                        message = "User locked";
                        status = "ERROR";
                    }
                }
                else
                {
                    message = "Usuario o Contraseña Incorrecta";
                    status = "ERROR";
                }
                con.close();
            }
        } catch (Exception ex){
            message = "Exceptions SQL";
            status = "ERROR";
        }

    };

    public void insertTravel( String source,
                              String target ){
        if (source.equals("") || target.equals("")){
            message = "Ingresar Origen y Destino";
            status = "ERROR";
            return;
        }
        Date datenow = new Date();
        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
        String mydate = formatdate.format(datenow);

        Date timenow = new Date();
        SimpleDateFormat formattime = new SimpleDateFormat("HH:mm:ss");
        String mytime = formattime.format(timenow);



        try {
            connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();
            if (con == null){
                message = "Error in connection with SQL Server";
                status = "ERROR";
            }else {
                String query;
                query = "INSERT INTO travels (travel_source, travel_target, travel_date," +
                        " travel_time,available) " + "VALUES('"+source+"','"+target+"'," +
                        "'"+mydate+"', '"+mytime+"',TRUE);";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                message = "Nuevo Viaje Creado";
                status = "OK";
            }
            con.close();
        }catch (Exception ex){
            message = "Exception";
            status = "ERROR";
        }
    }

    public ArrayList getTravels() {
        ArrayList<String[]> tbl_travels = new ArrayList<String[]>();
        try {
            connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();
            if (con == null){
                message = "Error in connection with SQL Server";
                status = "ERROR";
                return null;
            }

            String query;
            query = "SELECT * FROM travels ORDER BY travel_date DESC LIMIT 5";
            Statement stmt = con.createStatement();
            ResultSet data =stmt.executeQuery(query);
            while (data.next()){
                String[] linea = {null,null,null,null,null};
                linea[0] = data.getString("travel_id");
                linea[1] = data.getString("travel_source");
                linea[2] = data.getString("travel_target");
                linea[3] = data.getString("travel_date");
                linea[4] = data.getString("travel_time");
                tbl_travels.add(linea);
            }
            message = "Viajes Cargados";
            status = "OK";
            con.close();
        } catch (Exception ex){
            message = "Exception";
            status = "ERROR";
        }

        return tbl_travels;
    }

    public boolean deleteTravel(String id){
        try{
            connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();

            if (con == null){
                message = "Error  conection with SQL Server";
                status = "ERROR";
                return false;
            }

            String query = "DELETE FROM travels WHERE travel_id ='"+id+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();

        } catch (Exception ex ){
            message = "Error  connection with SQL Server";
            status = "ERROR";
        }
        return true;
    }

    public  ArrayList getCompanies(){
        ArrayList<String[]> tbl_companies = new ArrayList<String[]>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();

            if (con == null){
                status = "ERROR";
                message ="Error connection with SQL Server";
                return null;
            }

            String query = "SELECT * FROM companies";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String[] row = {null,null};
                if (rs.getBoolean("enabled") == true){
                    row[0] = rs.getString("company_id");
                    row[1] = rs.getString("company_name");
                    tbl_companies.add(row);
                }
            }
            con.close();
        }catch (Exception ex){
            message = "Error  connection with SQL Server: "+ex;
            status = "ERROR";
        }
        return tbl_companies;
    }

    public void insertUser(String name, String surname,
                           String email,String username, String pass, String company){

        if (name.equals("") || username.equals("") ||
                email.equals("") || username.equals("") ||
                        pass.equals("") || company.equals("")){
            message = "Completar todos los campos";
            status = "ERROR";
        }else{

            try{
                ConnectionClass connectionClass = new ConnectionClass();
                Connection con = connectionClass.CONN();

                if (con == null){
                    message = "Error Connection with SQL Server";
                    status = "ERROR";
                }

                String query = "SELECT * FROM users WHERE user_id ='"+username+"';";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (!rs.first()){
                    query = "INSERT INTO users (user_id, user_pass, user_name," +
                            " user_surname, user_email, locked, id_company) " + "VALUES('"+username+"','"+pass+"'," +
                            "'"+name+"', '"+surname+"', '"+email+"', NULL, '"+company+"');";
                    stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    message = "Usuario Registrado";
                    status = "OK";

                }else {
                    message = "Usuario Registrado";
                    status = "ERROR";
                }

                con.close();
            }catch (Exception ex){
                message = "Error  connection with SQL Server: "+ex;
                status = "ERROR";
            }


        }
    }

    public ArrayList travelsAvaliable() {
        ArrayList<String[]> tbl_travels = new ArrayList<String[]>();
        try {
            connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();
            if (con == null){
                message = "Error in connection with SQL Server";
                status = "ERROR";
                return null;
            }

            String query;
            query = "SELECT * FROM travels WHERE available = TRUE";
            Statement stmt = con.createStatement();
            ResultSet data =stmt.executeQuery(query);
            while (data.next()){
                String[] linea = {null,null,null,null,null};
                linea[0] = data.getString("travel_id");
                linea[1] = data.getString("travel_source");
                linea[2] = data.getString("travel_target");
                linea[3] = data.getString("travel_date");
                linea[4] = data.getString("travel_time");
                tbl_travels.add(linea);
            }
            if (tbl_travels.size() > 0){
                message = "Viajes Detectados";
                status = "OK";
            }else{
                message = "No se detectaron viajes";
                status = "NOK";
            }
            con.close();
        } catch (Exception ex){
            message = "Exception";
            status = "ERROR";
        }

        return tbl_travels;
    }

    public void lockTravel(String id_travel){
        try {
            connectionClass = new ConnectionClass();
            Connection con = connectionClass.CONN();
            if (con == null){
                message = "Error in connection with SQL Server";
                status = "ERROR";

            }

            String query;
            query = "UPDATE travels SET available = FALSE WHERE travel_id = '"+id_travel+"';";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

            message = "Viaje Bloqueado";
            status = "OK";

            con.close();
        } catch (Exception ex){
            message = "Exception";
            status = "ERROR";
        }
    }
}
