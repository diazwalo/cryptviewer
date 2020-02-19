package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class DecryptView {
	private VBox decryptView;
	private Button submitDecryptButton;
	private CheckBox optionOpenFile;
	
	public DecryptView() {
		this.decryptView = new VBox();
		this.setStyleOnDecryptView();
	}
	
	private void setStyleOnDecryptView() {
		this.decryptView.setAlignment(Pos.CENTER);
	}

	protected void createViewNavigator() {
		this.createSubmitDecrypt();
		this.createOptionOpenFile();
		
		this.decryptView.getChildren().addAll(this.submitDecryptButton, this.optionOpenFile);
	}

	private void createOptionOpenFile() {
		this.optionOpenFile = new CheckBox(" : Ouvrir le fichier apres décryptage");
	}

	private void createSubmitDecrypt() {
		this.submitDecryptButton = new Button("Decrypter");
	}
	
	public VBox getDecryptView() {
		return this.decryptView;
	}
	
	public boolean isOptionOpenFileChecked() {
		return this.optionOpenFile.isSelected();
	}
}
