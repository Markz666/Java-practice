package indi.zmx.clientserver;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {
	@Override
	public void start(Stage primaryStage) {
		TextArea ta = new TextArea();
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();

		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));

				Socket socket = serverSocket.accept();

				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

				while (true) {
					double radius = inputFromClient.readDouble();
					double area = radius * radius * Math.PI;

					outputToClient.writeDouble(area);

					Platform.runLater(() -> {
						ta.appendText("Radius received from client: " + radius + '\n');
						ta.appendText("Area is: " + area + '\n');
					});
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
