package com.zmx.javafxpractice;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static com.zmx.javafxpractice.ShiftCipher.shiftCipher;
public class CipherPane extends Application {
	private TextField strField = new TextField();
	private TextField offsetField = new TextField();
	private TextField resultField = new TextField();
	@Override
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		pane.add(new Label("Original String:"), 0, 0);
		pane.add(strField, 1, 0);
		pane.add(new Label("Offset:"), 0, 1);
		pane.add(offsetField, 1, 1);
		pane.add(new Label("Result"), 0, 2);
		pane.add(resultField, 1, 2);
		resultField.setEditable(false);

		Button runBtn = new Button("Run");
		pane.add(runBtn, 1, 3);
		GridPane.setHalignment(runBtn, HPos.RIGHT);
		
		runBtn.setOnAction(e -> cipher(strField.getText(), offsetField.getText()));
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("ShiftCipher");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	public void cipher(String str, String offset) {
		resultField.setText("");
		int offsetInt = 0;
		try {
			offsetInt = Integer.valueOf(offset);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String result = "";
		if (isAlpha(str)) {
			result += shiftCipher(str, offsetInt);	
		}
		resultField.appendText(result);
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
