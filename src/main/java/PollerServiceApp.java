/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import controller.PollerService;
import controller.Service;
import model.TableCreation;
import view.PollerFrame;

/**
 * Main class of the PollerServiceApp
 * @author JianiJiang
 */
public class PollerServiceApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // check if table exists in Mysql, otherwise create the table
        TableCreation tableCreation = new TableCreation();
        tableCreation.TableSetup();
        // initial the UI
        PollerFrame pollerFrame = new PollerFrame();
        pollerFrame.setVisible(true);
        System.out.println("GUI initialized.");
        
        // start a new thread for Poller controller.Service
        PollerService pollerService = new PollerService();
        Thread pollerThread;
        pollerThread = new Thread(){
            @Override
            public void run(){
                try{
                    ArrayList<Service> serviceList;
                    while(true){
                        //the refresh interval time is get from the text field from UI                        
                        Thread.sleep(pollerFrame.getRefreshTime()*1000);
                        serviceList = pollerService.runPoller();
                        //update the UI of the service status
                        pollerFrame.refreshStatus(serviceList);
                    }                    
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        pollerThread.start();
    }
    
}
