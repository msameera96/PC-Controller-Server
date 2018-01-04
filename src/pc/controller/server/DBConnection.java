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
import java.sql.*;
public class DBConnection {
    
      private Connection connection;
      Connection getConncetion()
      {
          try{
              Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pc_controller_db", "postgres", "sameer");
          }catch(Exception ex)
          {System.out.println("Exception while DB Connection "+ex);}
          return connection;
      }
    
    
    
}
