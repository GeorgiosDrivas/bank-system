package com.example.banksystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Deposit {

    @FXML
    private TextField depositField;

    public void confirmDeposit(ActionEvent event) throws SQLException, IOException {
        String id = userData.getUserId();
        int currBalance = userData.getUserBalance();
        int depositAmount = Integer.parseInt(depositField.getText());

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connectDB = connectNow.getConnection();

        String balanceQuery = "UPDATE profile SET currentBalance = ? WHERE id = ?";
        preparedStatement = connectDB.prepareStatement(balanceQuery);
        preparedStatement.setInt(1, currBalance + depositAmount);
        preparedStatement.setString(2, id);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            userData.setUserBalance(currBalance + depositAmount);
            Parent newSceneRoot = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
            Scene newScene = new Scene(newSceneRoot, 420, 420);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Banking System");
            window.show();
        } else {
            // Handle the case where the update failed
            System.out.println("Update failed, no rows affected.");
        }
    }

}
