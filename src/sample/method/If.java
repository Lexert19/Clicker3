package sample.method;

import java.util.ArrayList;
import java.util.List;

public class If extends Method{
    private String button;
    private List<Method> methods = new ArrayList<>();
    private int tickToAllow  = 0;
    private int tick  = 0;
    private int index = 0;
    private boolean running = false;

    public If(String button) {
        this.button = button.toUpperCase();
    }

    public void run(){
        if(running)
            tick++;
        if(running && tick >= tickToAllow){
            if(index == methods.size()){
                running = false;
                index =0;
                tick =0;
                tickToAllow =0;
                return;
            }
            if(methods.get(index) instanceof Wait)
                tickToAllow+=((Wait) methods.get(index)).getTime();
            else
                methods.get(index).run();
            index++;
        }
    }

    public void activate(String button){
        if(button.toUpperCase().equals(this.button.toUpperCase()))
            running = true;
    }

    public void addMethod(Method method){
        methods.add(method);
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button.toUpperCase();
    }
}
