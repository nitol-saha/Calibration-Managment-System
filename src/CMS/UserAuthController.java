package CMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class UserAuthController implements Initializable {

    SignUpDbQuery query;
    @FXML
    private ChoiceBox un_auth_list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        query = new SignUpDbQuery();

        try {
            un_auth_list.setItems(query.get_un_auth_id_list());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void MenuWindow(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public void auth_user(ActionEvent event) {

        int count = query.autho_user(un_auth_list.getValue().toString());
        int count1 = query.del_autho(un_auth_list.getValue().toString());

        refreshTable();


    }

    private void refreshTable() {
        try {
            un_auth_list.setItems(query.get_un_auth_id_list());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


    }
}
