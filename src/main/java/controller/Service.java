package controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Timestamp;

/**
 * controller.Service class defines the controller.Service object
 * @author JianiJiang
 */
public class Service {
    private int serviceID;
    private String serviceName, serviceUrl, serviceStatus;
    private Timestamp creationTime;

    public Service(int serviceID) {
        this.serviceID = serviceID;
    }
    
    public Service(int serviceID, String serviceName, String serviceUrl, Timestamp creationTime, String serviceStatus) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceUrl = serviceUrl;
        this.creationTime = creationTime;
        this.serviceStatus = serviceStatus;
    }
    // getters and setters
    public int getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
    
}
