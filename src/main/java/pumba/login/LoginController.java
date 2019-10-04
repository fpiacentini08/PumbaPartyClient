package main.java.pumba.login;

import main.java.pumba.exceptions.PumbaException;
import main.java.pumba.messages.LoginMessage;
import main.java.pumba.messages.utils.SocketMessage;

public class LoginController
{

	public void registerUser(String username, String password) throws PumbaException
	{
		Connector loginConnector = new Connector();

		SocketMessage message = new LoginMessage(username, password);

		loginConnector.setMessage(message);
		loginConnector.run();
	}
}
