package view;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		this.fileView = new VBox();
		
		keyInput= new TextField();
		validate = new Button("OK");
		
		validate.setDisable(true);
		
		this.keyInput.setOnAction(e -> {
			System.out.println(this.keyInput.getText().length());
			if(this.keyInput.getText().length() == this.lengthKey) {
				System.out.println(this.keyInput.getText().length());
				this.validate.setDisable(false);
			}else {
				this.validate.setDisable(true);
			}
		});
	
		
		this.fileView.getChildren().addAll(this.keyInput, this.validate);
		
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
		System.out.println(this.keyInput.getSelectedText());
		return this.keyInput.getSelectedText();
	}
}
