package view;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView {
	private Stage primaryStage;
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
			this.fileView.createFileView(this.primaryStage);
		});
		this.decryptView.getSubmitDecryptButton().setOnAction(e -> {
			File file = new File ("c:");
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	public static double getWinHeight() {
		return ge.getMaximumWindowBounds().getHeight();
	}

	public static double getWinWidth() {
		return ge.getMaximumWindowBounds().getWidth();
	}
}
