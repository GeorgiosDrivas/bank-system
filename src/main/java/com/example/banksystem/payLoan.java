package com.example.banksystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class payLoan {

    public void payLoanFunctionality(ActionEvent event) throws IOException, SQLException {

        String id = userData.getUserId();
        int balance = userData.getUserBalance();

        DatabaseConnection connectNow = new DatabaseConnection();

        String balanceQuery = "SELECT loan, currentBalance FROM profile WHERE id = ?";
        String updateQuery = "UPDATE profile SET loan = ?, currentBalance = ? WHERE id = ?";

        try (Connection connectDB = connectNow.getConnection();
             PreparedStatement preparedStatement = connectDB.prepareStatement(balanceQuery);
             PreparedStatement updateStatement = connectDB.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int loan = resultSet.getInt("loan");
                    int currentBalance = resultSet.getInt("currentBalance");

                    if (loan > 0 && balance > 150) {
                        int newLoanBalance = loan - 150;
                        int newBalance = currentBalance - 150;

                        updateStatement.setInt(1, newLoanBalance);
                        updateStatement.setInt(2, newBalance);
                        updateStatement.setString(3, id);

                        int results = updateStatement.executeUpdate();

                        if (results > 0) {
                            userData.setUserBalance(newBalance);
                            Parent newSceneRoot = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
                            Scene newScene = new Scene(newSceneRoot, 420, 420);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(newScene);
                            window.setTitle("Banking System");
                            window.show();
                        } else {
                            System.out.println("Update failed, no rows affected.");
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void backFunctionality(ActionEvent event) throws IOException {
        Parent newSceneRoot = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        Scene newScene = new Scene(newSceneRoot, 420, 420);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.setTitle("Banking System");
        window.show();
    }
}