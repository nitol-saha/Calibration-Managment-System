package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SdDbQuery {

    Connection connection;
    public SdDbQuery(){
        connection =DBConnector.connect();
    }

    public ObservableList<SdModel> get_sd_list() throws SQLException {
        ObservableList<SdModel> list= FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select * from standard_device_table")) {
            while (resultSet.next()){
                SdModel sd_model = new SdModel(resultSet.getString("ID"), resultSet.getString("Standard Device Name"),resultSet.getString("Standard Device Model"),resultSet.getString("Serial No"));
                list.add(sd_model);
            }
            return list;
        }

    }

    public int add_sd(SdModel model){
        String query= "INSERT INTO `cms`.`standard_device_table` (`ID`, `Standard Device Name`, `Standard Device Model`, `Serial No`) VALUES (?,?,?,?)";
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
