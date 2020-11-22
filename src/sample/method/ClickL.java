package sample.method;

import java.awt.*;
import java.awt.event.InputEvent;

public class ClickL extends Method{
    private Robot robot;

    public ClickL(Robot robot) {
        this.robot = robot;
    }

    public void run(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
