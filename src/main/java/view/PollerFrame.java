/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Validator;
import controller.Listener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.ServiceBL;
import controller.Service;

/**
 * UI frame of the Poller controller.Service, support add/delete/update/refresh and set refresh interval of service status actions.
 * @author JianiJiang
 */
public class PollerFrame extends javax.swing.JFrame {
    private Connection conn = null;
    private DefaultTableModel model = null;
    private Listener listener;
    private Validator validator;
    private ArrayList<Service> serviceList;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd kk:mm:ss");
    private int refreshTime = 10;

    /**
     * Creates new form UIPoller
     */
    public PollerFrame() {
        initComponents();        
        // add listener to the table       
        addListner();
        // do a search to load the data
        Search(); 
       // add a validator of the URL column
        addValidator();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitle = new java.awt.Label();
        btAdd = new javax.swing.JButton();
        btRemove = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        SPServices = new javax.swing.JScrollPane();
        TbServices = new javax.swing.JTable();
        btRefresh = new javax.swing.JButton();
        textTime = new javax.swing.JTextField();
        lbTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbTitle.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        lbTitle.setText("Kry Poller Service App");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btAdd.setText("Add");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btRemove.setText("Remove");
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        TbServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Url", "Creation Time", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SPServices.setViewportView(TbServices);

        btRefresh.setText("Refresh");
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });

        textTime.setText("10");
        textTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTimeActionPerformed(evt);
            }
        });

        lbTime.setText("Refresh interval (s):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(10, Short.MAX_VALUE)
                        .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTime, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btSave, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SPServices)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textTime)
                    .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SPServices, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("pTitle");

        getAccessibleContext().setAccessibleName("FMain");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addListner(){
        this.model = (DefaultTableModel)this.TbServices.getModel();
        this.listener = new Listener(this.serviceList);
        this.model.addTableModelListener((TableModelListener)this.listener);

    }
    
    private void addValidator(){
        this.validator = new Validator(new JTextField());
        this.TbServices.getColumnModel().getColumn(1).setCellEditor(validator);
    }
    
    private void Search(){
        try {
            // set listener to skip the event, since it is updated by process instead of users
            this.listener.setIsAutoRefresh(true);
            // clear the table
            this.model.setRowCount(0);
            
            //fetch data from DB and load it
            ServiceBL servicebl = new ServiceBL();
            serviceList = servicebl.loadData();
            
            Object[] row = new Object[4];
            for(int i=0; i<serviceList.size(); i++){
                row[0] = serviceList.get(i).getServiceName();
                row[1] = serviceList.get(i).getServiceUrl();
                row[2] = dateFormat.format(serviceList.get(i).getCreationTime());
                row[3] = serviceList.get(i).getServiceStatus();
                
                model.addRow(row);
                
            }
            // refresh the listener
            this.listener.setServiceList(this.serviceList);
            this.listener.setModifiedData(new HashMap<>());
            this.listener.setIsDirty(false);
            this.listener.setIsAutoRefresh(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
    }
    
    public void refreshStatus(ArrayList<Service> serviceList){  
        // if the table is dirty (modified), skip refresh the status to avoid messing around the table
        if(!this.listener.isDirty()){
            this.serviceList = serviceList;
            // let the listener to skip the event
            this.listener.setIsAutoRefresh(true);
            // iterate through the list and update the status on the table
            for(int i=0; i<serviceList.size(); i++){
                this.model.setValueAt(serviceList.get(i).getServiceStatus(), i, 3);                   
            }
            // refresh the listener
            this.listener.setServiceList(this.serviceList);
            this.listener.setIsAutoRefresh(false);
            System.out.println("controller.Service status refreshed!");
        }
    }
    
    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // when click Add bottom, we want to add a new row to the table
        Object[] newRow = new Object[4];
        this.model.addRow(newRow);
        // get index of the new row and make it selected
        int iRow = this.TbServices.getRowCount() - 1;
        this.TbServices.setRowSelectionInterval(iRow,iRow);
    }//GEN-LAST:event_btAddActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        // when click remove bottom, we want to remove one or multiple rows
        int[] iRows = this.TbServices.getSelectedRows();
        if(iRows.length == 0){
            JOptionPane.showMessageDialog(null, "No row is selected to be deleted.", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            // sort the rows to be removed, iterate backwards in this Array to safely remove rows
            Arrays.sort(iRows);
            for(int i=iRows.length-1; i>=0; i--){
                this.model.removeRow(iRows[i]);
            }
        }
    }//GEN-LAST:event_btRemoveActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        try{
            // check if the table is dirty and URL is valid, then save data to db
            if (listener.isDirty() && this.validator.checkValidation(this.listener.getServiceList())){
                // save data to db
                ServiceBL servicebl = new ServiceBL();
                servicebl.saveData(this.listener.getModifiedData());
                // do Search to refresh the data
                Search();
                JOptionPane.showMessageDialog(null, "Successfully saved.", "Infomation", JOptionPane.INFORMATION_MESSAGE);               

            }else{
                JOptionPane.showMessageDialog(null, "Save failed due to no change or invalid url.", "Warning", JOptionPane.WARNING_MESSAGE);               
            }
        }
        catch(SQLException ex){
           ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btSaveActionPerformed

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        // check if table is dirty, ask if user want to continue refresh, and the modified data will be lost
        if(this.listener.isDirty()){
            int reply = JOptionPane.showConfirmDialog(null, "You have unsaved change. Do you want to continue without saving?",
                    "Warning", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                // do Search to load the data
                Search();
            }
        }else{
            // do Search to load the data
            Search();
        }
    }//GEN-LAST:event_btRefreshActionPerformed

    private void textTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTimeActionPerformed
        // get the refresh interval time from ui, which should be used in controller.PollerService thread.
        try{
            this.refreshTime = Integer.parseInt(evt.getActionCommand());
        }
        catch(NumberFormatException e){                       
            JOptionPane.showMessageDialog(null, "Please enter a number to the Refresh Time.", "Warning", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_textTimeActionPerformed

    // getter
    public int getRefreshTime() {
        return refreshTime;
    }
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane SPServices;
    private javax.swing.JTable TbServices;
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btRefresh;
    private javax.swing.JButton btRemove;
    private javax.swing.JButton btSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbTime;
    private java.awt.Label lbTitle;
    private javax.swing.JTextField textTime;
    // End of variables declaration//GEN-END:variables
}
