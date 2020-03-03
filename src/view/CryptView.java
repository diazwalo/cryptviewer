package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class CryptView {
	private VBox cryptView;
	private Button sumbmitCryptButton;
	private ComboBox<String> cryptType;
	private CheckBox cryptOptionOveride;

	public CryptView() {
		this.cryptView = new VBox();
		this.setStyleOnCryptView();
	}

	private void setStyleOnCryptView() {
		this.cryptView.setAlignment(Pos.CENTER);
		this.cryptView.setPadding(new Insets(20.0));
	}

	protected void createViewNavigator() {
		createSubmitCrypt();
		createCryptType();
		createCryptOptionOveride();

		this.cryptView.getChildren().addAll(this.sumbmitCryptButton, this.cryptType, this.cryptOptionOveride);
	}

	private void createCryptOptionOveride() {
		this.cryptOptionOveride = new CheckBox(" : Ecraser le fichier");
	}

	private void createSubmitCrypt() {
		this.sumbmitCryptButton = new Button("Crypter");
	}

	private void createCryptType() {
		ObservableList<String> typesItems =FXCollections.observableArrayList ("AES", "DES", "DESede");
		this.cryptType = new ComboBox<String>(typesItems);
		this.cryptType.setPromptText(typesItems.get(0));
	}

	public VBox getCryptView() {
		return this.cryptView;
	}

	public Button getSumbmitCryptButton() {
		return this.sumbmitCryptButton;
	}

	public String getTypeCrypt() {
		System.out.println(this.cryptType.getPromptText());
		return this.cryptType.getValue();
	}
	
	public boolean isOptionOverideChecked() {
		return this.cryptOptionOveride.isSelected();
	}
}
