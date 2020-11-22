package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        BlockingQueue<String> queue = new ArrayBlockingQueue(64);
        Data.loadSettings();

        Thread listener = new Thread(new Listener(queue));
        listener.start();
        Thread presserClicker = new Thread(new PresserClicker(queue));
        presserClicker.start();

        Parent root = FXMLLoader.load(getClass().getResource("resources/sample.fxml"));
        root.getStylesheets().add(getClass().getResource("resources/main.css").toString());

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Clicker3");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600, 510));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            listener.interrupt();
            presserClicker.interrupt();
            Platform.exit();
            System.exit(0);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
