/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create the connection with MySQL DB
 * @author JianiJiang
 */
public class DBConnection {
    private Connection conn = null;
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String userName = "root";
    private final String userPassword = "1234";
    private final String dbName = "sys";
    
    public DBConnection(){
        startConnection();
    }
    
    private void startConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url+dbName, userName, userPassword);

            if(conn != null){
                System.out.println("DB connection setup seccuss.");
            }
                 
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println("DB Connection Exception:"+ e);
        }
        
    }
    
    //getter for db connection
    public Connection getConn(){
        return conn;
    }
}
