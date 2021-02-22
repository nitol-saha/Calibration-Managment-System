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

public class EquipmentController  implements Initializable {

    EquipmentDbQuery query;

    @FXML
    private TextField EqID;
    @FXML
    private TextField EqName;
    @FXML
    private TextField FaName;
    @FXML
    private TableColumn <EquipmentModel, String>eq_table_col_eq_id;
    @FXML
    private TableColumn <EquipmentModel, String> eq_table_col_eq_name;
    @FXML
    private TableColumn <EquipmentModel, String> eq_table_col_facility_name;
    @FXML
    private TableView <EquipmentModel> eq_table;
    @FXML
    private Button EqAdd;
    @FXML
    private Button EqSelect;

    @FXML
    private TextField ID;
    @FXML
    private Button EqDelete;
    @FXML
    private ChoiceBox eq_choicebox;

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
        query= new EquipmentDbQuery();
        eq_table_col_eq_id.setCellValueFactory(new PropertyValueFactory<EquipmentModel, String>("eq_id"));
        eq_table_col_eq_name.setCellValueFactory(new PropertyValueFactory<EquipmentModel, String>("eq_name"));
        eq_table_col_facility_name.setCellValueFactory(new PropertyValueFactory<EquipmentModel, String>("facility_name"));
        try {
            eq_table.setItems(query.get_eq_list());
            eq_choicebox.setItems(query.get_eq_id_list());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void add_equipment (ActionEvent event){
        EquipmentModel model = new EquipmentModel(0,EqID.getText(), EqName.getText(),FaName.getText());
        int count = query.add_eq(model);

        refreshTable();



    }

    public void select_equipment (ActionEvent event){
        EquipmentModel model = eq_table.getSelectionModel().getSelectedItem();
        ID.setText(Integer.toString(model.getId()));
        EqID.setText(model.getEq_id());
        EqName.setText(model.getEq_name());
        FaName.setText(model.getFacility_name());

    }

    public void update_equipment (ActionEvent event){
        EquipmentModel model = new EquipmentModel(Integer.parseInt(ID.getText()),EqID.getText(), EqName.getText(),FaName.getText());
        int count = query.update_eq(model);
        refreshTable();


    }

    public void delete_equipment (ActionEvent event){
        EquipmentModel model = eq_table.getSelectionModel().getSelectedItem();

        int count = query.delete_eq(model);
        refreshTable();


    }



    private void refreshTable (){
        try {
            eq_table.setItems(query.get_eq_list());



            eq_choicebox.setItems(query.get_eq_id_list());


        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        EqID.clear();
        EqName.clear();
        FaName.clear();
    }
}
