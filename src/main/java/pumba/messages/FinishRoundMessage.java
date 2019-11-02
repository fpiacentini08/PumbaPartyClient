package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class FinishRoundMessage extends SocketMessage
{
	private String clientId;

	public FinishRoundMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

}
