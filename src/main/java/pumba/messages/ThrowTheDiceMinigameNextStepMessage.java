package pumba.messages;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameStateReduced;

public class ThrowTheDiceMinigameNextStepMessage extends SocketMessage
{
	private ThrowTheDiceMinigameStateReduced actualState;

	private String clientId;

	public ThrowTheDiceMinigameNextStepMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
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
