package sample.method;

import java.awt.*;
import java.awt.event.InputEvent;

public class Click_Position extends Method {
    private int x;
    private int y;
    private Robot robot;

    public Click_Position(Robot robot) {
        this.robot = robot;
    }

    public void run(){
        robot.mouseMove(x,y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x < 0)
            this.x = 333;
        else
            this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(y < 0)
            this.y = 333;
        else
            this.y = y;
    }
}
