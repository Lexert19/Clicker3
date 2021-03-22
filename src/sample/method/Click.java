package sample.method;

import java.awt.*;
import java.awt.event.InputEvent;

public class Click extends Method{
    private Robot robot;
    private char mouse;

    public Click(Robot robot, char mouse) {
        this.robot = robot;
        this.mouse = mouse;
    }

    public void run(){
        if(mouse == 'L'){
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }else if(mouse == 'R'){
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        }else if(mouse == 'M'){
            robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
        }

    }
}
