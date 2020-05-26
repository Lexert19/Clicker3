package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSettings();
    }

    private void loadSettings(){
        onOff.setText("On/Off: "+Data.buttonOnOff);
        scripts.setText(Data.textSet);
        zestaw1.getStyleClass().remove("actualZestaw");
        zestaw2.getStyleClass().remove("actualZestaw");
        zestaw3.getStyleClass().remove("actualZestaw");
        if(Data.activeSet == 1)
            zestaw1.getStyleClass().add("actualZestaw");
        if(Data.activeSet == 2)
            zestaw2.getStyleClass().add("actualZestaw");
        if(Data.activeSet == 3)
            zestaw3.getStyleClass().add("actualZestaw");

    }

    public void changeSettings1(MouseEvent mouseEvent) throws IOException {
        Data.changeSet(1);
        loadSettings();
    }

    public void changeSettings2(MouseEvent mouseEvent) throws IOException {
        Data.changeSet(2);
        loadSettings();
    }

    public void changeSettings3(MouseEvent mouseEvent) throws IOException {
        Data.changeSet(3);
        loadSettings();
    }


    public void updateOnOff(MouseEvent mouseEvent) throws IOException {
        if(onOffInput.getText().length() != 0){
            Data.changeButtonOnOff(onOffInput.getText());
            onOffInput.setText("");
            loadSettings();
        }
    }

    public void loadSave(MouseEvent mouseEvent) throws IOException {
        //save
        Data.textSet = scripts.getText();
        Data.updateSet = true;
        Data.save();
    }
}
