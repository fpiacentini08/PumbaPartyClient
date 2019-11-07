package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class InterruptMessage extends SocketMessage
{
	private static String clientId;

	public InterruptMessage(String clientId)
	{
		super();
		this.clientId = clientId;
	}

}
