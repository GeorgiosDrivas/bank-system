package com.example.banksystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class mainSceneController {
    @FXML
    private Label userBalanceLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    public void initialize(){
        String username = userData.getUserName();
        userNameLabel.setText("Welcome " + username + "!");
        int userBalance = userData.getUserBalance();
        userBalanceLabel.textProperty().bind(new SimpleIntegerProperty(userBalance).asString());
    }
}
