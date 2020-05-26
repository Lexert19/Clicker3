package sample.method;

import java.awt.*;
import java.awt.event.InputEvent;

public class Click extends Method{
    private Robot robot;

    public Click(Robot robot) {
        this.robot = robot;
    }

    public void run(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
