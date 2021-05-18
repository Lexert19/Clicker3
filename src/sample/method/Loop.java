package sample.method;

public class Loop {
    private Events events;
    private String buttonOnOff;

    public Loop() {
        events = new Events(Events.Type.Loop);

    }

    public void run() {
        events.execute();
    }

    public void activate(String button){
        if(buttonOnOff.toUpperCase().equals(button.toUpperCase())){
            events.toggle();
        }
    }

    public void addMethod(Method method){
        events.addEvent(method);
    }

    public void setInterval(int interval) {
        events.addEvent(new Wait(interval));
    }

    public void setButtonOnOff(String buttonOnOff) {
        this.buttonOnOff = buttonOnOff;
    }
}
