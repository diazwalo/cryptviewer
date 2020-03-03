package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnterKeyView {
	private Stage secondaryStage;
	private Scene secondaryScene;
	private VBox fileView;
	private TextArea textArea;
	private TextField keyInput;
	private Button validate;
	
	
	public EnterKeyView() {

	}

	public Stage createFileView(Stage primaryStage) {
		this.secondaryStage = new Stage();

		this.secondaryStage.setScene(this.secondaryScene);
		this.secondaryStage.centerOnScreen();
		this.secondaryStage.initModality(Modality.WINDOW_MODAL);
		this.secondaryStage.initOwner(primaryStage);
		this.secondaryStage.show();

		return this.secondaryStage;
	}

	public Scene createSceneSecondaryScene(File selFile) {
		this.fileView = new VBox();
		
		keyInput= new TextField();
		validate = new Button("OK");
		validate.setDisable(true);
		
		
		return new Scene(this.fileView);
	}

	public Scene getSecondaryScene() {
		return this.secondaryScene;
	}
}
