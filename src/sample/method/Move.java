package sample.method;

import java.awt.*;
import java.awt.event.InputEvent;

public class Move extends Method{
    private int x;
    private int y;
    private Robot robot;

    public Move(Robot robot) {
        this.robot = robot;
    }

    public int run(){
        robot.mouseMove(x,y);
        return 1;
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
