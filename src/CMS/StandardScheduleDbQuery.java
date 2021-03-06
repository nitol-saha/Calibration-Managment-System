package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StandardScheduleDbQuery {

    Connection connection;

    public StandardScheduleDbQuery() {
        connection = DBConnector.connect();
    }


    public ObservableList<String> get_sd_id_list() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select *  from standard_device_table")) {
            while (resultSet.next()) {
                list.add(resultSet.getString("ID"));
            }
            return list;
        }
    }



    public ObservableList<ScheduleModel> get_schedule_list() throws SQLException {
        ObservableList<ScheduleModel> list= FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select * from standard_month")) {
            while (resultSet.next()){
                ScheduleModel sd_sch_model = new ScheduleModel(resultSet.getString("ID"), resultSet.getString("January"),resultSet.getString("February"),resultSet.getString("March")
                , resultSet.getString("April"), resultSet.getString("May"), resultSet.getString("June"), resultSet.getString("July"), resultSet.getString("August"),
                        resultSet.getString("September"), resultSet.getString("October"), resultSet.getString("November"), resultSet.getString("December"));
                list.add(sd_sch_model);
            }
            return list;
        }

    }

    public int add_sd_schedule(ScheduleInsertModel model){
        System.out.println(model.getMonth());
        String query= "INSERT INTO `cms`.`standard_month` (`ID`,"+model.getMonth()+" ) VALUES (?,?)";
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

    public int update_sd_schedule(ScheduleInsertModel model){
        System.out.println(model.getMonth());
        String query= "UPDATE `cms`.`standard_month` SET "+model.getMonth()+"= ? WHERE `ID`=?";
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


    public int add_sd(StandardDeviceModel model){
        String query= "INSERT INTO `cms`.`login_table` (`ID`, `Standard Device Name`, `Standard Device Model`,`Serial No`) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getSd_id());
            statement.setString(2, model.getSd_name());
            statement.setString(3, model.getSd_model());
            statement.setString(4, model.getSd_serial_no());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }

    }
}
