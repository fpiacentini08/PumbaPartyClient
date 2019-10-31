package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;

public class StartTestGameMessage extends SocketMessage
{

	private String clientId;
	
	public StartTestGameMessage()
	{
		super();
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
