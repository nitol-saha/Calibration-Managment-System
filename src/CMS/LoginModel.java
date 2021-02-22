package CMS;

import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginModel {
    Connection conn;

    public LoginModel ()
    {
        conn = DBConnector.connect();
    }

    public boolean isLogin(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        String query= "Select * from login_table where Username=? and Password=?";
        try {
            preparedStatement= conn.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2, password);
            resultset = preparedStatement.executeQuery();
            if (resultset.next()){
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        finally {
            preparedStatement.close();
            resultset.close();
        }



    }

}
