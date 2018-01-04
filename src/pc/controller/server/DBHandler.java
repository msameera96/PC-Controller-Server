/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;


import java.sql.*;

/**
 *
 * @author sameer
 */
public class DBHandler {
    Connection con;
    DBHandler(){
     con = new DBConnection().getConncetion();
    }
    
    boolean addNewClient(String username, String pass,  boolean isAdmin, int dev_id)
    {
        try{
        String sqlQuery = "INSERT INTO tbl_user( username, pass, isadmin, device_id) VALUES ( ?, ?, ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,pass);
            preparedStatement.setBoolean(3,isAdmin);
            preparedStatement.setInt(4,dev_id);
           int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            else
                return false;
            
    }catch(Exception ex){System.out.println("Exception while inserting data into user table"+ex);}
        return false;
        
    }
    boolean loginAuth(String username,String pass)
    {
        try{
            String sqlQuery="select username, pass from tbl_user where username = '" + username + "' and pass = '" + pass + "';";
            Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);
            int count =0;
            while(rs.next()) {
                count++;
            }
            if(count ==1)
                return true;
            else
                return false;
        }catch(Exception ex){System.out.println("Exception while Client Authentication"+ex);}
        return false;
    }
    
    
    int getCountRowsClients()
    {
        int count=0;
        try{
        
        String sqlQuery = "select count(*) from tbl_user;";
        Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);            
            while(rs.next()) {
               count++;
            }
        }catch(Exception ex){System.out.println("Exception while getting numbers of counts from user table"+ex);}
        return count;
        
    }
    boolean addNewDevice(String dev_name, String android_id)
    {
              
        try{
        String sqlQuery = "INSERT INTO tbl_device( device_name, android_id) VALUES ( ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setString(1,dev_name);
            preparedStatement.setString(2,android_id);
           int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            else
                return false;
            
    }catch(Exception ex){System.out.println("Exception while inserting data into Device table"+ex);}
        return false;
    }
    
    
    
    int getDeviceID(String android_id)
    {
        int dev_id =0;
        try{
        String sqlQuery ="select device_id from tbl_device where android_id ='" + android_id + "';";
        Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);            
            while(rs.next()) {
               dev_id =  rs.getInt("device_id");
            }
        }catch(Exception ex){System.out.println("Exception while geting dev_id from Device table"+ex);}
       return dev_id; 
    }
    
    boolean addNewLog(String act_name, String date, String time)
    {
              
        try{
        String sqlQuery = "INSERT INTO tbl_log( act_name, start_date, start_time)VALUES ( ?, ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setString(1,act_name);
            preparedStatement.setString(2,date);
            preparedStatement.setString(3,time);
           int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            else
                return false;
            
    }catch(Exception ex){System.out.println("Exception while inserting data into Log table"+ex);}
        return false;
    }
    
    boolean addEndTimeOfLog(int log_id, String time, String date)
    {
              
        try{
        String sqlQuery = "INSERT INTO tbl_log_end_dtl( log_id, end_time, end_date) VALUES ( ?, ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,log_id);
            preparedStatement.setString(2,time);
            preparedStatement.setString(3,date);
           int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            else
                return false;
            
    }catch(Exception ex){System.out.println("Exception while inserting data into Log_Ending_Time table"+ex);}
        return false;
    }
    
    boolean addNewSession(int user_id,int log_id, String time, String date)
    {
              
        try{
        String sqlQuery = "INSERT INTO tbl_session( user_id, log_id, start_time, start_date) VALUES ( ?, ?, ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(2,log_id);
            preparedStatement.setString(3,time);
            preparedStatement.setString(4,date);
           int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
            else
                return false;
            
    }catch(Exception ex){System.out.println("Exception while inserting data into session table"+ex);}
        return false;
    }
    
    
  boolean userAuth(String username, String pass)
  {
      try {
            
            String sqlQuery  = "select * from tbl_user where username='" + username + "'" + "AND pass='" + pass + "'";
            Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);
            int count =0;

            while(rs.next())
            {
                count++;

            }
            if(count ==1)
            {
                return true;
            }
            else
                return false;


        }catch (Exception ex)
        {
            System.out.println(" Exception while user Authentication = " + ex);
        }
        return false;
  }
    
    
}
