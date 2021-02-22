package CMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public void SensorWindow(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Sensor.fxml"));
        primaryStage.setTitle("Sensor Management");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void EquipmentWindow(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Equipment.fxml"));
        primaryStage.setTitle("Equipment Management");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void StandardDeviceWindow(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StandardDevice.fxml"));
        primaryStage.setTitle("Standard Device Management");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void ScheduleWindow(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Schedule.fxml"));
        primaryStage.setTitle("Schedule View");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}
