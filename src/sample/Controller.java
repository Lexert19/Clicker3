package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label zestaw1;
    @FXML
    private Label zestaw2;
    @FXML
    private Label zestaw3;
    @FXML
    private Label onOff;
    @FXML
    private Label saveLoadInfo;
    @FXML
    private TextField onOffInput;
    @FXML
    private TextArea scripts;
    private double x, y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            checkError();
            loadSettings();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadSettings() throws IOException {
        onOff.setText("On/Off: " + Data.buttonOnOff);
        scripts.setText(Data.textSet);
        zestaw1.getStyleClass().removeAll("actualZestaw");
        zestaw1.getStyleClass().add("zestawButton");
        zestaw2.getStyleClass().removeAll("actualZestaw");
        zestaw2.getStyleClass().add("zestawButton");
        zestaw3.getStyleClass().removeAll("actualZestaw");
        zestaw3.getStyleClass().add("zestawButton");
        if (Data.activeSet == 1) {
            zestaw1.getStyleClass().add("actualZestaw");
            zestaw1.getStyleClass().removeAll("zestawButton");
        }
        if (Data.activeSet == 2) {
            zestaw2.getStyleClass().add("actualZestaw");
            zestaw2.getStyleClass().removeAll("zestawButton");
        }
        if (Data.activeSet == 3) {
            zestaw3.getStyleClass().add("actualZestaw");
            zestaw3.getStyleClass().removeAll("zestawButton");
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

    public void loadSave(MouseEvent mouseEvent) throws IOException, InterruptedException {
        StringBuffer bf = new StringBuffer(scripts.getText());
        int j = 0;
        for (int i = 0; i < bf.length(); i++) {
            if (!Character.isSpaceChar(bf.charAt(i))) {
                bf.setCharAt(j++, bf.charAt(i));
            }
        }
        bf.delete(j, bf.length());
        Data.textSet = bf.toString();
        Data.updateSet.set(true);
        Data.save();

        checkError();
    }

    public void pressed(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }

    public void dragged(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY() - y);
    }

    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        //Platform.exit();
        //System.exit(0);
    }

    public void minimize(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void hideSaveLoadInfo(KeyEvent keyEvent) {
        saveLoadInfo.setText("");
    }

    public void checkError() throws InterruptedException {
        Thread.sleep(120);
        if (Data.error.get() == false) {
            saveLoadInfo.setText("Saved");
            saveLoadInfo.getStyleClass().removeAll("errorInfo");
            saveLoadInfo.getStyleClass().add("savedInfo");
        } else {
            saveLoadInfo.setText("Error");
            saveLoadInfo.getStyleClass().removeAll("savedInfo");
            saveLoadInfo.getStyleClass().add("errorInfo");
        }
    }
}
