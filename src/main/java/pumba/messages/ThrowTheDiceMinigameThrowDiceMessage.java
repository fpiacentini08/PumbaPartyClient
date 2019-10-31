package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameResult;
import pumba.sockets.Connector;

public class ThrowTheDiceMinigameThrowDiceMessage extends SocketMessage
{
	private ThrowTheDiceMinigameResult result;

	private String clientId;
	
	public ThrowTheDiceMinigameThrowDiceMessage()
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

	public ThrowTheDiceMinigameResult getResult()
	{
		return result;
	}

	public void setResult(ThrowTheDiceMinigameResult result)
	{
		this.result = result;
	}

}
