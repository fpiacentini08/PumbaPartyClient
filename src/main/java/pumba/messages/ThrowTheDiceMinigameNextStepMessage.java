package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameStateReduced;
import pumba.sockets.Connector;

public class ThrowTheDiceMinigameNextStepMessage extends SocketMessage
{
	private ThrowTheDiceMinigameStateReduced actualState;

	private String clientId;

	public ThrowTheDiceMinigameNextStepMessage()
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

	public ThrowTheDiceMinigameStateReduced getActualState()
	{
		return actualState;
	}

	public void setActualState(ThrowTheDiceMinigameStateReduced actualState)
	{
		this.actualState = actualState;
	}

}
