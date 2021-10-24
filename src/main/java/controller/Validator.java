package controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * controller.Validator class verifies the URL column when the cell is finished editing
 * @author JianiJiang
 */
public class Validator extends DefaultCellEditor{
    // url regex, start with http or https, can end with word or char except some chars like ?!:,.;
    private final String urlRegex = "https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private JTextField textField;
    
    public Validator(JTextField textField){
        super(textField);
        this.textField = textField;
    }
   
    @Override
    public boolean stopCellEditing(){
        try{
            String text = String.valueOf(textField.getText());
            Pattern pattern = Pattern.compile(urlRegex);
            Matcher matcher = pattern.matcher(text);
            if(!matcher.matches()){
                throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException ex){           
            JOptionPane.showMessageDialog(null, "The service url is invalid!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return super.stopCellEditing();
    }

    public boolean checkValidation(ArrayList<Service> serviceList){
        // loop through the list of services to check every URL is valid
        // check this validation when user try to save to DB
        for(Service service: serviceList){               
            String text = String.valueOf(service.getServiceUrl());
            Pattern pattern = Pattern.compile(urlRegex);
            Matcher matcher = pattern.matcher(text);
            if(!matcher.matches()){
                // once find it invalid, directly return false
                return false;
            }
        }
        return true;
    }
    

}
