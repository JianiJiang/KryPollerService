/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import controller.Service;
import controller.Validator;
import java.sql.Timestamp;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Test class for the validator
 * @author JianiJiang
 */
public class ValidatorTest {

    Validator validator;
    
    @Before
    public void setup(){
        validator = Mockito.mock(Validator.class);       
    }
        
    @Test
    public void testValidator(){
        String correct_url = "http://google.com";
        String wrong_url = "1234://google.com";

        Timestamp timestamp = Timestamp.valueOf(java.time.LocalDateTime.now());
        Service correct_service = new Service(0, "test1", correct_url, timestamp, "");
        Service wrong_service = new Service(1, "test2", wrong_url, timestamp, "");

        ArrayList<Service> serviceList = new ArrayList<>();
        serviceList.add(correct_service); 
        serviceList.add(wrong_service);
        
        boolean isValid = validator.checkValidation(serviceList);
        assertEquals(isValid, false);
    }

}
