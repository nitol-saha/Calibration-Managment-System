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

    public int add_sd_schedule(StandardScheduleModel model){
        System.out.println(model.getMonth());
        String query= "INSERT INTO `cms`.`standard_schedule_table` (`ID`, ?) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, model.getMonth());
            statement.setString(2, model.getSd_id());
            statement.setString(3, model.getWeek());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }

    }
}
