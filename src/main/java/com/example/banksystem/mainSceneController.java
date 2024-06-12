package com.example.banksystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
        Parent newSceneRoot = FXMLLoader.load(getClass().getResource("deposit.fxml"));
        Scene newScene = new Scene(newSceneRoot, 420, 420);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("Banking System - Deposit");
        window.show();
    }

    public void withdraw (ActionEvent event) throws IOException {
        Parent newSceneRoot = FXMLLoader.load(getClass().getResource("withdraw.fxml"));
        Scene newScene = new Scene(newSceneRoot, 420, 420);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("Banking System - Withdraw");
        window.show();
    }

    public void requestLoan (ActionEvent event) throws IOException {
        Parent newSceneRoot = FXMLLoader.load(getClass().getResource("requestLoan.fxml"));
        Scene newScene = new Scene(newSceneRoot, 420, 420);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("Banking System - Request Loan");
        window.show();
    }

    public void payLoan (ActionEvent event) throws IOException {
        Parent newSceneRoot = FXMLLoader.load(getClass().getResource("payLoan.fxml"));
        Scene newScene = new Scene(newSceneRoot, 420, 420);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("Banking System - Pay Loan");
        window.show();
    }
}
