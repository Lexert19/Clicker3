package sample.method;

import java.awt.*;
import java.awt.event.InputEvent;

public class ClickR extends Method{
    private Robot robot;

    public ClickR(Robot robot) {
        this.robot = robot;
    }

    public void run(){
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }
}
