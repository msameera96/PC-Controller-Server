/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author sameer
 */
class Modules
{
    BufferedReader br;
    Socket socket;
    void keyboardModule()
    {
        
        try{
        Keyboard key = new Keyboard();
        String readIn;
        socket = SocketHandler.getSocket();
        while (true){
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        readIn =br.readLine();
        key.type(readIn); 
        }
        
        }catch(IOException ex)
        {
          JOptionPane.showMessageDialog(null,"Exception occur"+ex,"Error",JOptionPane.ERROR_MESSAGE);  
        }catch(Exception ex)
        {
          JOptionPane.showMessageDialog(null,"Exception occur"+ex,"Error",JOptionPane.ERROR_MESSAGE);  
        }
    }
}

