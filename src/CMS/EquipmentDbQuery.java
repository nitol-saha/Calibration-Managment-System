package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentDbQuery {
    Connection connection;
    public EquipmentDbQuery(){
        connection =DBConnector.connect();
    }

    public ObservableList<EquipmentModel> get_eq_list() throws SQLException {
      ObservableList<EquipmentModel> list= FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select * from equipment_table")) {
            while (resultSet.next()){
                EquipmentModel eq_model = new EquipmentModel(resultSet.getInt("ID"), resultSet.getString("Equipment ID"),resultSet.getString("Equipment Name"),resultSet.getString("Facility Name"));
                list.add(eq_model);
            }
            return list;
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
    public int add_eq(EquipmentModel model){
        String query= "INSERT INTO `cms`.`equipment_table` (`Equipment ID`, `Equipment Name`, `Facility Name`) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getEq_id());
            statement.setString(2, model.getEq_name());
            statement.setString(3, model.getFacility_name());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }

    }
    public int update_eq(EquipmentModel model){
        String query= "UPDATE `cms`.`equipment_table` SET `Equipment ID` =?, `Equipment Name`=?, `Facility Name`=? WHERE `equipment_table`.`ID`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getEq_id());
            statement.setString(2, model.getEq_name());
            statement.setString(3, model.getFacility_name());
            statement.setInt(4, model.getId());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }


    }
    public int delete_eq(EquipmentModel model){
        String query= "DELETE FROM `cms`.`equipment_table` WHERE `equipment_table`.`ID` = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, model.getId());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }

    }
}
