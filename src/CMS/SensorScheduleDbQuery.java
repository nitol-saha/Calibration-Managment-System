package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorScheduleDbQuery {

    Connection connection;

    public SensorScheduleDbQuery() {
        connection = DBConnector.connect();
    }


    public ObservableList<String> get_sen_id_list() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select *  from sensor_table")) {
            while (resultSet.next()) {
                list.add(resultSet.getString("ID"));
            }
            return list;
        }
    }



    public ObservableList<ScheduleModel> get_schedule_list() throws SQLException {
        ObservableList<ScheduleModel> list= FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select * from sensor_month")) {
            while (resultSet.next()){
                ScheduleModel sen_sch_model = new ScheduleModel(resultSet.getString("ID"), resultSet.getString("January"),resultSet.getString("February"),resultSet.getString("March")
                        , resultSet.getString("April"), resultSet.getString("May"), resultSet.getString("June"), resultSet.getString("July"), resultSet.getString("August"),
                        resultSet.getString("September"), resultSet.getString("October"), resultSet.getString("November"), resultSet.getString("December"));
                list.add(sen_sch_model);
            }
            return list;
        }

    }

    public int add_sen_schedule(ScheduleInsertModel model){
        System.out.println(model.getMonth());
        String query= "INSERT INTO `cms`.`sensor_month` (`ID`,"+model.getMonth()+" ) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getSd_id());
            statement.setString(2, model.getWeek());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }

    }

    public int update_sen_schedule(ScheduleInsertModel model){
        System.out.println(model.getMonth());
        String query= "UPDATE `cms`.`sensor_month` SET "+model.getMonth()+"= ? WHERE `ID`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, model.getWeek());
            statement.setString(2, model.getSd_id());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }

    }


}
