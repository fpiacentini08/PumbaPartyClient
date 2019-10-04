package main.java.pumba.messages;

import main.java.pumba.login.Connector;
import main.java.pumba.messages.utils.SocketMessage;

public class LoginMessage extends SocketMessage
{
	private String username;
	private String password;
	
	
	public LoginMessage(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public void processResponse(Object object)
	{
		Connector connector = (Connector) object;
	}

}
