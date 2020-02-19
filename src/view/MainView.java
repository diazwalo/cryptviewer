package view;

import java.awt.GraphicsEnvironment;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView {
	private Stage primaryStage;
	private HBox mainView;
	private Scene sc;
	
	private DecryptView decryptView;
	private CryptView cryptView;
	
	private static GraphicsEnvironment ge;
	
	public MainView(Stage primaryStage) {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		this.mainView = new HBox();
		this.decryptView = new DecryptView();
		this.cryptView = new CryptView();
		
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
		
		
		this.sc = new Scene(this.mainView);
		this.primaryStage.setScene(this.sc);
		this.primaryStage.show();
	}
	

	public static double getWinHeight() {
		return ge.getMaximumWindowBounds().getHeight();
	}

	public static double getWinWidth() {
		return ge.getMaximumWindowBounds().getWidth();
	}
}
