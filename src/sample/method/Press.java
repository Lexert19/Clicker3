package sample.method;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Press extends Method{
    private String button;
    private Robot robot;

    public Press(Robot robot) {
        this.robot = robot;
    }

    public void run(){
        if(button.toUpperCase().equals("SPACE")){
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            return;
        }
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(button.charAt(0)));
        robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(button.charAt(0)));
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
