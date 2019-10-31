package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;

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

	@Override
	public void processResponse(Object object) throws PumbaException
	{
		Connector connector = (Connector) object;

		if (!connector.getMessage().getApproved())
		{
			this.setErrorMessage(connector.getMessage().getErrorMessage());
		}

	}

}
