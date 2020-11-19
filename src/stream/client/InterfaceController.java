package stream.client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
		client.setUsername(usernameInput.getText());
		client.run();
		loader = new FXMLLoader(getClass().getResource("SceneChat.fxml"));
		loader.setController(this);
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
	}
	
	@FXML
	public void onSubmitMessage() {
		System.out.println("submit message");
		String msg = messageInput.getText();
		client.sendMessage(msg);
	}
}