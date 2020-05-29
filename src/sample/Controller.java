package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button zestaw1;
    @FXML
    private Button zestaw2;
    @FXML
    private Button zestaw3;
    @FXML
    private Label onOff;
    @FXML
    private Button onOffButton;
    @FXML
    private TextField onOffInput;
    @FXML
    private TextArea scripts;
    private double x,y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSettings() throws IOException {
        onOff.setText("On/Off: " + Data.buttonOnOff);
        scripts.setText(Data.textSet);
        zestaw1.getStyleClass().remove("actualZestaw");
        zestaw1.getStyleClass().add("zestawButton");
        zestaw2.getStyleClass().remove("actualZestaw");
        zestaw2.getStyleClass().add("zestawButton");
        zestaw3.getStyleClass().remove("actualZestaw");
        zestaw3.getStyleClass().add("zestawButton");
        if (Data.activeSet == 1){
            zestaw1.getStyleClass().add("actualZestaw");
            zestaw1.getStyleClass().remove("zestawButton");
        }
        if (Data.activeSet == 2){
            zestaw2.getStyleClass().add("actualZestaw");
            zestaw2.getStyleClass().remove("zestawButton");
        }
        if (Data.activeSet == 3){
            zestaw3.getStyleClass().add("actualZestaw");
            zestaw3.getStyleClass().remove("zestawButton");
        }
        Data.loadSettings();
    }

    public void changeSettings1(MouseEvent mouseEvent) throws IOException {
        saveSet();
        Data.changeSet(1);
        loadSettings();
    }

    public void changeSettings2(MouseEvent mouseEvent) throws IOException {
        saveSet();
        Data.changeSet(2);
        loadSettings();
    }

    public void changeSettings3(MouseEvent mouseEvent) throws IOException {
        saveSet();
        Data.changeSet(3);
        loadSettings();
    }


    public void updateOnOff(MouseEvent mouseEvent) throws IOException {
        if (onOffInput.getText().length() != 0) {
            Data.changeButtonOnOff(onOffInput.getText());
            onOffInput.setText("");
            loadSettings();
        }
    }

    private void saveSet() throws IOException {
        StringBuffer bf = new StringBuffer(scripts.getText());
        int j = 0;
        for (int i = 0; i < bf.length(); i++) {
            if (!Character.isSpaceChar(bf.charAt(i))) {
                bf.setCharAt(j++, bf.charAt(i));
            }
        }
        Data.textSet = bf.toString();
        Data.save();
    }

    public void loadSave(MouseEvent mouseEvent) throws IOException {
        StringBuffer bf = new StringBuffer(scripts.getText());
        int j = 0;
        for (int i = 0; i < bf.length(); i++) {
            if (!Character.isSpaceChar(bf.charAt(i))) {
                bf.setCharAt(j++, bf.charAt(i));
            }
        }
        bf.delete(j, bf.length());
        Data.textSet = bf.toString();
        Data.updateSet = true;
        Data.save();
    }

    public void pressed(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }

    public void dragged(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX()-x);
        stage.setY(mouseEvent.getScreenY()-y);
    }

    public void close(MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }
}
