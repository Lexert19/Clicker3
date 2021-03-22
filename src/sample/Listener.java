package sample;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements NativeKeyListener, Runnable {
    private BlockingQueue queue;
    public Listener(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new Listener(queue));
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        logger.setUseParentHandlers(false);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        try {
            if(e.getKeyText(e.getKeyCode()).toUpperCase().equals(Data.buttonOnOff.toUpperCase())){
                Data.active.set(!Data.active.get());
            }

            if(Data.active.get()){
                queue.put(e.getKeyText(e.getKeyCode()));
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

    }


}

