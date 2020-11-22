package sample.method;

import java.awt.*;
import java.awt.event.InputEvent;

public class ClickM extends Method{
    private Robot robot;

    public ClickM(Robot robot) {
        this.robot = robot;
    }

    public void run(){
        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }
}
