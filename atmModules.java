package application;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class atmModules extends Main{
	
	
	static void Modules() {

			Stage modulesStage = new Stage();
		    BorderPane root = new BorderPane();
		    modulesStage.setTitle("ATM INTERFACE");
			
			Label content = new Label("WELCOME TO SWIZZ BANK");
			content.setStyle("-fx-font-weight: bold;  -fx-font-size: 16pt; -fx-text-fill: PURPLE");
			BorderPane.setAlignment(content, javafx.geometry.Pos.CENTER);
			root.setTop(content);
			
            Separator separatorTop = new Separator();
            separatorTop.setPrefWidth(200);
			
			Label styleText = new Label("AVAILABLE SERVICES");
			styleText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-font-family: 'Arial';  -fx-font-style: italic; -fx-text-fill: red;");
			root.setLeft(styleText);
			
			 VBox FixAlign = new VBox(styleText);
	         FixAlign.setAlignment(Pos.CENTER);
			
            Separator separatorBottom = new Separator();
            separatorBottom.setPrefWidth(200);
			 
			
			
			VBox buttonBox = new VBox();
			Button withdraw = new Button("WITHDRAW MONEY");
			withdraw.setPrefSize(200, 30);
			Button deposit = new Button("DEPOSIT MONEY");
			deposit.setPrefSize(200, 30);
			Button transfer = new Button("TRANSFER MONEY");
			transfer.setPrefSize(200, 30);
			Button balEnquiry = new Button("BALANCE ENQUIRY");
			balEnquiry.setPrefSize(200, 30);
			Button txnHistory = new Button("TRANSACTION HISTORY");
			txnHistory.setPrefSize(200, 30);
			Button pin = new Button("PIN CODE CHANGE");
			pin.setPrefSize(200, 30);
			
			buttonBox.getChildren().addAll(withdraw, deposit, transfer, balEnquiry, txnHistory, pin);
			buttonBox.setAlignment(Pos.CENTER);
			buttonBox.setSpacing(10);
			root.setBottom(buttonBox);
			
			root.setCenter(FixAlign);
			
            root.getChildren().addAll(separatorTop, separatorBottom);
            
            withdraw.setOnAction(new EventHandler() {
            	@Override
            	public void handle(Event arg0) {
            		WithdrawInterface.WithdrawAmt();
            	}
            });
            
            deposit.setOnAction(new EventHandler(){
            	public void handle(Event arg0) {
            		DepositInterface.depositAmt();
            	}
            });
            
            transfer.setOnAction(new EventHandler() {
            	public void handle(Event arg0) {
            		TransferInterface.transferAmt();
            	}
            });
            
            balEnquiry.setOnAction(new EventHandler() {
            	public void handle(Event arg0) {
            		BalanceEnquiryInterface.balanceEnquiry();
            	}
            });
            
            txnHistory.setOnAction(new EventHandler() {
            	public void handle(Event arg0) {
            		TransactionHistoryInterface.txnHistory();
            	}
            });
            
            pin.setOnAction(new EventHandler() {
            	public void handle(Event arg0) {
            		ChangePinInterface.changePin();
            	}
            });
			
			Scene scene = new Scene(root,600,350);
			modulesStage.setScene(scene);
			modulesStage.show();
		} 
	
		
	}
	

