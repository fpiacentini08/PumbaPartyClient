package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class StartAllTimeConnectionMessage extends SocketMessage
{
	private String clientId;

	public StartAllTimeConnectionMessage(String clientId)
	{
		super();
		this.clientId = clientId;
	}

}
