package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FileView {
	private Stage secondaryStage;
	private Scene secondaryScene;
	private VBox fileView;
	private TextArea textArea;

	public FileView() {

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
		this.textArea = new TextArea();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(selFile));
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println(str);
				this.textArea.appendText(str);
			}
		} catch (IOException e) {
		} finally {
			try { 
				in.close(); 
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		this.fileView.getChildren().add(this.textArea);
		return new Scene(this.fileView);
	}

	public Scene getSecondaryScene() {
		return this.secondaryScene;
	}
}
