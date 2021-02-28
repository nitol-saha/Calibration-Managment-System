package CMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class SignUpController implements Initializable {


    SignUpDbQuery query;
    private String gender;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField f_name;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        query= new SignUpDbQuery();

        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        genderGroup.selectedToggleProperty().addListener(
                (observable, oldToggle, newToggle) -> {
                    if (newToggle == male) {
                        gender = "male";
                    } else if (newToggle == female) {
                        gender = "female";
                    } else {
                        gender = "none";
                    }
                }
        );



    }

    public void LoginWindow(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void add_user (ActionEvent event){
        SignUPModel model = new SignUPModel(user.getText(), pass.getText(),f_name.getText(),  gender);
        int count = query.user_add(model);

    }





}
