package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class LoginMessage extends SocketMessage
{
	private String username;
	private String password;
	private String clientId;

	public LoginMessage(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
		SocketMessage.setClientId(this.username);
		this.clientId = SocketMessage.getClientId();
	}

}
