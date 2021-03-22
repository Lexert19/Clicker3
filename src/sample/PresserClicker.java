package sample;

import sample.method.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
                try {
                    createSetFromText(new StringBuffer(Data.textSet));
                    Data.error.set(false);
                } catch (Exception e) {
                    Data.error.set(true);
                }
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
                Thread.sleep(1);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    public Method createMethod(String line) {
        List<String> args = getArgs(line);
        if (line.contains("-wait")) {
            Wait wait = new Wait();
            wait.setTime(Integer.parseInt(args.get(0)));
            return wait;
        } else if (line.contains("-press")) {
            Press press = new Press(robot);
            press.setButton(args.get(0));
            return press;

        } else if (line.contains("-move")) {
            Move move = new Move(robot);
            move.setX(Integer.parseInt(args.get(0)));
            move.setY(Integer.parseInt(args.get(1)));
            return move;

        } else if (line.contains("-click")) {

            Click click = new Click(robot, args.get(0).charAt(0));
            return click;
        }
        return null;
    }

    private void correctSet(StringBuffer text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '\n') {
                if (text.charAt(i + 1) == '\n') {
                    text.deleteCharAt(i + 1);
                    i--;
                }
            }
        }
    }

    public void createSetFromText(StringBuffer text) {
        correctSet(text);

        String[] lines = text.toString().split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("if")) {
                List<String> args = getArgs(lines[i]);

                If newIf = new If(args.get(0));
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
            } else if (lines[i].contains("loop")) {
                Loop loop = new Loop();
                List<String> args = getArgs(lines[i]);

                loop.setButtonOnOff(args.get(1));
                loop.setInterval(Integer.parseInt(args.get(0)));
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

    private List<String> getArgs(String line) {
        String cuttedCommand = cutCommand(line);
        List<String> args = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean spacesCleared = false;
        char ch;
        for (int i = 0; i < cuttedCommand.length(); i++) {
            ch = cuttedCommand.charAt(i);
            if (ch == '"') {
                i++;
                ch = cuttedCommand.charAt(i);
                while (ch != '"') {
                    sb.append(ch);
                    i++;
                    ch = cuttedCommand.charAt(i);
                }
                args.add(sb.toString());
                sb = new StringBuilder();
                i++;
                if ((i) == cuttedCommand.length()) {
                    return args;
                }
                ch = cuttedCommand.charAt(i);
            } else if (spacesCleared && ch == ' ') {
                args.add(sb.toString());
                sb = new StringBuilder();
                spacesCleared = false;
            } else if (ch == ' ') {
                continue;
            } else {
                spacesCleared = true;
                sb.append(ch);
            }
        }
        if (sb.length() > 0) {
            args.add(sb.toString());
        }
        return args;
    }

    private String cutCommand(String command) {
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == ' ') {
                command = command.substring(i + 1);
                break;
            }
        }
        return command;
    }
}
