package pumba.messages;

import pumba.messages.utils.SocketMessage;;

public class StartTestGameMessage extends SocketMessage
{

	private String clientId;
	
	public StartTestGameMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

}
