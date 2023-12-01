package application;

import java.sql.SQLException;
import java.sql.Statement;
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

public class WithdrawInterface extends atmModules{
	
	static void WithdrawAmt() {
		
		
		Stage withdrawStage = new Stage(); 
		
		  withdrawStage.initModality(Modality.APPLICATION_MODAL);
          withdrawStage.initStyle(StageStyle.UTILITY);
          withdrawStage.setTitle("Withdraw Money");
          
          
        
          // Create UI components for the withdrawal dialog
          BorderPane root = new BorderPane();
          
          VBox dialogLayout = new VBox(10);
          Label avaiBal = new Label("AVAILABLE BALANCE : " + totalBalance);
          avaiBal.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-text-fill: Blue;");
          dialogLayout.setAlignment(Pos.CENTER);
          dialogLayout.getChildren().add(avaiBal);          
          
          Label enterAmount = new Label("Withdraw Amount:");
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
              int withdrawamount = Integer.parseInt(amountTextField.getText());
              String enteredpin = pinTextField.getText();
              
              if(enteredpin.equals(PIN)) {
            	  totalBalance -= withdrawamount;
            	  System.out.println("Amount Deducted Succesfully\n" + "Available Balance : " + totalBalance);
            	  withdrawStage.close();
              }
              
              else {
            	  System.out.println("Invalid PIN, Please Enter Correct PIN");
              }
              

              // Close the withdrawal dialog
              withdrawStage.close();
          });
          
          // Create a layout for the withdrawal dialog
          dialogLayout.getChildren().addAll(enterAmount, amountTextField, enterPin, pinTextField, confirmButton);
          
          
          // Create a scene and set it for the withdrawal dialog
          Scene withdrawScene = new Scene(dialogLayout, 500, 300);
          withdrawStage.setScene(withdrawScene);

          // Show the withdrawal dialog
          withdrawStage.showAndWait();	
	}
	
//	insertWithdrawal(idWithdrawAmount,pinCode);
	
//	***************    INSERT TO DATABASE          **************
	
	public void insertWithdrawal(int idWithdrawAmount, String pinCode) {
	    try {
	        // Ensure that the database connection is established
	        createConnection();

	        // Prepare the SQL statement for insertion
	        String dbInsert = "INSERT INTO withdrawmoney (idWithdrawAmount, pinCode) VALUES ('" + idWithdrawAmount + "', '" + pinCode +"')";

	        // Create and execute the statement
	        try (Statement stat = con.createStatement()) {
	            stat.executeUpdate(dbInsert);
	        }
	        System.out.println("User details inserted into the database.");
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        // Handle the exception appropriately, e.g., show an error message
	        System.out.println("Error inserting user details into the database.");
	    }
	}


}


	


