package com.zmx.javafxpractice;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
public class ButtonDemo extends Application {
	protected Text text = new Text(50, 50, "Hello World");
	protected BorderPane getPane() {
		HBox paneForButtons = new HBox(20);
		Button btLeft = new Button("", new ImageView("image/left.png"));
		Button btRight = new Button("", new ImageView("image/right.png"));
		paneForButtons.getChildren().addAll(btLeft, btRight);
		paneForButtons.setAlignment(Pos.CENTER);
		paneForButtons.setStyle("-fx-border-color: green");
		
		BorderPane pane = new BorderPane();
		pane.setBottom(paneForButtons);
		
		Pane paneForText = new Pane();
		paneForText.getChildren().add(text);
		pane.setCenter(paneForText);
		
		btLeft.setOnAction(e -> text.setX(text.getX() - 10));
		btRight.setOnAction(e -> text.setX(text.getX() + 10));
		return pane;
	}
	public void start(Stage primaryStage) {
		Scene scene = new Scene(getPane(), 500, 250);
		primaryStage.setTitle("ButtonDemo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
}
