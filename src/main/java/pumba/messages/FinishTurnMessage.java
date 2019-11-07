package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class FinishTurnMessage extends SocketMessage
{
	private String clientId;

	public FinishTurnMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

}
