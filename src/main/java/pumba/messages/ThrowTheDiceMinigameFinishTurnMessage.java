package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class ThrowTheDiceMinigameFinishTurnMessage extends SocketMessage
{
	
	
	private String clientId;

	public ThrowTheDiceMinigameFinishTurnMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

}
