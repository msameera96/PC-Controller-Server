/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;

/**
 *
 * @author sameer
 */
public class UserDevice {
    String username;
    boolean isadmin;
    String device_name;
    String android_id;
    
    
    UserDevice(String un, boolean admin, String dev_name, String and_id){
    username = un;
    isadmin = admin;
    device_name = dev_name;
    android_id = and_id;
    }
    
    public void setUsername(String uname){username = uname;}
    public String getUsername(){return username;}
    public void setIsAdmin(boolean ad){isadmin=ad;}
    public boolean getIsAdmin(){return isadmin;}
    public void setDeviceName(String dev_name){device_name = dev_name;}
    public String getDeviceName(){return device_name;}
    public void setAndroid_ID(String and_id){android_id = and_id;}
    public String getAndroid_ID(){return android_id;}
    
}
