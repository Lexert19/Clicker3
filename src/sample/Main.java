package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        BlockingQueue<String> queue = new ArrayBlockingQueue(64);
        Data.loadSettings();

        Thread listener = new Thread(new Listener(queue));
        listener.run();
        Thread presserClicker = new Thread(new PresserClicker(queue));
        presserClicker.start();


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getStylesheets().add(getClass().getResource("main.css").toString());

        primaryStage.setTitle("Clicker3 XP");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 536, 470));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
