/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sameer
 */
public class DateTime {
    
    String getDate()
    {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
    String getTime(){
        
        Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    return sdf.format(date);
    }
    
}
