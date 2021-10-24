/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * DB class for controller.Service, queries are defined and executed in methods here
 * @author JianiJiang
 */
public class ServiceDB {
    private Connection m_conn = null;
    
    public ServiceDB(Connection conn){
        this.m_conn = conn;
    }
    
    public ArrayList<Service> loadData(){
        ArrayList<Service> serviceList = new ArrayList<>();
        try{                
            String query = "SELECT * FROM TbServices";
            Statement statement = this.m_conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            Service service;
            while(rs.next()){
                service = new Service(rs.getInt("serviceid"), rs.getString("service_name"),
                rs.getString("url"), rs.getTimestamp("creation_time"), rs.getString("service_status"));
                serviceList.add(service);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return serviceList;
    }
    
    public void saveData(HashMap<String, ArrayList<Service>> serviceListMap){       
        try{
            this.m_conn.setAutoCommit(false);
            Statement statement = this.m_conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            String insertQuery = "INSERT INTO TbServices(SERVICE_NAME, URL, CREATION_TIME) VALUE(?, ?, ?)";
            String updateQuery = "UPDATE TbServices SET SERVICE_NAME='%s', URL='%s' WHERE SERVICEID=%d";
            String deleteQuery = "DELETE FROM TbServices WHERE SERVICEID=%d";
              
            for(String action: serviceListMap.keySet()){
                switch(action){
                    case "insert":
                        for(Service service: serviceListMap.get(action)){
                            // execute each insert query seperately since it contains timestamp
                            PreparedStatement s = this.m_conn.prepareStatement(insertQuery);
                            s.setString(1, service.getServiceName());
                            s.setString(2, service.getServiceUrl());
                            s.setTimestamp(3, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                            s.execute();
                        }                   
                        break;
                    case "update":
                        // generate queries for each update record, will update them in batch
                        for(Service service: serviceListMap.get(action)){
                            String query = String.format(updateQuery, service.getServiceName(),
                                    service.getServiceUrl(), service.getServiceID());
                            statement.addBatch(query);   
                        } 
                        break;
                    case "delete":
                        // generate queries for each delete record, will update them in batch
                        for(Service service: serviceListMap.get(action)){
                            String query = String.format(deleteQuery, service.getServiceID());
                            statement.addBatch(query);   
                        } 
                        break;
                }
            }
            statement.executeBatch();
            this.m_conn.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateServiceStatus(ArrayList<Service> serviceList){
        try{   
            this.m_conn.setAutoCommit(false);
            // only update the service status, used by poller service
            Statement statement = this.m_conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            String updateQuery = "UPDATE TbServices SET SERVICE_STATUS='%s' WHERE SERVICEID=%d";
            for(Service service: serviceList){
                String query = String.format(updateQuery, service.getServiceStatus(), service.getServiceID());
                statement.addBatch(query);   
            }

            statement.executeBatch();
            this.m_conn.commit();
        }
        catch(Exception ex){
            
        }
    }
}
