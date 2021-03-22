package sample.method;

import java.util.ArrayList;
import java.util.List;

public class Loop {
    private int interval;
    private String buttonOnOff;
    private boolean runnnig = false;
    private List<Method> methods = new ArrayList<>();
    private int tick = 0;

    public Loop() {
    }

    public void run() {
        tick ++;
        if(runnnig && tick >= interval){
            tick = 0;
            for(Method method : methods){
                method.run();
            }
        }
    }

    public void activate(String button){
        if(buttonOnOff.toUpperCase().equals(button.toUpperCase()))
            runnnig = !runnnig;
    }

    public void addMethod(Method method){
        methods.add(method);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getButtonOnOff() {
        return buttonOnOff;
    }

    public void setButtonOnOff(String buttonOnOff) {
        this.buttonOnOff = buttonOnOff.toUpperCase();
    }
}
