package core;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainView;

public class Core extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		MainView mw = new MainView(primaryStage);
	}
}
