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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;

/**
 * Controleur pour l'interface
 * @author binome 1-8
 *
 */
public class InterfaceController {

	private Stage primaryStage;
	private Client client;
	private FXMLLoader loader;

	@FXML private TextField usernameInput;
	@FXML private Text textUsername;
	@FXML private Text usernameError;
	@FXML private Text textErrorConnection;

	@FXML private ListView<String> messagesView;
	@FXML private ListView<String> usersView;
	
	@FXML private TextField messageInput;
	@FXML private Button messageBtn;

	public InterfaceController(Stage primaryStage, FXMLLoader loader, Client client) {
		this.primaryStage = primaryStage;
		this.client = client;
		this.loader = loader;
	}

	/**
	 * Lancer lorsque l'utilisateur rentre son username
	 * @throws IOException
	 */
	@FXML
	public void onSubmitUsername() throws IOException {
		System.out.println("submit username");
		
		String username = usernameInput.getText();
		if(username.matches("[a-zA-Z0-9]{3,}")) {
			loader = new FXMLLoader(getClass().getResource("SceneChat.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			primaryStage.setScene(new Scene(root));
			
			primaryStage.setOnCloseRequest((event) -> {
				System.out.println("stop");
				client.stop();
				System.exit(0);
			}) ;
			client.setUsername(usernameInput.getText());
			try {
				client.run();
				textUsername.setText(usernameInput.getText());
			} catch (Exception e) {
				textErrorConnection.setOpacity(1.0);
				messageBtn.setDisable(true);
				messageInput.setDisable(true);
				e.printStackTrace();
			}
			
		} else {
			usernameError.setOpacity(1.0);
		}
		
	}
	
	/**
	 * Lancer quand l'utilisateur envoie un message
	 */
	@FXML
	public void onSubmitMessage() {
		System.out.println("submit message");
		String msg = messageInput.getText();
		client.sendMessage(msg);
		messageInput.clear();
	}
	
	/**
	 * Permet d'ajouter un message dans l'interface
	 * @param msg Le message à ajouter
	 */
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