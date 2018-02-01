/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author sameer
 */
public class CommandPrompt {
    ServerConnection servCon;
    CommandPrompt(){servCon = new ServerConnection();}
    
     public void execCmd(String dosCommand){
        //dosCommand = "cmd /c dir /s";
        final String cmd ="cmd /c ";
      final String location = "C:\\WINDOWS\\system32 ";
      try {
         final Process process = Runtime.getRuntime().exec(
            cmd+dosCommand + " " /*+ location*/);
        /* final InputStream in = process.getInputStream();*/
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

         
         int ch;
         String str;
         String str2="";
         //String str[];
         while((str = stdInput.readLine()) != null) {
             //char cr =(char) ch;
            // String str=Character.toString(cr);
            str2+=str+'\n';
           
          //System.out.print((char)ch);
            //str[ch]=char;
         }
         ServerConnection.objectOutputStream.writeObject(str2);
                                    ServerConnection.objectOutputStream.flush();
                                   // System.out.print(str2);
      } catch (IOException e) {
         e.printStackTrace();
         servCon.resetConnection();
      }
    }
        
        
        /*try{     
        Runtime rt = Runtime.getRuntime();
//String[] commands = {"system.exe","-get t"};
Process proc = rt.exec(dosCommand);


BufferedReader stdError = new BufferedReader(new 
     InputStreamReader(proc.getErrorStream()));

// read the output from the command
System.out.println("Here is the standard output of the command:\n");
String s = null;

while ((s = stdInput.readLine()) != null) {
     //String str=Character.toString(cr);
            ServerConnection.objectOutputStream.writeObject(s);
                                    ServerConnection.objectOutputStream.flush();
    //System.out.println(s);
}

// read any errors from the attempted command
System.out.println("Here is the standard error of the command (if any):\n");
while ((s = stdError.readLine()) != null) {
    System.out.println(s);
}
}catch(Exception ex){
    ex.printStackTrace();
}}*/
}
    

