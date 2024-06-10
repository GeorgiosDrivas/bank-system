package com.example.banksystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

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

    public void deposit (ActionEvent event) throws IOException {
        Deposit deposit = new Deposit();
        try {
            deposit.deposit(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
