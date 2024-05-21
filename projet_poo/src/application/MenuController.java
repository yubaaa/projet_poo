package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button servicePatientButton;

    @FXML
    private Button ServiceMedecinButton;

    @FXML
    private Button GestionUsersButton;

    @FXML
    private Button RendezvousButton;

    private double x = 0;
    private double y = 0;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Hide the current window
        ((Button) event.getSource()).getScene().getWindow().hide();
        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("qwe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButton(ActionEvent event) throws IOException {
        // Hide the current window
        ((Button) event.getSource()).getScene().getWindow().hide();
        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("qwe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    
    @FXML
    private void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
