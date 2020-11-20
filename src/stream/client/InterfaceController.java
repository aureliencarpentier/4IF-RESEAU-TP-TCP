package stream.client;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Platform;

public class InterfaceController {

	private Stage primaryStage;
	private Client client;
	private FXMLLoader loader;

	@FXML private TextField usernameInput;

	@FXML private ListView<String> messagesView;
	@FXML private ListView<String> usersView;
	
	@FXML private TextField messageInput;
	@FXML private Button messageBtn;

	public InterfaceController(Stage primaryStage, FXMLLoader loader, Client client) {
		this.primaryStage = primaryStage;
		this.client = client;
		this.loader = loader;
	}

	@FXML
	public void onSubmitUsername() throws IOException {
		System.out.println("submit username");
		loader = new FXMLLoader(getClass().getResource("SceneChat.fxml"));
		loader.setController(this);
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
		
		primaryStage.setOnCloseRequest((event) -> {
			System.out.println("stop");
			client.stop();
			System.exit(0);
		}) ;
		
		System.out.println(messagesView);
		client.setUsername(usernameInput.getText());
		client.run();
	}
	
	@FXML
	public void onSubmitMessage() {
		System.out.println("submit message");
		String msg = messageInput.getText();
		client.sendMessage(msg);
		messageInput.clear();
	}
	
	public void addMessage(String msg)  {
		System.out.println(msg);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						messagesView.getItems().add(msg);
						
					}
				});
				return null;
			}
		};
		new Thread(task).start();
		
	}
}