package CMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDbQuery {
    Connection connection;
    public SignUpDbQuery(){
        connection =DBConnector.connect();
    }

    public int user_add(SignUPModel model){


        String query= "INSERT INTO `cms`.`signup_table` (`Username`, `Password`, `Full Name`, `Gender`) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model.getF_name());
            statement.setString(2, model.getU_name());
            statement.setString(3, model.getPsw());
            statement.setString(4, model.getGend());
            int result= statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }
    }


    public ObservableList<String> get_un_auth_id_list() throws SQLException {
        ObservableList<String> list= FXCollections.observableArrayList();
        try (ResultSet resultSet = connection.createStatement().executeQuery("select *  from signup_table")) {
            while (resultSet.next()){
                System.out.println(resultSet);
                list.add(resultSet.getString("Username"));

            }
            return list;
        }


    }

    public int autho_user(String user_id){


        String query= "INSERT INTO `cms`.`login_table` (`Username`, `Password`) SELECT `Username`, `Password` FROM `cms`.`signup_table` WHERE `signup_table`.`Username`=?";


        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user_id);
            int result = statement.executeUpdate();

            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }



    }


    public int del_autho(String user_id){


        String query="DELETE FROM `cms`.`signup_table` WHERE `signup_table`.`Username` = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user_id);
            int result = statement.executeUpdate();
            return result;
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();

            return 0;
        }




    }




}
