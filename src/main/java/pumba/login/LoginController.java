package pumba.login;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.LoginMessage;
import pumba.messages.RegisterUserMessage;
import pumba.messages.utils.SocketMessage;

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
