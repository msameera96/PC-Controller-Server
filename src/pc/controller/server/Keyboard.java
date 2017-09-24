/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;
import java.awt.AWTException;
import java.awt.AWTKeyStroke;
import static java.awt.AWTKeyStroke.getAWTKeyStroke;
import java.awt.Robot;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;
import java.awt.event.KeyEvent;

/**
 *
 * @author M_Sameer
 */
public class Keyboard {

    private final Robot robot;
    int count;

    public Keyboard() throws AWTException {
        this.robot = new Robot();
        count=0;
    }

    public static void main(String[] args) throws Exception {
       // Runtime.getRuntime().exec("notepad.exe");
        //Thread.sleep(3000L);
        Keyboard keyboard = new Keyboard();
        keyboard.type("`1234567890-=[]\\;',./\n");
        keyboard.type("~!@#$%^&*()_+{}|:\"<>?\n");
        keyboard.type("abcdefghijklmnopqrstuvwxyz\n\tABCDEFGHIJKLMNOPQRSTUVWXYZ");
        keyboard.type("\n\n\twh\bat");
    }

    private static AWTKeyStroke getKeyStroke(char c) {
        String upper = "`~'\"!@#$%^&*()_+{}|:<>?";
        String lower = "`~'\"1234567890-=[]\\;,./";

        int index = upper.indexOf(c);
        if (index != -1) {
            int keyCode;
            boolean shift = false;
            switch (c) {
                // these chars need to be handled specially because
                // they don't directly translate into the correct keycode
                case '~':
                    shift = true;
                case '`':
                    keyCode = KeyEvent.VK_BACK_QUOTE;
                    break;
                case '\"':
                    shift = true;
                case '\'':
                    keyCode = KeyEvent.VK_QUOTE;
                    break;
                case '\n':
                    keyCode = KeyEvent.VK_ENTER;
                    break;
                
                default:
                    keyCode = (int) Character.toUpperCase(lower.charAt(index));
                    shift = true;
            }
            return getAWTKeyStroke(keyCode, shift ? SHIFT_DOWN_MASK : 0);
        }
        return getAWTKeyStroke((int) Character.toUpperCase(c), 0);
    }

    
    public void type(CharSequence characters) {
        char character=0;
       int length = characters.length();
        for (int i =count; i<length ; i++) {
            character = characters.charAt(i);
            type(character);       
            
        }count++;
        
        
    }

    public void type(char c) {
        //ms = ms > 0 ? ms : 0;
        
            AWTKeyStroke keyStroke = getKeyStroke(c);
            int keyCode = keyStroke.getKeyCode();
            boolean shift = Character.isUpperCase(c) || keyStroke.getModifiers() == (SHIFT_DOWN_MASK + 1);
            if (shift) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }

            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

            if (shift) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            //if (ms > 0) {
           //     robot.delay(ms);
           // }
        }
    }

