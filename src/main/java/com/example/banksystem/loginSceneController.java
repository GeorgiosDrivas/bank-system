package com.example.banksystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginSceneController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;

    @FXML
    public void connectButton(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connectDB = connectNow.getConnection();
            String email = emailText.getText();
            String password = passwordText.getText();

            String connectQuery = "SELECT email, password, id FROM users WHERE email = ? AND password = ?";
            preparedStatement = connectDB.prepareStatement(connectQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String userId = resultSet.getString("id");
                userData.setUserId(userId);

                // Query to get currentBalance from profile table
                String balanceQuery = "SELECT currentBalance, userName FROM profile WHERE id = ?";
                preparedStatement = connectDB.prepareStatement(balanceQuery);
                preparedStatement.setString(1, userId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int userBalance = resultSet.getInt("currentBalance");
                    String userName = resultSet.getString("userName");
                    userData.setUserName(userName);
                    userData.setUserBalance(userBalance);
                    loadNewScene(event);
                } else {
                    welcomeText.setText("No profile found for the user.");
                }
            } else {
                welcomeText.setText("Wrong credentials. Try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
            welcomeText.setText("An error occurred. Please try again later.");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connectDB != null) connectDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadNewScene(ActionEvent event) {
        try {
            Parent newSceneRoot = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
            Scene newScene = new Scene(newSceneRoot, 420, 420);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Banking System");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            welcomeText.setText("Failed to load the new scene. Please try again later.");
        }
    }
}