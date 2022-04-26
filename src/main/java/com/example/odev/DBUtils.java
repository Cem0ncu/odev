package com.example.odev;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.channels.ConnectionPendingException;
public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String Title, String email, String password){
        Parent root = null;

        if (email != null && password != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LogIn logInController = loader.getController();
                logInController.setUserInformation(email, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root.FXMLLoader(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String email,String password, String favchannel){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymstudio", "root", "ferraridst1");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM mitglied AND trainer WHERE Name = ?");
            psCheckUserExists.setString(1, EMail);

            resultSet = psCheckUserExists.executeQuerry();

            if (resultSet.isBeforeFirst()) {
                System.out.println("USerAlreadyExists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("E-Mail already in use");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (Email, username, favechannel, VALUES(?,?,?)*");
                psInsert.setString(1, EMail);
                psInsert.setString(2, Höhe);
                psInsert.setString(3, Weiblich / Manner);
                psInsert.executeUpdate();
                changeScene(event, "LogIn.fxml","Welcome!",email,password);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if ( resultSet != null) {
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if ( psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if ( psInsert != null) {
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if ( connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void  logInUser(ActionEvent event, String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymstudio", "root", "ferraridst1");
            preparedStatement = connection.prepareStatement("SELECT password, email FROM mitglied AND trainer WHERE email = ?");
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User is not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Credentials are incorrect");
                alert.show();
            } else {
                while(resultSet.next()) {
                    String retrivedPassword = resultSet.getString(password);
                    String retrivedEmail = resultSet.getString(email);
                    if (retrivedPassword.equals(password)) {
                        changeScene(event,"login.xml","welcome",email,password);
                    } else {
                        System.out.println("Passwords ");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Passwords are incorrect");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ( resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if ( preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if ( connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
