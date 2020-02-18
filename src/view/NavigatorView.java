package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NavigatorView {
	private VBox navigatorView;
	private HBox containerButton;
	
	public NavigatorView() {
		this.navigatorView = new VBox();
		this.containerButton = new HBox();
	}
	
	protected void createViewNavigator() {
		this.containerButton.getChildren().addAll(new Button("Crypter"), new Button("Decrypter"));
		this.navigatorView.getChildren().addAll(this.containerButton);
	}
	
	protected VBox getNavigatorView() {
		return this.navigatorView;
	}
}
