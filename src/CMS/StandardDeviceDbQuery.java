package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StandardDeviceDbQuery {

    Connection connection;
    public StandardDeviceDbQuery(){
        connection =DBConnector.connect();
    }

    public ObservableList<StandardDeviceModel> get_sd_list() throws SQLException {
        ObservableList<StandardDeviceModel> list= FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select * from standard_device_table")) {
            while (resultSet.next()){
                StandardDeviceModel sd_model = new StandardDeviceModel(resultSet.getString("ID"), resultSet.getString("Standard Device Name"),resultSet.getString("Standard Device Model"),resultSet.getString("Serial No"));
                list.add(sd_model);
            }
            return list;
        }

    }



    public int add_sd(StandardDeviceModel model){
        String query= "INSERT INTO `cms`.`standard_device_table` (`ID`, `Standard Device Name`, `Standard Device Model`,`Serial No`) VALUES (?,?,?,?)";
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




