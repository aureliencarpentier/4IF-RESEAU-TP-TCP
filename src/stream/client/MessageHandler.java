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
	 * Permet de g�rer le message re�u
	 * @param msg Le message re�u
	 */
	public void handleMessage(String msg) {
		controller.addMessage(msg);
	}

}
