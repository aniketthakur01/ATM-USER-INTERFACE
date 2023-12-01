package application;

import java.util.function.UnaryOperator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TransferInterface extends atmModules{
	
	static void transferAmt() {
		
		Stage transferStage = new Stage(); 
		
		transferStage.initModality(Modality.APPLICATION_MODAL);
        transferStage.initStyle(StageStyle.UTILITY);
        transferStage.setTitle("Transfer Money");
        
      
        // Create UI components for the withdrawal dialog
        BorderPane root = new BorderPane();
        
        VBox dialogLayout = new VBox(10);
        Label avaiBal = new Label("AVAILABLE BALANCE : " + totalBalance);
        avaiBal.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-text-fill: Blue;");
        dialogLayout.setAlignment(Pos.CENTER);
        dialogLayout.getChildren().add(avaiBal);
        
        Label enterAmount = new Label("Enter Amount:");
        enterAmount.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        TextField amountTextField = new TextField();
        amountTextField.setMaxSize(100, 30);
        
        Label enterAccNo = new Label("Enter Account Number:");
        enterAccNo.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        TextField accText = new TextField();
        accText.setMaxSize(150, 30);
        
        Label chooseAcc = new Label("Choose Bank: ");
        chooseAcc.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        ComboBox<String> AcccomboBox = new ComboBox<>();
        AcccomboBox.getItems().addAll("AXIS BANK", "PUNJAB NATIONAL BANK", "STATE BANK OF INDIA", "CANARA BANK", "YES BANK", "HDFC BANK", "BANK OF INDIA", "UNION BANK OF INDIA");
        
        Label chooseAccType = new Label("Choose Account Type:");
        chooseAccType.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        ComboBox<String> TypecomboBox = new ComboBox<>();
        TypecomboBox.getItems().addAll("Saving Account", "Current Account");
        
        
        Label enterPin = new Label("Enter PIN:");
        enterPin.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
        TextField pinTextField = new TextField();
        pinTextField.setMaxSize(100, 30);
        
         
//       it allows user to enter only NUMERIC VALUES
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        
        TextFormatter<String> accTextFormatter = new TextFormatter<>(filter);
        accText.setTextFormatter(accTextFormatter);
        
        Button confirmButton = new Button("PRESS HERE");
        confirmButton.setMaxSize(100,  30);
        
        dialogLayout.getChildren().addAll(enterAccNo, accText, chooseAcc, AcccomboBox, chooseAccType, TypecomboBox, enterAmount, amountTextField, enterPin, pinTextField, confirmButton);



        confirmButton.setOnAction(confirmEvent -> {
            // Retrieve the entered amount and PIN
             double transferedamount = Double.parseDouble(amountTextField.getText());
             String enteredpin = pinTextField.getText();
             
             if(enteredpin.equals(PIN)) {
             if(totalBalance >= transferedamount) {
             	  totalBalance -= transferedamount;
             	  System.out.println("Amount Transferred Succesfully\n" + "Available Balance : " + totalBalance);
             	  transferStage.close();
             }
             	  else {
             		  System.out.println("Insuficient Balance");
             		  
             	  }
             }
             
               else {
             	  System.out.println("Invalid PIN, Please Enter Correct PIN");
               }
             
             // Close the withdrawal dialog
             transferStage.close();
        });
        
        
        Scene transferScene = new Scene(dialogLayout, 500, 400);
       transferStage.setScene(transferScene);

        // Show the withdrawal dialog
//        withdrawStage.setLeft(avaiBal);
       transferStage.showAndWait();	

        
	}

}
