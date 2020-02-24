package view;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainView {
	private Stage primaryStage;
	private Stage secondaryStage;
	private HBox mainView;
	private Scene sc;

	private DecryptView decryptView;
	private CryptView cryptView;
	private FileView fileView;

	private static GraphicsEnvironment ge;

	public MainView(Stage primaryStage) {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		this.mainView = new HBox();
		this.decryptView = new DecryptView();
		this.cryptView = new CryptView();
		this.fileView = new FileView();

		this.primaryStage = primaryStage;
	}

	public void createMainView() {
		this.primaryStage.setTitle("CryptViewer");
		this.primaryStage.setResizable(false);
		this.primaryStage.setMaxHeight(getWinHeight());
		this.primaryStage.setMaxWidth(getWinWidth());

		this.decryptView.createViewNavigator();
		this.cryptView.createViewNavigator();

		this.mainView.getChildren().addAll(this.cryptView.getCryptView(), this.decryptView.getDecryptView());
		this.setOnActionMainView();

		this.sc = new Scene(this.mainView);
		this.primaryStage.setScene(this.sc);
		this.primaryStage.show();
	}

	private void setOnActionMainView() {
		this.decryptView.getSubmitDecryptButton().setOnAction(e -> {
			File fileToCrypt = this.getFileByOpenDialog(this.getExtensionAccepted());
			
			/**
			 * TRAITEMENT DU FICHIER fileToCrypt
			 */
			
			this.openFile(fileToCrypt);
		});
		this.cryptView.getSumbmitCryptButton().setOnAction(e -> {
			File fileToDecrypt = this.getFileByOpenDialog(this.getExtensionAccepted());

			/**
			 * TRAITEMENT DU FICHIER fileToDecrypt
			 */
			
			this.openFile(fileToDecrypt);
		});
	}

	public static double getWinHeight() {
		return ge.getMaximumWindowBounds().getHeight();
	}

	public static double getWinWidth() {
		return ge.getMaximumWindowBounds().getWidth();
	}

	public File getFileByOpenDialog(List<ExtensionFilter> filter) {
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(filter);
		fileChooser.setTitle("Open Resource File");

		File fileChoosen = fileChooser.showOpenDialog(primaryStage);
		System.out.println(fileChoosen.getName());

		return fileChoosen;
	}

	public List<ExtensionFilter> getExtensionAccepted() {
		List<ExtensionFilter> extensionAccepted = new ArrayList<ExtensionFilter> ();

		extensionAccepted.add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
		extensionAccepted.add(new FileChooser.ExtensionFilter("PNG", "*.png"));
		extensionAccepted.add(new FileChooser.ExtensionFilter("TXT", "*.txt"));

		return extensionAccepted;
	}
	
	public void openFile(File fileToOpen) {
		try {
			Desktop.getDesktop().open(fileToOpen);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
