package stream.client;

public class MessageHandler {
	
	private InterfaceController controller;
	
	public MessageHandler(InterfaceController controller) {
		this.controller = controller;
	}
	
	
	public void handleMessage(String msg) {
		controller.addMessage(msg);
	}

}
