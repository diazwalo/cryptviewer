package view;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.CryptDecrypt;

public class MainView {
	private Stage primaryStage;
	private Stage secondaryStage;
	private HBox mainView;
	private Scene sc;
	private Separator separateur;


	private DecryptView decryptView;
	private CryptView cryptView;
	private EnterKeyView enterKeyView;

	private static GraphicsEnvironment ge;
	
	public MainView(Stage primaryStage) {
		this.createFolderCryptIfNotExist();
		
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		this.mainView = new HBox();
		this.decryptView = new DecryptView();
		this.cryptView = new CryptView();
		this.enterKeyView = new EnterKeyView();
		
		this.primaryStage = primaryStage;
		this.separateur = new Separator(Orientation.VERTICAL);

	}

	public void createMainView() {
		this.primaryStage.setTitle("CryptViewer");
		this.primaryStage.setResizable(false);
		this.primaryStage.setMaxHeight(getWinHeight());
		this.primaryStage.setMaxWidth(getWinWidth());

		this.decryptView.createViewNavigator();
		this.cryptView.createViewNavigator();

		this.mainView.getChildren().addAll(this.cryptView.getCryptView(),this.separateur, this.decryptView.getDecryptView());
		this.setOnActionMainView();
	    this.mainView.setPadding(new Insets(10, 0, 10, 0));


		this.sc = new Scene(this.mainView);
		this.primaryStage.setScene(this.sc);
		this.primaryStage.show();
	}

	private void setOnActionMainView() {
		//Set Action sur le button "Crypter"
		this.cryptView.getSumbmitCryptButton().setOnAction(e -> {
			File fileDecrypted = this.getFileByOpenDialog(this.getExtensionAccepted());

			if(fileDecrypted != null) {
				int lengthKey = 8;
				if(this.cryptView.getTypeCrypt().equals("AES")) {
					lengthKey = 16;
				}else if(this.cryptView.getTypeCrypt().equals("DESede")) {
					lengthKey = 24;
				}
				this.secondaryStage = this.enterKeyView.createFileView(primaryStage, lengthKey);
				this.enterKeyView.getValidateButton().setOnAction(eButton -> {
					CryptDecrypt.createKey(this.enterKeyView.getKeyInput(), this.cryptView.getTypeCrypt());
					this.secondaryStage.hide();
				});
				
				File fileTempo = null;
				if(! this.cryptView.isOptionOverideChecked()) {
					String nameOfFile = fileDecrypted.getName();
					String pathOfFile = fileDecrypted.getAbsolutePath();
					
					fileTempo = new File(System.getProperty("user.home")+"/Chiffre/"+fileDecrypted.getName().substring(0, fileDecrypted.getName().lastIndexOf("."))+ "_crypted" + nameOfFile.substring(nameOfFile.lastIndexOf(".")));
				}
				CryptDecrypt.encrypt(CryptDecrypt.myKey, this.cryptView.getTypeCrypt(), fileDecrypted, this.cryptView.isOptionOverideChecked(), fileTempo);
			}
		});
		
		//Set Action sur le button "Decrypter"
		this.decryptView.getSubmitDecryptButton().setOnAction(e -> {
			File fileCrypted = this.getFileByOpenDialog(this.getExtensionAccepted());
			
			if(fileCrypted != null) {
				File fileTempo = null;
				if(! this.decryptView.isOptionOverideChecked()) {
					String nameOfFile = fileCrypted.getName();
					String pathOfFile = fileCrypted.getAbsolutePath();
					
					fileTempo = new File(pathOfFile.substring(0, pathOfFile.lastIndexOf(".")) + "_decrypted" + nameOfFile.substring(nameOfFile.lastIndexOf(".")));
				}
				CryptDecrypt.decrypt(CryptDecrypt.myKey, this.decryptView.getDecryptType(), fileCrypted, this.decryptView.isOptionOverideChecked(), fileTempo);
			}
			
			if(this.decryptView.isOptionOpenFileChecked()) {
				this.openFile(fileCrypted);
			}
		});
	}

	private void createFolderCryptIfNotExist() {
		File folder = new File(System.getProperty("user.home"),"Chiffre");
		if(! folder.exists()) {
			folder.mkdir();
			
		}
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
		if(fileToOpen != null) {
			try {
				Desktop.getDesktop().open(fileToOpen);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
