package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class RegisterUserMessage extends SocketMessage
{
	private String username;
	private String password;
	private String clientId;

	public RegisterUserMessage(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
		this.clientId = SocketMessage.getClientId();
	}

}
