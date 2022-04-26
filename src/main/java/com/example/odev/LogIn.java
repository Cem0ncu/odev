package com.example.odev;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LogInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "login",null,null);
            }
        });
    }
}
