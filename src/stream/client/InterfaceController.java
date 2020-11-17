package stream.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceController {

	private Stage stage;
	
	/**
	 * M�thode permettant de cr�er la fen�tre principale
	 */
	public void start(Stage primaryStage) {
		try {
			InterfaceController ui = new InterfaceController();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/stream/SceneChat.fxml"));
			loader.setController(ui);
			Parent root = loader.load();
			
			ui.setStage(primaryStage);

			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�thode test
	 */
	public void test() {
		System.out.println("test");
	}
	
	/**
	 * 
	 *  
	 * 
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}