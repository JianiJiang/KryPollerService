package controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *  controller.Listener listens to the table change, and keep track of modified data.
 * @author JianiJiang
 */
public class Listener implements TableModelListener{


    private boolean isDirty = false;
    private boolean isAutoRefresh = false;
    private ArrayList<Service> serviceList;
    private HashMap<String, ArrayList<Service>> modifiedData = new HashMap<>();
    
    public Listener(ArrayList<Service> serviceList){
        this.serviceList = serviceList;
    }
    
    @Override
    public void tableChanged(TableModelEvent e){
        // if it is auto refresh (table change caused not by users' action), the table change will no be kept track
        if(!this.isAutoRefresh){
            DefaultTableModel model = (DefaultTableModel)e.getSource();
            int firstRow = e.getFirstRow();
            int lastRow = e.getLastRow();
            // if multiple rows been changed at a time, loop through each of them
            for (int row = Math.min(firstRow, lastRow); row <= Math.max(firstRow, lastRow); row++) {
                Service serviceRow;
                switch (e.getType()) {                            
                    case TableModelEvent.DELETE:
                        serviceRow = serviceList.get(row);
                        if(serviceRow.getServiceID() != -1){
                            // service id is not -1, means it is not a new row
                            // we can safelt remove it
                            if(modifiedData == null || !modifiedData.containsKey("delete")){
                                ArrayList<Service> serviceList = new ArrayList<>();
                                serviceList.add(serviceRow);
                                modifiedData.put("delete", serviceList);
                            }else{
                                modifiedData.get("delete").add(serviceRow);
                            }
                        
                            // check if this row is updated before, then we don't need to do update action
                            // remove the row from 'update' list
                            if(modifiedData.containsKey("update") && modifiedData.get("update").contains(serviceRow)){
                                modifiedData.get("update").remove(serviceRow);                            
                            }
                       
                        }else{
                            // service id = -1, just need to remove it from the Map of modifiedData
                            modifiedData.get("insert").remove(serviceRow);
                        }
                        // keep the data list updated
                        serviceList.remove(serviceRow);
                        // mark the isDirty = true, means it has been modified by user
                        this.isDirty = true;
                        break;
                    case TableModelEvent.INSERT:
                        // give serviceID= -1 for new row
                        serviceRow = new Service(-1);
                        serviceList.add(serviceRow);
                        if(modifiedData == null || !modifiedData.containsKey("insert")){
                            ArrayList<Service> serviceList = new ArrayList<>();
                            serviceList.add(serviceRow);
                            modifiedData.put("insert", serviceList);
                        }else{
                            modifiedData.get("insert").add(serviceRow);
                        }
                    
                        this.isDirty = true;
                        break;
                    case TableModelEvent.UPDATE:
                        serviceRow = serviceList.get(row);
                        // update the data in this service object
                        // note that only name and URL is allowed to be modified by user
                        serviceRow.setServiceName((String)model.getValueAt(row, 0));
                        serviceRow.setServiceUrl((String)model.getValueAt(row, 1));

                        if(serviceRow.getServiceID() != -1){
                            // service id is not -1, means it is not a new row
                            // can safely add it to the 'update' list
                            if(modifiedData == null || !modifiedData.containsKey("update")){
                                ArrayList<Service> serviceList = new ArrayList<>();
                                serviceList.add(serviceRow);
                                modifiedData.put("update", serviceList);
                            }else{
                                if(!modifiedData.get("update").contains(serviceRow)){
                                    modifiedData.get("update").add(serviceRow);
                                }
                            }
                        }
                        // else if service id = -1, it is a new row
                        // the object has updated already, no need to do any action here.
                                        
                        this.isDirty = true;
                        break;
                    default:
                        break;                        
                }                   
            }                  
        }   
    }

    // getters and setters
    public boolean isDirty() {
        return isDirty;
    }

    public void setIsDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    public ArrayList<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public HashMap<String, ArrayList<Service>> getModifiedData() {
        return modifiedData;
    }

    public void setModifiedData(HashMap<String, ArrayList<Service>> modifiedData) {
        this.modifiedData = modifiedData;
    }

    public void setIsAutoRefresh(boolean isAutoRefresh) {
        this.isAutoRefresh = isAutoRefresh;
    }    
}
