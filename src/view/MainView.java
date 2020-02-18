package view;

import java.awt.GraphicsEnvironment;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView {
	private Stage primaryStage;
	private HBox mainView;
	private Scene sc;
	
	private static GraphicsEnvironment ge;
	
	public MainView(Stage primaryStage) {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		this.mainView = new HBox();
		
		this.createMainView(primaryStage);
	}

	private void createMainView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("CryptViewer");
		this.primaryStage.setMaximized(true);
		this.primaryStage.setResizable(false);
		this.primaryStage.setMaxHeight(getWinHeight());
		this.primaryStage.setMaxWidth(getWinWidth());

		Label welcome = new Label("WELCOME !");
		this.mainView.getChildren().add(welcome);
		
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
