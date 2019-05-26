package hr.fer.oop.meteo;

import java.io.*;
import org.apache.commons.io.IOUtils;
import java.sql.*;
import java.util.*;


public class Database {

    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/meteo";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection conn = null;


    public static boolean connectToDB() {

        int ex = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            ex = 1;
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            ex = 1;
        } finally {
            if (ex == 0)
                return true;
            else
                return false;
        }
    }

    public static void closeDB() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Goodbye!");
    }

    public static boolean checkIfEx(String date) {
        int rt = 0;
        try {
            connectToDB();
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT 1 FROM meteo WHERE date = '" + date + "'");
            if (rset.next()) {
                rt = 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
            if (rt == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static Set<String> getPlaces(String date) {
        Set<String> places = new TreeSet<>();
        try {
            connectToDB();
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM meteo WHERE date = '" + date + "'");
            while (rset.next()) {
                //System.out.println(rset.getString("Rainfall"));
                InputStream is = rset.getAsciiStream("place");
                String value = IOUtils.toString(is);
                //IOUtils.closeStream(is);
                //String value = Character.toString(is);
                System.out.println(value);
                places.add(value);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
            return places;
        }
    }

   /* public boolean addToDB(String date){

    }

    public String[] rainfall(String date, String place){

    }*/

    public static void main(String[] args) {

        System.out.println(checkIfEx("2019-7-1"));
        for (String place : getPlaces("2019-7-1")){
            System.out.println(place);
        }

    }
}
