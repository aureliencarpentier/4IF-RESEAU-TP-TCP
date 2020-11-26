package stream.client;

/**
 * Classe qui permet de faire le lien entre le client et le controller lorsque le client recoit un message
 * @author Utilisateur
 *
 */
public class MessageHandler {
	
	private InterfaceController controller;
	
	/**
	 * Contructeur
	 * @param controller
	 */
	public MessageHandler(InterfaceController controller) {
		this.controller = controller;
	}
	
	/**
	 * Permet de gérer le message reçu
	 * @param msg Le message reçu
	 */
	public void handleMessage(String msg) {
		controller.addMessage(msg);
	}

}
