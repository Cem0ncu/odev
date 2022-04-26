package com.example.odev;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
    @FXML
    private Button LogInButton;
    @FXML
    private Button SignUpButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @Override
    public void initialize(){
        LogInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, emailField.getText(), passwordField.getText());
            }
        });
        SignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"SignUp.fxml","Sign Up", null,null);
            }
        });
    }
}