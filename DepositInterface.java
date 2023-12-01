package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DepositInterface extends atmModules{
	
	static void depositAmt() {
		Stage depositStage = new Stage(); 
		
	    depositStage.initModality(Modality.APPLICATION_MODAL);
        depositStage.initStyle(StageStyle.UTILITY);
        depositStage.setTitle("Deposit Money");
        
      
        // Create UI components for the withdrawal dialog
        BorderPane root = new BorderPane();
        
        VBox dialogLayout = new VBox(10);
        Label avaiBal = new Label("AVAILABLE BALANCE : " + totalBalance);
        avaiBal.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-text-fill: Blue;");
        dialogLayout.setAlignment(Pos.CENTER);
        dialogLayout.getChildren().add(avaiBal);          
        
        Label enterAmount = new Label("Deposit Amount:");
        enterAmount.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        TextField amountTextField = new TextField();
        amountTextField.setMaxSize(100, 30);
        
        Label enterPin = new Label("Enter PIN:");
        enterPin.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        TextField pinTextField = new TextField();
        pinTextField.setMaxSize(100, 30);
        
        Button confirmButton = new Button("PRESS HERE");
        confirmButton.setMaxSize(100,  30);
        
        // Add functionality to the confirm button
        confirmButton.setOnAction(confirmEvent -> {
            // Retrieve the entered amount and PIN
              double depositamount = Double.parseDouble(amountTextField.getText());
              String enteredpin = pinTextField.getText();
            
            if(enteredpin.equals(PIN)) {
          	  totalBalance += depositamount;
          	  System.out.println("Amount Deposited Succesfully\n" + "Available Balance : " + totalBalance);
          	  depositStage.close();
            }
            
            else {
          	  System.out.println("Invalid PIN, Please Enter Correct PIN");
            }

            // Close the withdrawal dialog
            depositStage.close();
        });
        
        // Create a layout for the withdrawal dialog
        dialogLayout.getChildren().addAll(enterAmount, amountTextField, enterPin, pinTextField, confirmButton);
        
        
        // Create a scene and set it for the withdrawal dialog
        Scene depositScene = new Scene(dialogLayout, 500, 300);
        depositStage.setScene(depositScene);

        // Show the withdrawal dialog
        depositStage.showAndWait();	

		
	}

}
