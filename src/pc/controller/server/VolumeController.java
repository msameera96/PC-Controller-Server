package pc.controller.server;

import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;


/**
 *
 * @author nobeen
 */
public class VolumeController {
    String nircmdFilePath;
    VolumeController()
    {
        nircmdFilePath="C:\\Windows\\System32\\nircmd.exe";
    }
    public void setSystemVolume(int volume){
    if(volume < 0 || volume > 100)
    {
        throw new RuntimeException("Error: " + volume + " is not a valid number. Choose a number between 0 and 100");
    }

    else
    {
        double endVolume = 655.35 * volume;

        Runtime rt = Runtime.getRuntime();
        Process pr;
        try 
        {
            pr = rt.exec(nircmdFilePath + " setsysvolume " + endVolume);
            pr = rt.exec(nircmdFilePath + " mutesysvolume 0");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
   /*
  FloatControl control;  
  Port outputLine;
  javax.sound.sampled.Port.Info source;
 
    
    void setVolumeRuntime() throws IOException
    {
        runtime = Runtime.getRuntime();
       runtime.exec("sndvol.exe -f");
    }
   

   public void setGain(float gain) throws IOException{   
          
       
       source = Port.Info.SPEAKER;
       //JOptionPane.showMessageDialog(null, " "+gain);
   if (gain != -1) {
        if ((gain < 0) || (gain > 1)) {
            throw new IllegalArgumentException("Volume must be between 0.0 and 1.0");
        }
    }
    try{
     outputLine = (Port) AudioSystem.getLine(source);
            outputLine.open();
    ;
    if (outputLine == null) {
        return;
    }
    try {
        control = (FloatControl) outputLine.getControl(FloatControl.Type.VOLUME);
        if (gain == -1) {
            control.setValue(0.0f);
        } else {
            float max = control.getMaximum();
            // negative values all seem to
            float min = control.getMinimum();
            // be zero?
            float range = max - min;
           // control.setValue(min + (range * gain));
            control.setValue(gain);
        }
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    }
}
    catch(Exception ex)
   {}
       }
    */
}
