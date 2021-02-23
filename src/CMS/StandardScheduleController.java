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

public class StandardScheduleController implements Initializable {

    StandardScheduleDbQuery query;

    @FXML
    private ChoiceBox sd_id_choicebox;
    @FXML
    private ChoiceBox month_choicebox;
    @FXML
    private ChoiceBox week_choicebox;

    @FXML
    private ChoiceBox frequency_checkbox;
    @FXML
    private TableColumn col_sd_id;
    @FXML
    private TableColumn col_jan;
    @FXML
    private TableColumn col_feb;
    @FXML
    private TableColumn col_mar;
    @FXML
    private TableColumn col_apr;
    @FXML
    private TableColumn col_may;
    @FXML
    private TableColumn col_jun;
    @FXML
    private TableColumn col_jul;
    @FXML
    private TableColumn col_aug;
    @FXML
    private TableColumn col_sep;
    @FXML
    private TableColumn col_oct;
    @FXML
    private TableColumn col_nov;
    @FXML
    private TableColumn col_dec;
    @FXML
    private TableView sche_table;


    public void MenuWindow(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        query = new StandardScheduleDbQuery();

        col_sd_id.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("sd_id"));
        col_jan.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("jan"));
        col_feb.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("feb"));
        col_mar.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("mar"));
        col_apr.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("apr"));
        col_may.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("may"));
        col_jun.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("jun"));
        col_jul.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("jul"));
        col_aug.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("aug"));
        col_sep.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("sep"));
        col_oct.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("oct"));
        col_nov.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("nov"));
        col_dec.setCellValueFactory(new PropertyValueFactory<StandardScheduleModel, String>("dec"));


        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months_temp = dfs.getMonths();
        String[] months = Arrays.copyOf(months_temp, months_temp.length - 1);
        List<String> monthList = Arrays.asList(months);
        ObservableList<String> MonthList = FXCollections.observableList(monthList);
        ObservableList<String> WeekList = FXCollections.observableArrayList("1st", "2nd", "3rd", "4th");
        ObservableList<String> FrequencyList = FXCollections.observableArrayList("1", "3", "6", "12");
        try {
            sche_table.setItems(query.get_schedule_list());
            sd_id_choicebox.setItems(query.get_sd_id_list());
            ;
            month_choicebox.setItems(MonthList);
            week_choicebox.setItems(WeekList);
            frequency_checkbox.setItems(FrequencyList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void add_sd_schedule(ActionEvent event) {
        int frequency = Integer.parseInt(String.valueOf(frequency_checkbox.getValue()));
        int loop_count = 12 / frequency;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months_temp = dfs.getMonths();
        String[] months = Arrays.copyOf(months_temp, months_temp.length - 1);
        List<String> monthList = Arrays.asList(months);
        int month_position = monthList.indexOf(month_choicebox.getValue());
        query = new StandardScheduleDbQuery();

        for (int i = 0; i < loop_count; i++) {


            StandardScheduleInsertModel model = new StandardScheduleInsertModel(sd_id_choicebox.getValue().toString(), monthList.get(month_position), week_choicebox.getValue().toString());
            if(i==0) {
                int count = query.add_sd_schedule(model);
            }
            else {

                int count = query.update_sd_schedule(model);

            }

            System.out.printf("\nID : " + sd_id_choicebox.getValue().toString());
            System.out.println("\nMonth :" + monthList.get(month_position));
            System.out.println("Week :" + week_choicebox.getValue().toString());
            month_position = month_position + frequency;
            if (month_position > 11) {
                month_position = month_position - 12;

            }

        }
        refreshTable();


    }

    private void refreshTable() {
        try {
            sche_table.setItems(query.get_schedule_list());




        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


    }
}
