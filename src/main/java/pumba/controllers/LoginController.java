package pumba.controllers;

import pumba.exceptions.PumbaException;
import pumba.messages.LoginMessage;
import pumba.messages.RegisterUserMessage;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;

public class LoginController
{

	public void registerUser(Connector connector, String username, String password)
			throws PumbaException
	{
		SocketMessage message = new RegisterUserMessage(username, password);
		connector.setMessage(message);
		connector.run();
	}

	public void loginUser(Connector connector, String username, String password)
			throws PumbaException

	{
		SocketMessage message = new LoginMessage(username, password);
		connector.setMessage(message);
		connector.run();
		
	}
}
