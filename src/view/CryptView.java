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
	private CheckBox cryptOption1;
	private CheckBox cryptOption2;

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
		createCryptOption();

		this.cryptView.getChildren().addAll(this.sumbmitCryptButton, this.cryptType, this.cryptOption1, this.cryptOption2);
	}

	private void createCryptOption() {
		this.cryptOption1 = new CheckBox(" : Option 1");
		this.cryptOption2 = new CheckBox(" : Option 2");
	}

	private void createSubmitCrypt() {
		this.sumbmitCryptButton = new Button("Crypter");
	}

	private void createCryptType() {
		ObservableList<String> typesItems =FXCollections.observableArrayList ("RSA", "SMIC", "ChOMaGe");
		this.cryptType = new ComboBox<String>(typesItems);
		this.cryptType.setPromptText("RSA");
	}

	public VBox getCryptView() {
		return this.cryptView;
	}

	public boolean isOption1Checked() {
		return this.cryptOption1.isSelected();
	}

	public boolean isOption2Checked() {
		return this.cryptOption2.isSelected();
	}

	public String getCryptType() {
		return this.cryptType.getPromptText();
	}
}
