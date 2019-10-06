package pumba.messages;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;

public class RegisterUserMessage extends SocketMessage
{
	private String username;
	private String password;

	public RegisterUserMessage(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
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
