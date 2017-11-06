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
   // DataInputStream dis;
   
    
    MouseKeyboardControl mouseControl;
   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
      ServerConnection()
      {
          
      }
     
    
     void connectionEstablishing(int port)
     {
         try{
              serverSocket = new ServerSocket(port);
              socketHandler = new SocketHandler ();
              
              MouseKeyboardControl key=new MouseKeyboardControl();
          
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
                            case "DOWN_ARROW_KEY":
                                mouseControl.pressDownArrowKey();
                                break;
                            case "RIGHT_ARROW_KEY":
                                mouseControl.pressRightArrowKey();
                                break;
                            case "UP_ARROW_KEY":
                                mouseControl.pressUpArrowKey();
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
             
             
                    MainMenu. conStatusTextField.setText("Error in Connection");
                    
             
         }
        
        
        
     }
     
     
    
}
