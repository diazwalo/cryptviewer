package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class DecryptView {
	private VBox decryptView;
	private Button submitDecryptButton;
	private CheckBox optionOpenFile;
	private ComboBox<String> decryptType;
	
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
		this.createDecryptType();
		
		this.decryptView.getChildren().addAll(this.submitDecryptButton, this.decryptType, this.optionOpenFile);
	}
	
	private void createDecryptType() {
		ObservableList<String> typesItems =FXCollections.observableArrayList ("AES", "DES", "DESede");
		this.decryptType = new ComboBox<String>(typesItems);
		this.decryptType.setPromptText(typesItems.get(0));
	}

	private void createOptionOpenFile() {
		this.optionOpenFile = new CheckBox(" : Ouvrir le fichier");
	}

	private void createSubmitDecrypt() {
		this.submitDecryptButton = new Button("Decrypter");
	}
	
	public VBox getDecryptView() {
		return this.decryptView;
	}
	
	public String getTypeDecrypt() {
		return this.decryptType.getPromptText();
	}
	
	public boolean isOptionOpenFileChecked() {
		return this.optionOpenFile.isSelected();
	}
	
	public Button getSubmitDecryptButton() {
		return this.submitDecryptButton;
	}
}
