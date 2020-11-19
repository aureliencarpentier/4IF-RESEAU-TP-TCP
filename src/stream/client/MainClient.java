package stream.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application {
	
	static Client client;
	InterfaceController controller;
	
	public static void main(String args[]){ 
		client = new Client(args[0],Integer.parseInt(args[1]));
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		controller = new InterfaceController(primaryStage, loader, client);
		
		loader.setController(controller);
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
        primaryStage.show();
	}
}