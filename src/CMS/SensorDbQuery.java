package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorDbQuery {

    Connection connection;

    public SensorDbQuery() {
        connection = DBConnector.connect();
    }

    public ObservableList<SensorModel> get_sen_list() throws SQLException {
        ObservableList<SensorModel> list = FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select * from sensor_table")) {
            while (resultSet.next()) {
                SensorModel sen_model = new SensorModel(resultSet.getString("ID"), resultSet.getString("Sensor Name"), resultSet.getString("Sensor Model"), resultSet.getString("Serial No"), resultSet.getString("Equipment ID"));
                list.add(sen_model);
            }
            return list;
        }

    }


    public int add_sen(SensorModel model) {
        String query = "INSERT INTO `cms`.`sensor_table` (`ID`, `Sensor Name`, `Sensor Model`,`Serial No`, `Equipment ID`) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getSen_id());
            statement.setString(2, model.getSen_name());
            statement.setString(3, model.getSen_model());
            statement.setString(4, model.getSen_serial_no());
            statement.setString(5, model.getEq_id());
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException throwable) {
            throwable.printStackTrace();

            return 0;
        }

    }

    public int update_sen(SensorModel model) {
        String query = "UPDATE `cms`.`sensor_table` SET `ID` =?, `Sensor Name`=?, `Sensor Model`=?, `Serial No`=?, `Equipment ID`=? WHERE `sensor_table`.`ID`=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getSen_id());
            statement.setString(2, model.getSen_name());
            statement.setString(3, model.getSen_model());
            statement.setString(4, model.getSen_serial_no());
            statement.setString(5, model.getEq_id());
            statement.setString(6, model.getSen_id());
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException throwable) {
            throwable.printStackTrace();

            return 0;
        }


    }

    public int delete_sen(SensorModel model) {
        String query = "DELETE FROM `cms`.`sensor_table` WHERE `sensor_table`.`ID` = ?";
        String query1 = "DELETE FROM `cms`.`sensor_month` WHERE `sensor_month`.`ID` = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getSen_id());
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException throwable) {
            throwable.printStackTrace();

            return 0;
        }

    }
    public ObservableList<String> get_eq_id_list() throws SQLException {
        ObservableList<String> list= FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select *  from equipment_table")) {
            while (resultSet.next()){
                list.add(resultSet.getString("Equipment ID"));
            }
            return list;
        }

    }

    public int delete_sen_sch(SensorModel model) {
        String query = "DELETE FROM `cms`.`sensor_month` WHERE `sensor_month`.`ID` = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getSen_id());
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException throwable) {
            throwable.printStackTrace();

            return 0;
        }

    }
}