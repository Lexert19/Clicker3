package sample.method;


public class If extends Method{
    private String button;
    private Events events;

    public If(String button) {
        this.button = button.toUpperCase();
        events = new Events(Events.Type.If);
    }

    public int run(){
        events.execute();
        return 0;
    }

    public void activate(String button){
        if(button.toUpperCase().equals(this.button.toUpperCase())){
            events.activate();
        }
    }

    public void addMethod(Method method){
        events.addEvent(method);
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button.toUpperCase();
    }
}
