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

public class SensorController implements Initializable {



    SensorDbQuery query;



    @FXML
    private Button SenAdd;
    @FXML
    private Button SenUpdate;
    @FXML
    private Button SenDelete;
    @FXML
    private TextField SenID;
    @FXML
    private TextField SenName;
    @FXML
    private TextField SenModel;
    @FXML
    private TextField SenSerialNo;
    @FXML
    private Button SenSelect;
    @FXML
    private TableView <SensorModel>sen_table;
    @FXML
    private TableColumn <SensorModel, String> sen_table_col_sen_id;
    @FXML
    private TableColumn <SensorModel, String> sen_table_col_sen_name;
    @FXML
    private TableColumn <SensorModel, String> sen_table_col_sen_model;
    @FXML
    private TableColumn <SensorModel, String> sen_table_col_sen_serial_no;
    @FXML
    private TableColumn <SensorModel, String> sen_table_col_eq_id;
    @FXML
    private ChoiceBox eq_choicebox_sen;

    public void MenuWindow(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        query= new SensorDbQuery();
        sen_table_col_sen_id.setCellValueFactory(new PropertyValueFactory<SensorModel, String>("sen_id"));
        sen_table_col_sen_name.setCellValueFactory(new PropertyValueFactory<SensorModel, String>("sen_name"));
        sen_table_col_sen_model.setCellValueFactory(new PropertyValueFactory<SensorModel, String>("sen_model"));
        sen_table_col_sen_serial_no.setCellValueFactory(new PropertyValueFactory<SensorModel, String>("sen_serial_no"));
        sen_table_col_eq_id.setCellValueFactory(new PropertyValueFactory<SensorModel, String>("eq_id"));


        try {
            sen_table.setItems(query.get_sen_list());
            eq_choicebox_sen.setItems(query.get_eq_id_list());

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


    }



    public void add_sen (ActionEvent event){
        SensorModel model = new SensorModel(SenID.getText(), SenName.getText(),SenModel.getText(), SenSerialNo.getText(),eq_choicebox_sen.getValue().toString());
        int count = query.add_sen(model);
        refreshTable();
    }

    public void select_sen (ActionEvent event){
        SensorModel model = sen_table.getSelectionModel().getSelectedItem();
        SenID.setText(model.getSen_id());
        SenName.setText(model.getSen_name());
        SenModel.setText(model.getSen_model());
        SenSerialNo.setText(model.getSen_serial_no());
        eq_choicebox_sen.setValue(model.getEq_id());
    }


    public void update_sen (ActionEvent event){
        SensorModel model = new SensorModel(SenID.getText(), SenName.getText(),SenModel.getText(), SenSerialNo.getText(), eq_choicebox_sen.getValue().toString());
        int count = query.update_sen(model);
        refreshTable();

    }

    public void delete_sen (ActionEvent event){
        SensorModel model = sen_table.getSelectionModel().getSelectedItem();

        int count = query.delete_sen(model);
        int count1 = query.delete_sen_sch(model);
        refreshTable();


    }

    private void refreshTable (){
        try {
            sen_table.setItems(query.get_sen_list());
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        SenID.clear();
        SenName.clear();
        SenModel.clear();
        SenSerialNo.clear();
    }

}
