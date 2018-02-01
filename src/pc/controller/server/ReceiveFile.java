/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
/**
 *
 * @author sameer
 */
public class ReceiveFile {
    
    
     public void receiveFile(final String fileName, long fileSize, 
            ObjectInputStream objectInputStream) {
        FileOutputStream fos = null;
        FileAPI fileApi = new FileAPI();
        String path = fileApi.getHomeDirectoryPath();
        path = path + "/PCController/" + fileName;
        File file = new File(path);
        File dirs = new File(file.getParent());
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        try {
            fos = new FileOutputStream(file);
            byte buffer[] = new byte[4096];
            int read = 0;
            long totalRead = 0;
            int remaining = (int) fileSize;
            while ((read = objectInputStream.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
              // System.out.println("Receive Progress: " + ((totalRead * 100) / fileSize));
                fos.write(buffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ServerConnection serverCon  = new ServerConnection();
           serverCon.resetConnection();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                ServerConnection serverCon  = new ServerConnection();
           serverCon.resetConnection();
                e.printStackTrace();
            }
        }
    }
    
}
