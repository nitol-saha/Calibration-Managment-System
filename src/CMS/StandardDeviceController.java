package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StandardDeviceController implements Initializable {

    StandardDeviceDbQuery query;


    @FXML
    private Button SdAdd;
    @FXML
    private Button SdUpdate;
    @FXML
    private Button SdDelete;
    @FXML
    private TextField SdID;
    @FXML
    private TextField SdName;
    @FXML
    private TextField SdModel;
    @FXML
    private TextField SdSerialNo;
    @FXML
    private TableColumn sd_table_col_sd_id;
    @FXML
    private TableColumn sd_table_col_sd_name;
    @FXML
    private TableColumn sd_table_col_sd_model;
    @FXML
    private TableColumn sd_table_col_sd_serial_no;
    @FXML
    private TableView sd_table;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        query= new StandardDeviceDbQuery();
        sd_table_col_sd_id.setCellValueFactory(new PropertyValueFactory<StandardDeviceModel, String>("sd_id"));
        sd_table_col_sd_name.setCellValueFactory(new PropertyValueFactory<StandardDeviceModel, String>("sd_name"));
        sd_table_col_sd_model.setCellValueFactory(new PropertyValueFactory<StandardDeviceModel, String>("sd_model"));
        sd_table_col_sd_serial_no.setCellValueFactory(new PropertyValueFactory<StandardDeviceModel, String>("sd_serial_no"));



        try {
            sd_table.setItems(query.get_sd_list());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void MenuWindow(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void add_sd (ActionEvent event){
        StandardDeviceModel model = new StandardDeviceModel(SdID.getText(), SdName.getText(),SdModel.getText(), SdSerialNo.getText());
        int count = query.add_sd(model);

        refreshTable();

    }
    public void add_sd_schedule (ActionEvent event){
        StandardDeviceModel model = new StandardDeviceModel(SdID.getText(), SdName.getText(),SdModel.getText(), SdSerialNo.getText());
        int count = query.add_sd(model);

        refreshTable();

    }

    private void refreshTable (){
        try {
            sd_table.setItems(query.get_sd_list());
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        SdID.clear();
        SdName.clear();
        SdModel.clear();
        SdSerialNo.clear();
    }



}
