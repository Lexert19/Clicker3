package sample.method;

public class Wait extends Method {
    private int time;
    private int currentTime;

    public Wait(int time) {
        this.time = time;
        currentTime = 0;
    }

    public int run() {
        if(currentTime == time){
            currentTime = 0;
            return 1;
        }else {
            currentTime++;
            return 0;
        }
    }
}
