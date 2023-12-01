package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BalanceEnquiryInterface extends atmModules{
	
	static void balanceEnquiry() {
		
Stage balanceEnquiryStage = new Stage(); 
		
	    balanceEnquiryStage.initModality(Modality.APPLICATION_MODAL);
        balanceEnquiryStage.initStyle(StageStyle.UTILITY);
        balanceEnquiryStage.setTitle("Balance Enquiry");
        
        BorderPane root = new BorderPane();
        
        VBox dialogLayout = new VBox(10);
        
        Label chooseAccType = new Label("Choose Account Type:");
        chooseAccType.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        dialogLayout.setAlignment(Pos.CENTER);
        ComboBox<String> TypecomboBox = new ComboBox<>();
        TypecomboBox.getItems().addAll("Saving Account", "Current Account");
        
        
        Label enterPin = new Label("Enter PIN:");
        enterPin.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        TextField pinTextField = new TextField();
        pinTextField.setMaxSize(100, 30);
        
        Button confirmButton = new Button("PRESS HERE");
        confirmButton.setMaxSize(100, 30);
        
        // Add functionality to the confirm button
        confirmButton.setOnAction(confirmEvent -> {
            // Retrieve the entered amount and PIN
            String enteredpin = pinTextField.getText();
            
            if(enteredpin.equals(PIN)) {
                    // Creating a simple information alert
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("BALANCE ENQURY");
                    alert.setHeaderText("AVAILABLE BALANCE : "+ totalBalance);
                    alert.showAndWait();
            }
            
            else
            {
            	System.out.println("Invalid PIN, Please Enter Correct PIN");
            }

            // Close the withdrawal dialog
            balanceEnquiryStage.close();
        });
        
        // Create a layout for the withdrawal dialog
        dialogLayout.getChildren().addAll(chooseAccType, TypecomboBox, enterPin, pinTextField, confirmButton);


        
        
        // Create a scene and set it for the withdrawal dialog
        Scene balanceEnquiryScene = new Scene(dialogLayout, 500, 300);
        balanceEnquiryStage.setScene(balanceEnquiryScene);

        // Show the withdrawal dialog
        balanceEnquiryStage.showAndWait();
	}

}
