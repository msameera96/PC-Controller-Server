/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;



import java.awt.Dimension;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Sameer
 */
public class ServerConnection {
   public static ServerSocket serverSocket;
   public static Socket socket;
   public static  InputStream inputStream;
   public static  OutputStream outputStream;
   public static  ObjectInputStream objectInputStream;
   public static ObjectOutputStream objectOutputStream = null;
   Power power;
    SocketHandler socketHandler; 
    BufferedReader br;
    char readChar;
    String readLn;
    String readMenu;
    String message, filePath, fileName;
    DBHandler db;
    FileAPI fileAPI = new FileAPI();
    VolumeController volumeController= new VolumeController();
    String android_id ="";
    String auth_username="";
    String auth_pass="";
    String activity_name ="";
    String log_date ="";
    String log_time="";
    
   // DataInputStream dis;
   
    
    MouseKeyboardControl mouseControl;
   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
      ServerConnection()
      {
          
      }
     
    
     void connectionEstablishing(int port) throws IOException
     {
         try{
             
              serverSocket = new ServerSocket(port);
              socketHandler = new SocketHandler ();
              
              MouseKeyboardControl key=new MouseKeyboardControl();
              DateTime dt = new DateTime();
              //System.out.println(dt.getDate());
              //System.out.println(dt.getTime());
                MainMenu.conStatusTextField.setText("Server is up! Waiting for connection...");
                socket = serverSocket.accept();
                socketHandler.setSocket(socket);
                if(socket.isConnected()){
                MainMenu.conStatusTextField.setText("Connected");
                mouseControl = new MouseKeyboardControl();
                inputStream = socket.getInputStream();
               objectInputStream = new ObjectInputStream(inputStream);
               outputStream = socket.getOutputStream();
               objectOutputStream = new ObjectOutputStream(outputStream);
               power = new Power();
               db= new DBHandler();
               
               
                  
              
               while (true) {
                try {
                   // JOptionPane.showMessageDialog(null,"Before recieving msg");
                   String message = (String) objectInputStream.readObject();
                    int keyCode;
                    if (message != null) {
                        switch (message) {
                                                           
                                
                            case "LEFT_CLICK":
                                mouseControl.leftClick();
                                break;
                            case "RIGHT_CLICK":
                               mouseControl.rightClick();
                                break;
                            case "DOUBLE_CLICK":
                                mouseControl.doubleClick();
                                break;
                            case "MOUSE_WHEEL":
                               int scrollAmount = 
                                        (int) objectInputStream.readObject();
                                mouseControl.mouseWheel(scrollAmount);
                                break;
                            case "MOUSE_MOVE":
                                int x = (int) objectInputStream.readObject();
                                int y = (int) objectInputStream.readObject();
                                Point point = MouseInfo.getPointerInfo().getLocation(); 
                                // Get current mouse position
                                float nowx = point.x;
                                float nowy = point.y;
                                mouseControl.mouseMove((int) (nowx + x), (int) (nowy + y));
                                break;
                            case "MOUSE_MOVE_LIVE":
                                // need to adjust coordinates 
                                float xCord = (float) objectInputStream.readObject();
                                float yCord = (float) objectInputStream.readObject();
                                xCord = xCord * screenWidth;
                                yCord = yCord * screenHeight;
                                mouseControl.mouseMove((int) xCord, (int) yCord);
                                break;
                            case "KEY_PRESS":
                               /* keyCode = (int) objectInputStream.readObject();
                                mouseControl.keyPress(keyCode);*/
                                break;
                            case "KEY_RELEASE":
                                keyCode = (int) objectInputStream.readObject();
                                mouseControl.keyRelease(keyCode);
                                break;
                            case "CTRL_ALT_T":
                                mouseControl.ctrlAltT();
                                break;
                            case "CTRL_SHIFT_Z":
                                mouseControl.ctrlShiftZ();
                                break;
                            case "ALT_F4":
                                mouseControl.altF4();
                                break;
                            case "TYPE_CHARACTER": 
                                //handle StringIndexOutOfBoundsException here when pressing soft enter key
                                char ch = ((String) objectInputStream.readObject()).charAt(0);
                                mouseControl.typeCharacter(ch);
                                break;
                            case "TYPE_KEY": 
                                keyCode = (int) objectInputStream.readObject();
                                mouseControl.typeCharacter(keyCode);
                                break;
                            case "LEFT_ARROW_KEY":
                                mouseControl.pressLeftArrowKey();
                                break;
                            
                            case "CTRL_A":
                                mouseControl.ctrlA();
                                break;
                            case "CTRL_B":
                                mouseControl.ctrlB();
                                break;
                            case "CTRL_C":
                                mouseControl.ctrlC();
                                break;
                            case "CTRL_I":
                                mouseControl.ctrlI();
                                break;
                            case "CTRL_S":
                                mouseControl.ctrlS();
                                break;
                            case "CTRL_U":
                                mouseControl.ctrlU();
                                break;
                            case  "CTRL_V":
                                mouseControl.ctrlV();
                                break;
                            case  "CTRL_X":
                                mouseControl.ctrlX();
                                break;
                                
                            case "DOWN_ARROW_KEY":
                                mouseControl.pressDownArrowKey();
                                break;
                            case "RIGHT_ARROW_KEY":
                                mouseControl.pressRightArrowKey();
                                break;
                            case "UP_ARROW_KEY":
                                mouseControl.pressUpArrowKey();
                                break;
                            case "SHIFT_LEFT":
                                mouseControl.shiftLeft();
                                break;
                            case "SHIFT_DOWN":
                                mouseControl.shiftDOWN();
                                break;
                            case "SHIFT_RIGHT":
                                mouseControl.shiftRight();
                                break;
                            case "SHIFT_UP":
                                mouseControl.shiftUp();
                                break;
                                
                            case "F5_KEY":
                                mouseControl.pressF5Key();
                                break;
                            
                            case "Shutdown_PC":
                               power.shutdown();
                                break;
                            case "Restart_PC":
                               power.restart();
                                break;
                            case "SLEEP_PC":
                                power.suspend();
                                break;
                            case "Sign_out":
                              power.lock();
                                break;
                            case "Hibernate_PC":
                              power.hibernate();
                                break;
                           
                            case "SCREENSHOT_REQUEST":
                                new Screenshot().sendScreenshot(objectOutputStream);
                                break;
                            case "FILE_DOWNLOAD_LIST_FILES":
                                filePath = (String) ServerConnection.objectInputStream.readObject();
                                System.out.println("FILE_DOWNLOAD_LIST_FILES");
                                if (filePath.equals("/")) {
                                    filePath = fileAPI.getHomeDirectoryPath();
                                }
                                new SendFileList().sendFilesList(fileAPI, filePath, ServerConnection.objectOutputStream);
                                break;
                            case "FILE_DOWNLOAD_REQUEST":
                                //filePath is complete path including file name
                                filePath = (String) ServerConnection.objectInputStream.readObject();
                                new SendFile().sendFile(filePath, ServerConnection.objectOutputStream);
                                break;
                            case "FILE_TRANSFER_REQUEST":
                                fileName = (String) ServerConnection.objectInputStream.readObject();
                                long fileSize = (long) ServerConnection.objectInputStream.readObject();
                                //not in thread, blocking action
                                new ReceiveFile().receiveFile(
                                        fileName, fileSize, ServerConnection.objectInputStream
                                );
                                break;
                            case "VolumeController":
                                int gain = (int) objectInputStream.readObject();
                                 
                                volumeController.setSystemVolume(gain);
                                break;
                            case "DB_Mob_Detail":
                                String dev_name = (String) objectInputStream.readObject();
                                android_id = (String) objectInputStream.readObject();
                                db.addNewDevice(dev_name, android_id);
                                break;
                            case "DB_User_Insert":
                                String username = (String) objectInputStream.readObject();
                                String pass = (String) objectInputStream.readObject(); 
                                boolean b=false;
                                if(db.getCountRowsClients()==0)
                                b=db.addNewClient(username, pass, true,db.getDeviceID(android_id));
                                else
                                   b=db.addNewClient(username, pass, false,db.getDeviceID(android_id));
                                if(b)
                                {
                                    String reg_msg = "Registered";
                                    ServerConnection.objectOutputStream.writeObject(reg_msg);
                                    ServerConnection.objectOutputStream.flush();
                                }
                                else
                                {
                                    String reg_msg = "Registration_Failed";
                                    ServerConnection.objectOutputStream.writeObject(reg_msg);
                                    ServerConnection.objectOutputStream.flush();
                                }
                                
                                break;
                            case "DB_Login":
                                auth_username = (String) objectInputStream.readObject();
                                auth_pass = (String) objectInputStream.readObject();
                                if(db.loginAuth(auth_username, auth_pass))
                                {
                                    String reg_msg = "Login_Successfully";
                                    ServerConnection.objectOutputStream.writeObject(reg_msg);
                                    ServerConnection.objectOutputStream.flush();
                                    db.addNewSession(db.getUserId(auth_username, auth_pass), dt.getTime(), dt.getDate());
                                }else{
                                    String reg_msg = "Invalid_Username/Password";
                                    ServerConnection.objectOutputStream.writeObject(reg_msg);
                                    ServerConnection.objectOutputStream.flush();
                                }
                                break;
                            
                            case "log_started":
                               activity_name = (String) objectInputStream.readObject();
                               log_date=dt.getDate();
                               log_time = dt.getTime();
                                db.addNewLog(activity_name,log_date , log_time , db.getSessionId(db.getUserId(auth_username, auth_pass)));                              
                                
                                break;
                            case "log_ended":
                                db.addEndTimeOfLog(db.getLogId(activity_name, log_date, log_time, db.getSessionId(db.getUserId(auth_username, auth_pass))), dt.getTime(), dt.getDate());
                                break;
                        }
                    } else {
                         MainMenu. conStatusTextField.setText("Module is invalid");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    socket.close();
                    serverSocket.close();
                    MainMenu. conStatusTextField.setText("Error in Connection");
                    break;
                }
            };
          }
                else
                {
                    JOptionPane.showMessageDialog(null, "Client is not connected");
                }
              
         }catch(Exception ex) 
         {
             JOptionPane.showMessageDialog(null,"Exception occur"+ex,"Error",JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
             
             
                    MainMenu. conStatusTextField.setText("Exception Occur");
                    
                    
             
         }
        
        
        
     }
     
     
    
}
