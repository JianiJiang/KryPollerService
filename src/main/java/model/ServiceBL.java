/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import controller.Service;

/**
 * Concrete class for service, it defines the methods that will interact with DB, and manage the db connection
 * @author JianiJiang
 */
public class ServiceBL {
    private Connection conn;
    private ServiceDB servicedb;
    
    // constructor of ServiceBL class using connection generated in inner method.
    public ServiceBL() {
        setupDBConnection();
        this.servicedb = new ServiceDB(this.conn);
    }
    
    private void setupDBConnection(){
        if (this.conn==null){
            DBConnection dbConn = new DBConnection();
            this.conn = dbConn.getConn();         
        }
    }
    public void saveData(HashMap<String, ArrayList<Service>> serviceMap) throws SQLException{
        // save data to db
        try{            
            this.servicedb.saveData(serviceMap);
            System.out.println("Successfully saved data.");
        }
        finally{
            this.conn.close();
        }
    }
    
    public ArrayList<Service> loadData() throws SQLException{
        ArrayList<Service> serviceList = new ArrayList<>();
        try{
            serviceList = servicedb.loadData();
            System.out.println("Successfully loaded data.");

        }
        finally{
            this.conn.close();
        }
        return serviceList;
    }
    
    public void updateServiceStatus(ArrayList<Service> serviceList) throws SQLException{
        try{
            this.servicedb.updateServiceStatus(serviceList);
            System.out.println("Successfully saved service status.");
        }
        finally{
            this.conn.close();
        }
    }
}
