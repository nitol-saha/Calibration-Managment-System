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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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



        query= new StandardScheduleDbQuery();
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months_temp = dfs.getMonths();
        String[] months= Arrays.copyOf(months_temp, months_temp.length-1);
        List<String> monthList = Arrays.asList(months);
        ObservableList<String> MonthList = FXCollections.observableList(monthList);
        ObservableList<String> WeekList = FXCollections.observableArrayList("1st Week","2nd Week","3d Week","4th Week");
        ObservableList<String> FrequencyList = FXCollections.observableArrayList("1","3","6","12");
        try {
            sd_id_choicebox.setItems(query.get_sd_id_list());;
            month_choicebox.setItems(MonthList);
            week_choicebox.setItems(WeekList);
            frequency_checkbox.setItems(FrequencyList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void add_sd_schedule (ActionEvent event){
        int frequency = Integer.parseInt(String.valueOf(frequency_checkbox.getValue()));
        int loop_count= 12/frequency;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months_temp = dfs.getMonths();
        String[] months= Arrays.copyOf(months_temp, months_temp.length-1);
        List<String> monthList = Arrays.asList(months);
        int month_position = monthList.indexOf(month_choicebox.getValue());
        query= new StandardScheduleDbQuery();
        for (int i = 0; i < loop_count; i++)
            {


                StandardScheduleModel model = new StandardScheduleModel(sd_id_choicebox.getValue().toString(), monthList.get(month_position), week_choicebox.getValue().toString());
                int count = query.add_sd_schedule(model);
                System.out.printf("\nID : "+sd_id_choicebox.getValue().toString() );
                System.out.println("\nMonth :" + monthList.get(month_position));
                System.out.println("Week :" +week_choicebox.getValue().toString());
                month_position=month_position+frequency;
                if(month_position>11)
                {
                    month_position=month_position-12;

                }

            }


    }

}
