package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static String Mobile;
	public static String AccNum;
	public static int totalBalance;
	public static String PIN;
	public Connection con;
	
	// Method to display an alert
	private void showAlert(AlertType alertType, String title, String message) {
	    Alert alert = new Alert(alertType);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	
		@Override
		public void start(Stage primaryStage) {
			
			primaryStage.setTitle("USER REGISTRATION");
		
		    BorderPane root = new BorderPane();
		    VBox dialogLayout = new VBox(10);
		    dialogLayout.setAlignment(Pos.CENTER);
			
			Label content = new Label("ENTER USER DETAILS");
			content.setStyle("-fx-font-weight: bold;  -fx-font-size: 16pt; -fx-text-fill: RED");
			BorderPane.setAlignment(content, javafx.geometry.Pos.CENTER);
			root.setTop(content);
			
            Separator separatorTop = new Separator();
            separatorTop.setPrefWidth(200);
			
            Label MobileLabel = new Label("Enter Registered Mobile Number");
            MobileLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial';");
            TextField mobileText = new TextField();
            mobileText.setMaxSize(100, 30);
           
            
            Label enterAccNo = new Label("Enter Account Number:");
            enterAccNo.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
            TextField accText = new TextField();
            accText.setMaxSize(150, 30);
            
            Label enterAmount = new Label("Enter Amount:");
            enterAmount.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
            TextField amountText = new TextField();
            amountText.setMaxSize(150, 30);
            
            Label enterPin = new Label("Enter PIN:");
            enterPin.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-size: 10pt; -fx-font-style: italic ");
            PasswordField  pinText = new PasswordField();
            pinText.setMaxSize(100, 30);
            
            
//             it will allows user to input only NUMERIC VALUES 
            UnaryOperator<TextFormatter.Change> filter = change -> {
                String text = change.getText();
                if (text.matches("[0-9]*")) {
                    return change;
                }
           
                return null;
            };
            TextFormatter<String> mobileTextFormatter = new TextFormatter<>(filter);
            TextFormatter<String> accTextFormatter = new TextFormatter<>(filter);
            TextFormatter<String> amountTextFormatter = new TextFormatter<>(filter);
            TextFormatter<String> pinTextFormatter = new TextFormatter<>(filter);
            mobileText.setTextFormatter(mobileTextFormatter);
            accText.setTextFormatter(accTextFormatter);
            amountText.setTextFormatter(amountTextFormatter);
            pinText.setTextFormatter(pinTextFormatter);
            
            
            Button confirmButton = new Button("PRESS HERE");
            confirmButton.setMaxSize(100,  30);
            
            
//            to execute the next module display --  atmModule method
            confirmButton.setOnAction(new EventHandler() {
            	@Override
            	public void handle(Event arg0) {
            	atmModules.Modules();
            	}
            });
            
            
//          Reminder to show text fields are empty
            confirmButton.setOnAction(e -> {
            	
                if (mobileText.getText().isEmpty() || accText.getText().isEmpty() ||
                    amountText.getText().isEmpty() || pinText.getText().isEmpty()) {
                    // If any of the fields is empty, show an alert
                    showAlert(AlertType.WARNING, "Empty Fields", "Please fill in all the fields.");
                    
                    
                } else {
                	try{
                	String  mobileNo = mobileText.getText();
                    String accNumber = accText.getText();
                    int amount = Integer.parseInt(amountText.getText());
                    String pinCode = pinText.getText();

                    insertUserDetails(mobileNo, accNumber, amount, pinCode);
                    Mobile = mobileNo;
                    AccNum = accNumber;
                    totalBalance = amount;
                    PIN = pinCode;	
                    atmModules.Modules();
                }
                
                catch (NumberFormatException ex) {
                    showAlert(AlertType.ERROR, "Input Error", "Please enter valid numbers.");
                    // Handle the exception appropriately, e.g., show an error message
                    ex.printStackTrace();
                }
                }
            });

            dialogLayout.getChildren().addAll(content, separatorTop, MobileLabel, mobileText, enterAccNo, accText, enterAmount, amountText, enterPin, pinText, confirmButton);
            
            root.setCenter(dialogLayout);
           
            Scene mainScene = new Scene(root,500, 400);
            primaryStage.setScene(mainScene);
            primaryStage.show();
	
		}
		//Method to insert user details into the database
		public void insertUserDetails(String mobileNo, String AccNumber, int amount, String pinCode) {
		    try {
		        // Ensure that the database connection is established
		        createConnection();
 
		        // Prepare the SQL statement for insertion
		        String dbInsert = "INSERT INTO userregister (mobileNo, AccNumber, Amount, pinCode) VALUES ('" + mobileNo + "', '" + AccNumber + "', '" + amount + "', '" + pinCode +"')";

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
		

		
		
//		*************       MAIN FUNCTION      *****************
    public static void main(String[] args) {
		   launch(args); // Launch the JavaFX application
  }	
    
    /* *****************                   DATABASE CONNECTION                 ***************************/
    
    public void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_interface", "root", "aniket@31186");
                System.out.println("Database connection successful");
                
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error: Failed to establish database connection");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: JDBC driver not found");
        }
    }

}
