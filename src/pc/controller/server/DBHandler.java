/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;


import java.sql.*;
import java.util.ArrayList;

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
    
    boolean getIsAdmin(String username,String pass)
    {
        try{
            String sqlQuery="select isadmin from tbl_user where username = '" + username + "' and pass = '" + pass + "' and isadmin = TRUE;";
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
        }catch(Exception ex){System.out.println("Exception while Client isadmin"+ex);}
        return false;
    }
    
    int getUserId(String username,String pass)
    {
        int user_id=0;
        try{
            String sqlQuery="select user_id from tbl_user where username = '" + username + "' and pass = '" + pass + "';";
            Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);
            while(rs.next()) {
               user_id =  rs.getInt("user_id");
            }            
            
        }catch(Exception ex){System.out.println("Exception while Client Authentication"+ex);}
        return user_id;
    }
    
    
    int getCountRowsClients()
    {
        int count=0;
        try{
        
        String sqlQuery = "select count(*) from tbl_user;";
        Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);            
            rs.next();
               count = rs.getInt("COUNT");
            
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
    int getSessionId( int user_id)
    {
        int ses_id=0;
        try{
        String sqlQuery ="select sess_id from tbl_session where user_id ='" + user_id + "' ;";
        Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);            
            while(rs.next()) {
               ses_id =  rs.getInt("sess_id");
            }
        }catch(Exception ex){System.out.println("Exception while geting sess_id from Session table"+ex);}
        return ses_id;
    }
    
    int getLogId(String act_name, String sessdate, String sesstime, int session_id)
    {
        int log_id=0;
        try{
        String sqlQuery ="select log_id from tbl_log where act_name ='" + act_name + "' AND start_date = '" + sessdate + "' AND start_time = '" + sesstime + "' AND session_id = '" + session_id + "';";
        Statement stmt=con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);            
            while(rs.next()) {
               log_id =  rs.getInt("log_id");
            }
        }catch(Exception ex){System.out.println("Exception while geting log_id from Log table"+ex);}
        
        return log_id;
    }
    
    boolean addNewLog(String act_name, String date, String time, int sess_id)
    {
              
        try{
        String sqlQuery = "INSERT INTO tbl_log( act_name, start_date, start_time, session_id)VALUES ( ?, ?, ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setString(1,act_name);
            preparedStatement.setString(2,date);
            preparedStatement.setString(3,time);
            preparedStatement.setInt(4,sess_id);
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
    
    boolean addNewSession(int user_id, String time, String date)
    {
              
        try{
        String sqlQuery = "INSERT INTO tbl_session( user_id, start_time, start_date) VALUES ( ?, ?, ?);";
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,user_id);
            preparedStatement.setString(2,time);
            preparedStatement.setString(3,date);
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
  
  ArrayList <UserDevice> getDeviceDetails()
  {
      String sql= "SELECT username,isadmin, device_name, android_id from tbl_user as u join tbl_device as d on (u.device_id = d.device_id);";
      ArrayList <UserDevice> userDeviceList = new ArrayList<>();
      try{
          Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            UserDevice ud;
            while(rs.next())
            {
                ud = new UserDevice (rs.getString("username"),rs.getBoolean("isadmin"),rs.getString("device_name"),rs.getString("android_id"));
                userDeviceList.add(ud);
            }
           
      }catch(Exception ex){System.out.println("Exception occur while getting Devices "+ex);}
      return userDeviceList;
  }
  
  ArrayList <UserLogDetails> getLogDetails()
  {
      String sql = "SELECT username, s.start_time, s.start_date, act_name, l.start_date, l.start_time, end_time, "
              + "end_date from tbl_user as u full outer join tbl_session as s on (u.user_id = s.user_id) "
              + "full outer join tbl_log as l on (s.sess_id = l.session_id) full outer join tbl_log_end_dtl as le on (le.log_id = l.log_id);";
      ArrayList <UserLogDetails> userLogList = new ArrayList<>();
      try{
          Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            UserLogDetails uld;
            while(rs.next())
            {
                uld = new UserLogDetails(rs.getString("username"),rs.getString("start_time"),rs.getString("start_date"),rs.getString("act_name"),rs.getString("start_date"),rs.getString("start_time"),rs.getString("end_time"),rs.getString("end_date"));
                userLogList.add(uld);
            }
          
      }catch(Exception ex){System.out.println("Exception occur while getting Log details "+ex);}
      return userLogList;
  }
    
    
}
