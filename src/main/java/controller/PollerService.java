package controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import model.ServiceBL;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * controller.PollerService class gets service URLs from DB, does HTTP GET requests, and updates data in DB
 * @author JianiJiang
 */
public class PollerService {
    // one instance, reuse
    private CloseableHttpClient httpclient;
    private ArrayList<Service> serviceList = new ArrayList<>();
    
    public PollerService() {
        this.httpclient = HttpClients.createDefault();
        System.out.println("Apache Http client created.");
    }
    
    public ArrayList<Service> runPoller(){
        getServiceUrl();
        performHTTPGet();
        updateData();
        return this.serviceList;
    }
    private void getServiceUrl(){
        try {
            ServiceBL servicebl = new ServiceBL();
            this.serviceList = servicebl.loadData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void performHTTPGet(){
        if(this.serviceList.size()>0){
            System.out.println("Start sending HTTP GET requests.");
            this.httpclient = HttpClients.createDefault();
            HttpGet httpGet;
            String response;
            for(Service service: this.serviceList){
                httpGet = new HttpGet(service.getServiceUrl());
                // http client excutes the httpGet request
                try(CloseableHttpResponse reply = this.httpclient.execute(httpGet)){
                    // get the status code. Set status to 'OK' only when status code is 200.
                    int status = reply.getStatusLine().getStatusCode();
                    switch(status){
                        case HttpStatus.SC_OK:
                            response = "OK";
                            break;
                        default: 
                            response = "FAIL";
                            break;
                    }
                } catch (Exception ex) {
                    // when no connection or timeout, set the status to 'FAIL'
                    response = "FAIL";
                }
                // update the service object of the status
                service.setServiceStatus(response);
            }
        }
    }
    
    private void updateData(){
        if(this.serviceList.size()>0){
            System.out.println("Start saving service status to DB.");
            try {
                ServiceBL servicebl = new ServiceBL();
                servicebl.updateServiceStatus(this.serviceList);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
