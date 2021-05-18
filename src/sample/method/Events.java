package sample.method;

import java.util.ArrayList;
import java.util.List;

public class Events {
    enum Type{
        Loop,
        If;
    }
    private Type type;
    private boolean active;
    private int position;
    private List<Method> methods;

    public Events(Type type) {
        this.position = 0;
        this.active = false;
        methods = new ArrayList<>();
        this.type = type;
    }

    public void execute(){
        if(methods.size() == position){
            if (Type.Loop.equals(type)) {
                position = 0;
            }else if(Type.If.equals(type)){
                position = 0;
                active = false;
            }

            return;
        }
        if(active){
            position += methods.get(position).run();
        }
    }

    public void activate(){
        if(!active){
            position = 0;
            active = true;
        }

    }

    public void toggle(){
        active = !active;
        position = 0;
    }

    public void addEvent(Method method){
        methods.add(method);
    }
}
