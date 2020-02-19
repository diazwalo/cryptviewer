package view;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FileView {
	private Stage secondaryStage;
	private Scene secondaryScene;
	private VBox fileView;
	
	public FileView() {
		
	}
	
	public Stage createFileView(Stage primaryStage) {
		this.secondaryStage = new Stage();
		
		this.secondaryScene = createSceneSecondaryScene();
		
		this.secondaryStage.setScene(this.secondaryScene);
		this.secondaryStage.centerOnScreen();
		this.secondaryStage.initModality(Modality.WINDOW_MODAL);
		this.secondaryStage.initOwner(primaryStage);
		this.secondaryStage.show();
		
		return this.secondaryStage;
	}

	private Scene createSceneSecondaryScene() {
		this.fileView = new VBox();
		this.fileView.getChildren().add(new TextArea("NOT IMPLEMENTED YES"));
		return new Scene(this.fileView);
	}
	
	public Scene getSecondaryScene() {
		return this.secondaryScene;
	}
}
