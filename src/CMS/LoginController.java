package CMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private Label msg;
    LoginModel model;


    @Override
    public void initialize (URL location, ResourceBundle resources){
        model= new LoginModel();
    }


    public void login(ActionEvent event)  {
        try {
            if(model.isLogin(user.getText(),pass.getText())){
                msg.setText("Login Successful");
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage= new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                primaryStage.setTitle("View Schedule Page");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
            else
                msg.setText("Login Failed");
        } catch (SQLException throwables) {
            msg.setText("Something went wrong");
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void SignupWindow (ActionEvent event) throws  IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        primaryStage.setTitle("Signup Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


}
