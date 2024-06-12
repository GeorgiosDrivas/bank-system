package com.example.banksystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class requestLoan {
    ResultSet resultSet = null;

    public void requestLoanAction(ActionEvent event) throws SQLException, IOException {
        String id = userData.getUserId();

        DatabaseConnection connectNow = new DatabaseConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatement balanceStatement = null;

        Connection connectDB = connectNow.getConnection();

            String requestQuery = "SELECT loan, currentBalance FROM profile WHERE id = ?";
            preparedStatement = connectDB.prepareStatement(requestQuery);
            preparedStatement.setString(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int loan = resultSet.getInt("loan");
                int balance = resultSet.getInt("currentBalance");

                if(loan == 0){
                    String balanceQuery = "UPDATE profile SET currentBalance = ?, loan = ? WHERE id = ?";
                    balanceStatement = connectDB.prepareStatement(balanceQuery);
                    balanceStatement.setInt(1, balance + 150);
                    balanceStatement.setInt(2, 1);
                    balanceStatement.setString(3, id);

                    int rowsAffected = balanceStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        userData.setUserBalance(balance + 150);
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
    }
}
