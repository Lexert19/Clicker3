package sample;

import sample.method.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class PresserClicker implements Runnable {
    private BlockingQueue queue;
    private Robot robot = new Robot();

    private List<If> ifs = new ArrayList<>();
    private List<Loop> loops = new ArrayList<>();

    public PresserClicker(BlockingQueue queue) throws AWTException {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            if (Data.updateSet.get()) {
                Data.updateSet.set(false);
                ifs.clear();
                loops.clear();
                createSetFromText(new StringBuffer(Data.textSet));
            }

            if (Data.active.get()) {
                try {
                    String clickedButton = "";
                    if (!queue.isEmpty())
                        clickedButton = (String) queue.take();
                    for (If oneIf : ifs) {
                        oneIf.activate(clickedButton);
                        oneIf.run();
                    }
                    for (Loop loop : loops) {
                        loop.activate(clickedButton);
                        loop.run();
                    }
                } catch (Exception e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    public Method createMethod(String line) {
        if (line.contains("-wait:")) {
            Wait wait = new Wait();
            wait.setTime(Integer.parseInt(readValue(line)));
            return wait;

        } else if (line.contains("-press:")) {
            Press press = new Press(robot);
            press.setButton(readValue(line));
            return press;

        }
        else if (line.contains("-move:")) {
            Move click = new Move(robot);
            click.setX(getX(line));
            click.setY(getY(line));
            return click;

        }
        else if (line.contains("-clickL")) {
            ClickL click = new ClickL(robot);
            return click;
        }
        else if(line.contains("-clickR")){
            ClickR click = new ClickR(robot);
            return click;
        }
        else if(line.contains("-clickM")){
            ClickM click = new ClickM(robot);
            return click;
        }
        return null;
    }

    private void correctSet(StringBuffer text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '\n') {
                if(text.charAt(i+1) == '\n'){
                    text.deleteCharAt(i + 1);
                    i--;
                }
            }
        }
    }

    public void createSetFromText(StringBuffer text) {
        correctSet(text);

        String[] lines = text.toString().split("\\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("if:")) {
                If newIf = new If(readValue(lines[i]));
                i++;
                while (lines[i].charAt(0) == '-') {
                    newIf.addMethod(createMethod(lines[i]));
                    i++;
                    if (i == lines.length) {
                        break;
                    }
                }
                i--;
                ifs.add(newIf);
            }
            else if (lines[i].contains("loop:")) {
                Loop loop = new Loop();
                loop.setButtonOnOff(loopGetButtonOnOff(lines[i]));
                loop.setInterval(loopGetInterval(lines[i]));
                i++;
                while (lines[i].charAt(0) == '-') {
                    loop.addMethod(createMethod(lines[i]));
                    i++;
                    if (i == lines.length) {
                        break;
                    }
                }
                i--;
                loops.add(loop);
            }
        }
    }

    private String readValue(String line) {
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ':') {
                while (i < line.length()) {
                    i++;
                    try {
                        value.append(line.charAt(i));
                    } catch (Exception e) {
                    }
                }
                return value.toString();
            }
        }
        return "";
    }

    private int getX(String line) {
        StringBuffer value = new StringBuffer("");
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'x') {
                i += 2;
                while (line.charAt(i) != ':') {
                    value.append(line.charAt(i));
                    i++;
                    if (i == line.length())
                        break;
                }
                return Integer.parseInt(value.toString());
            }
        }
        return 333;
    }

    private int loopGetInterval(String line) {
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 't') {
                i += 2;
                while (line.charAt(i) != ':') {
                    value.append(line.charAt(i));
                    i++;
                }
                return Integer.parseInt(value.toString());
            }
        }
        return 3000;
    }

    private String loopGetButtonOnOff(String line) {
        StringBuffer value = new StringBuffer("");
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'b') {
                i += 2;
                while (line.charAt(i) != ':') {
                    value.append(line.charAt(i));
                    i++;
                    if (i == line.length())
                        break;
                }
                return value.toString();
            }
        }
        return "";
    }

    private int getY(String line) {
        StringBuffer value = new StringBuffer("");
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'y') {
                i += 2;
                while (line.charAt(i) != ':') {
                    value.append(line.charAt(i));
                    i++;
                    if (i == line.length())
                        break;
                }
                return Integer.parseInt(value.toString());
            }
        }
        return 333;
    }
}
