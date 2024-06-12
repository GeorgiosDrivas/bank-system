package com.example.banksystem;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Withdraw {

    @FXML
    private TextField withdrawField;

    @FXML
    private Label limitLabel;

    @FXML
    private Button withdrawButton;

    public void confirmWithdraw(ActionEvent event) throws SQLException, IOException {
        String id = userData.getUserId();
        int currBalance = userData.getUserBalance();
        int withdrawAmount = Integer.parseInt(withdrawField.getText());

        DatabaseConnection connectNow = new DatabaseConnection();
        PreparedStatement preparedStatement = null;

        Connection connectDB = connectNow.getConnection();

        if(currBalance > withdrawAmount){

            String balanceQuery = "UPDATE profile SET currentBalance = ? WHERE id = ?";
            preparedStatement = connectDB.prepareStatement(balanceQuery);
            preparedStatement.setInt(1, currBalance - withdrawAmount);
            preparedStatement.setString(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                userData.setUserBalance(currBalance - withdrawAmount);
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

        } else {
            limitLabel.setText("Your balance is lower than the amount");
            withdrawButton.setDisable(true);
            withdrawField.setText("");

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    e -> withdrawButton.setDisable(false)
            ));
            timeline.play();
        }
    }
}