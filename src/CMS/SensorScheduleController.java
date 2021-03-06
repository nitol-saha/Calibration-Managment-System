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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SensorScheduleController implements Initializable {
    SensorScheduleDbQuery query;
    @FXML
    private ChoiceBox sen_id_choicebox;
    @FXML
    private ChoiceBox month_choicebox;
    @FXML
    private ChoiceBox week_choicebox;
    @FXML
    private ChoiceBox frequency_checkbox;
    @FXML
    private TableView sche_table;
    @FXML
    private TableColumn col_sen_id;
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


        query = new SensorScheduleDbQuery();

        col_sen_id.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("sd_id"));
        col_jan.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("jan"));
        col_feb.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("feb"));
        col_mar.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("mar"));
        col_apr.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("apr"));
        col_may.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("may"));
        col_jun.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("jun"));
        col_jul.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("jul"));
        col_aug.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("aug"));
        col_sep.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("sep"));
        col_oct.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("oct"));
        col_nov.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("nov"));
        col_dec.setCellValueFactory(new PropertyValueFactory<ScheduleModel, String>("dec"));


        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months_temp = dfs.getMonths();
        String[] months = Arrays.copyOf(months_temp, months_temp.length - 1);
        List<String> monthList = Arrays.asList(months);
        ObservableList<String> MonthList = FXCollections.observableList(monthList);
        ObservableList<String> WeekList = FXCollections.observableArrayList("1st", "2nd", "3rd", "4th");
        ObservableList<String> FrequencyList = FXCollections.observableArrayList("1", "3", "6", "12");
        try {
            sche_table.setItems(query.get_schedule_list());
            sen_id_choicebox.setItems(query.get_sen_id_list());
            ;
            month_choicebox.setItems(MonthList);
            week_choicebox.setItems(WeekList);
            frequency_checkbox.setItems(FrequencyList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void add_sen_schedule(ActionEvent event) {
        int frequency = Integer.parseInt(String.valueOf(frequency_checkbox.getValue()));
        int loop_count = 12 / frequency;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months_temp = dfs.getMonths();
        String[] months = Arrays.copyOf(months_temp, months_temp.length - 1);
        List<String> monthList = Arrays.asList(months);
        int month_position = monthList.indexOf(month_choicebox.getValue());
        query = new SensorScheduleDbQuery();

        for (int i = 0; i < loop_count; i++) {


            ScheduleInsertModel model = new ScheduleInsertModel(sen_id_choicebox.getValue().toString(), monthList.get(month_position), week_choicebox.getValue().toString());
            if(i==0) {
                int count = query.add_sen_schedule(model);
            }
            else {

                int count = query.update_sen_schedule(model);

            }

            System.out.printf("\nID : " + sen_id_choicebox.getValue().toString());
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
