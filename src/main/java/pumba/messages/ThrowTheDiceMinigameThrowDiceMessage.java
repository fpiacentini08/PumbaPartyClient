package pumba.messages;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameResult;

public class ThrowTheDiceMinigameThrowDiceMessage extends SocketMessage
{
	private ThrowTheDiceMinigameResult result;

	public ThrowTheDiceMinigameThrowDiceMessage()
	{
		super();
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
