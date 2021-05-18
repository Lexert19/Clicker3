package sample.method;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Press extends Method{
    private String button;
    private Robot robot;

    public Press(Robot robot) {
        this.robot = robot;
    }

    public int run(){
        if(button.toUpperCase().equals("SPACE")){
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            return 1;
        }else if(button.toUpperCase().equals("ESC")){
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return 1;
        }
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(button.charAt(0)));
        robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(button.charAt(0)));
        return 1;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
