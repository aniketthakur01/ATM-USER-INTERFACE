package application;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChangePinInterface extends atmModules {
	
	Twilio.init("AC18bed894d0a19100d9aceb34c71f7f8f", "54da13bd6e6e6eadce1b2e8a03aaa2e9");

    public static final String ACCOUNT_SID = "AC18bed894d0a19100d9aceb34c71f7f8f";
    public static final String AUTH_TOKEN = "54da13bd6e6e6eadce1b2e8a03aaa2e9";
//    public static final String TWILIO_PHONE_NUMBER = "+12512902978";
    

    private static int generateOTP() {
        // Generate a random 6-digit OTP for demonstration
        return (int) (Math.random() * 900000) + 100000;
    }
    
    
    private static void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    private static void updatePin(String newPin) {};
    
    private static int generatedOTP;


    static void changePin() {
        Stage changePinStage = new Stage();
        changePinStage.initModality(Modality.APPLICATION_MODAL);
        changePinStage.initStyle(StageStyle.UTILITY);
        changePinStage.setTitle("Change PIN");

        
        Label MobileLabel = new Label("Enter Registered Mobile Number");
        MobileLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial';");
        TextField mobileTextField = new TextField();
        mobileTextField.setMaxSize(100, 30);
        
        Button sendOTPButton = new Button("Send OTP");
        sendOTPButton.setMaxSize(100, 30);
        
        sendOTPButton.setOnAction(e -> {
            generatedOTP = generateOTP();
            String recipientPhoneNumber = mobileTextField.getText();
            
 
//            Twilio.init("AC18bed894d0a19100d9aceb34c71f7f8f", "54da13bd6e6e6eadce1b2e8a03aaa2e9");
            
            try {
            Message message = Message.creator(
                    new PhoneNumber("+919109021038"),
                    new PhoneNumber("+12512902978"),
                    "Your OTP is: " + generatedOTP).create();

            System.out.println("OTP sent successfully!");
            }
            catch(Exception ex) {
            	System.out.println(ex.getMessage());
            }
        });

        Label otpText = new Label("Enter OTP");
        otpText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial';");
        TextField otpTextField = new TextField();
        otpTextField.setMaxSize(100, 30);

        Label Newpin = new Label("Enter New PIN");
        Newpin.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial';");
        TextField newPinText = new TextField();
        newPinText.setMaxSize(100, 30);
        
        
        Button confirmButton = new Button("PRESS HERE");
        confirmButton.setMaxSize(100, 30);
        
        confirmButton.setOnAction(em -> {
            String enteredOTP = otpTextField.getText();
            String newPin = newPinText.getText();

            if (enteredOTP.isEmpty() || newPin.isEmpty()) {
                // Handling empty fields with a warning alert
                showAlert(Alert.AlertType.WARNING, "Warning", "Incomplete Fields", "Please enter OTP and new PIN.");
            } 
            
            else if (enteredOTP.equals(String.valueOf(generatedOTP))) {
                // OTP matches, update the PIN
                updatePin(newPin);
                showAlert(Alert.AlertType.INFORMATION, "Success", "PIN Updated", "PIN updated successfully!");
            } 
            
            else {
                // Incorrect OTP with a warning alert
                showAlert(Alert.AlertType.WARNING, "Warning", "Incorrect OTP", "Incorrect OTP. PIN update failed.");
            }
        });
       

        VBox dialogLayout = new VBox(10);
        dialogLayout.getChildren().addAll(MobileLabel, mobileTextField, sendOTPButton, otpText, otpTextField, Newpin, newPinText, confirmButton);

        Scene changePinScene = new Scene(dialogLayout, 500, 300);
        changePinStage.setScene(changePinScene);
        changePinStage.showAndWait();
    }
}

