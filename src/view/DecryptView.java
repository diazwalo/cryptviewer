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
	private CheckBox decryptOptionOveride;
	
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
		this.createDecryptOptionOveride();
		
		this.decryptView.getChildren().addAll(this.submitDecryptButton, this.decryptType, this.decryptOptionOveride, this.optionOpenFile);
	}
	
	private void createDecryptOptionOveride() {
		this.decryptOptionOveride = new CheckBox(" : Ecraser le fichier");
	}
	
	private void createDecryptType() {
		ObservableList<String> typesItems =FXCollections.observableArrayList ("AES", "DES", "DESede");
		this.decryptType = new ComboBox<String>(typesItems);
		this.decryptType.setPromptText(typesItems.get(0));
	}

	private void createOptionOpenFile() {
		this.optionOpenFile = new CheckBox(" : Ouvrir le fichier");
		this.optionOpenFile.setDisable(true);
	}

	private void createSubmitDecrypt() {
		this.submitDecryptButton = new Button("Decrypter");
	}
	
	public VBox getDecryptView() {
		return this.decryptView;
	}
	
	public Button getSubmitDecryptButton() {
		return this.submitDecryptButton;
	}

	public String getDecryptType() {
		if(this.decryptType.getValue() == null) {
			return this.decryptType.getItems().get(0);
		}else {
			return this.decryptType.getValue();
		}
	}
	
	public boolean isOptionOpenFileChecked() {
		return this.optionOpenFile.isSelected();
	}
	
	public boolean isOptionOverideChecked() {
		return this.decryptOptionOveride.isSelected();
	}
}
