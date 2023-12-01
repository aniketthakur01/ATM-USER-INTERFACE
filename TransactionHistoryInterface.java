package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TransactionHistoryInterface extends atmModules{
	
	static void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.setAlwaysOnTop(true); 
		
		alert.showAndWait();
	}
	
	 static List<String> fetchTransactions(LocalDate startDate, LocalDate endDate) {
	        List<String> transactions = new ArrayList<>();
	        transactions.add("Transaction 1");
	        transactions.add("Transaction 2");
	        
	        return transactions;
	    }
	 

	
	static void txnHistory() {
		
Stage txnHistoryStage = new Stage(); 
		
	    txnHistoryStage.initModality(Modality.APPLICATION_MODAL);
        txnHistoryStage.initStyle(StageStyle.UTILITY);
        txnHistoryStage.setTitle("Transaction History");
        

        BorderPane root = new BorderPane();
        
        VBox dialogLayout = new VBox(10);
        
        Label txnHistoryLabel = new Label("SELECT DATE TO SEE TRANSACTION HISTORY DETAILS");
        txnHistoryLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-text-fill: Blue; -fx-font-style: italic ");
        root.setTop(txnHistoryLabel);
        dialogLayout.setAlignment(Pos.CENTER);
        
        
        Label startDateLabel = new Label("Start Date");
        startDateLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt ");
        DatePicker startDate = new DatePicker();
        
        Label endDateLabel = new Label("End Date");
        endDateLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt;");
        DatePicker endDate = new DatePicker();
        
        Label chooseAccType = new Label("Choose Account Type:");
        chooseAccType.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        ComboBox<String> TypecomboBox = new ComboBox<>();
        TypecomboBox.getItems().addAll("Saving Account", "Current Account");
        
        Label enterPin = new Label("Enter PIN:");
        enterPin.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        TextField pinTextField = new TextField();
        pinTextField.setMaxSize(100, 30);
        
        Button confirmButton = new Button("PRESS HERE");
        confirmButton.setMaxSize(100,  30);
        
        confirmButton.setOnAction(confirmEvent -> {
            String enteredpin = pinTextField.getText();
            LocalDate selectedStartDate = startDate.getValue();
            LocalDate selectedEndDate = endDate.getValue();

            if (enteredpin != PIN) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Incorrect PIN", "Please enter the correct PIN.");
                return;
            }
            
            if (selectedStartDate != null && selectedEndDate != null) {
                if (selectedStartDate.isAfter(selectedEndDate)) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Date Range Error", "End date should be after start date.");
                    return; // Stop further execution
                }

                // Simulated transaction history retrieval between dates
                List<String> transactionHistory = fetchTransactions(selectedStartDate, selectedEndDate);

                // Simulate displaying transaction history (here, just printing to console)
                System.out.println("Transaction History between " + selectedStartDate + " and " + selectedEndDate + ":");
                for (String transaction : transactionHistory) {
                    System.out.println(transaction);
                }

                // Close the transaction history dialog
                txnHistoryStage.close();
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Incomplete Fields", "Please select both start and end dates.");
            }
        });

        
        dialogLayout.getChildren().addAll( txnHistoryLabel, startDateLabel, startDate, endDateLabel, endDate, chooseAccType, TypecomboBox, enterPin, pinTextField, confirmButton);
        
        Scene txnHistoryScene = new Scene(dialogLayout, 500, 400);
        txnHistoryStage.setScene(txnHistoryScene);

        // Show the withdrawal dialog
        txnHistoryStage.showAndWait();	
        
        
	}

}
