package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnterKeyView {
	private Stage secondaryStage;
	private Scene secondaryScene;
	private VBox fileView;
	private TextField keyInput;
	private Button validate;
	private Label message;
	
	private int lengthKey;
	
	public EnterKeyView() {

	}

	public Stage createFileView(Stage primaryStage, int lengthKey) {
		this.secondaryStage = new Stage();
		
		this.secondaryStage.setScene(this.createSceneSecondaryScene(lengthKey));
		this.secondaryStage.centerOnScreen();
		this.secondaryStage.initModality(Modality.WINDOW_MODAL);
		this.secondaryStage.initOwner(primaryStage); 	
		this.secondaryStage.show();

		return this.secondaryStage;
	}

	public Scene createSceneSecondaryScene(int lengthKey) {
		this.lengthKey = lengthKey;
		
		this.fileView = new VBox();
		this.message = new Label("Entrez une clef de longueur " + this.lengthKey + " : ");
		
		keyInput= new TextField();
		validate = new Button("OK");
		
		this.fileView.getChildren().addAll(this.message, this.keyInput, this.validate);
		
		this.secondaryScene = new Scene(this.fileView);
		return this.secondaryScene;
	}

	public Scene getSecondaryScene() {
		return this.secondaryScene;
	}
	
	public Button getValidateButton() {
		return this.validate;
	}
	
	public String getKeyInput() {
		return this.keyInput.getText();
	}
	
	public boolean isLengthKeyInputEnougth() {
		if(this.keyInput.getText().length() == this.lengthKey) {
			return true;
		}else {
			System.out.println("GET :" + this.keyInput.getText().length() +" EXPECTED : " + this.lengthKey);
			
			this.keyInput.clear();
			return false;
		}
	}
}
