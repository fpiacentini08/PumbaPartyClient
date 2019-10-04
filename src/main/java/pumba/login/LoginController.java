package main.java.pumba.login;

import main.java.pumba.exceptions.PumbaException;
import main.java.pumba.messages.LoginMessage;
import main.java.pumba.messages.SocketMessage;

public class LoginController {

	public void registerUser(String username, String password) throws PumbaException {
		LoginConnector loginConnector = new LoginConnector();
		
		SocketMessage message = new LoginMessage("Usuario registrado! " + username + ":" + password);
		
		loginConnector.setMessage(message);
		loginConnector.run();
	}
}
