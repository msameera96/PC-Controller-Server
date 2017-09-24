/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;


import java.awt.Robot;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Sameer
 */
public class ServerConnection {
    ServerSocket serverSocket;
    Socket socket;
    char readChar;
    int count=0;
    String readIn;
    Robot robot=null;
    SocketHandler socketHandler=null; 
    BufferedReader br;
      ServerConnection()
    {}
     void connectionEstablishing(int port)
     {
         try{
             socketHandler = new SocketHandler ();
             robot = new Robot();
             
             serverSocket = new ServerSocket(port);
             
             JOptionPane.showMessageDialog(null,"Server is up!\nwaiting for connection");
                Socket client = serverSocket.accept();
                if(client.isConnected()){
                JOptionPane.showMessageDialog(null,"A client has connected.");
                socketHandler.setSocket(socket);
                while(true){
                 br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                readIn =br.readLine();                
                JOptionPane.showMessageDialog(null,""+readIn);
                Modules mod = new Modules ();
                        
                switch (readIn)
                {
                    case "cmd":
                        break;
                    case "file":
                        break;
                    case "key":
                        mod.keyboardModule();
                        break;
                    case "mouse":
                        break;
                    case "power":
                        break;
                    case "ppt":
                        break;
                    case "desk":
                        break;
                    case "vol":
                        break;
                    case "word":
                        break;
                }
                }
                
                }
                else
                {
                       JOptionPane.showMessageDialog(null,"Connection Failed.","Inane error",JOptionPane.ERROR_MESSAGE); 
                        }
                
                
                /*
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())); 
                bw.write("This is a message from the Server");
                bw.newLine();
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream())); 
                JOptionPane.showMessageDialog(null,"Message From client"+br.readLine());*/
              
         }catch(Exception ex)
         {
             JOptionPane.showMessageDialog(null,"Exception occur"+ex,"Error",JOptionPane.ERROR_MESSAGE);
         }
        
        
        
     }
    
}
