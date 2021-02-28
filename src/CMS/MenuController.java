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

    public void StandardScheduleWindow (ActionEvent event) throws  IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("StandardSchedule.fxml"));
        primaryStage.setTitle("Standard Device Schedule");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void SensorScheduleWindow (ActionEvent event) throws  IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("SensorSchedule.fxml"));
        primaryStage.setTitle("Sensor Schedule");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void AuthorizeUserWindow (ActionEvent event) throws  IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("UserAuth.fxml"));
        primaryStage.setTitle("User Authorization");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}
