package com.zmx.javafxpractice;
import static com.zmx.javafxpractice.ShiftCipher.*;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;
public class DecipherPane extends Application {
	private TextField ori1Field = new TextField();
	private TextField ori2Field = new TextField();
	private TextField strField = new TextField();
	private TextField resultField = new TextField();
	@Override
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		pane.add(new Label("Original String1:"), 0, 0);
		pane.add(ori1Field, 1, 0);
		pane.add(new Label("Original String2:"), 0, 1);
		pane.add(ori2Field, 1, 1);
		pane.add(new Label("Your input:"), 0, 2);
		pane.add(strField, 1, 2);
		pane.add(new Label("Result:"), 0, 3);
		pane.add(resultField, 1, 3);
		resultField.setEditable(false);

		Button runBtn = new Button("Decipher");
		pane.add(runBtn, 1, 4);
		GridPane.setHalignment(runBtn, HPos.RIGHT);
		
		runBtn.setOnAction(e -> decipher(ori1Field.getText(), ori2Field.getText(), strField.getText()));
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("DecipherString");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	public void decipher(String s1, String s2, String s3) {
		resultField.setText("");
		List<Integer> offsets = computeOffset(s1, s2);
		String result = "";
		if (isAlpha(s3)) {
			result += decipherStr(s3, offsets);	
		}
		resultField.appendText(result);
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
