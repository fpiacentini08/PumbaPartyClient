package pumba.messages;

import pumba.messages.utils.SocketMessage;
import pumba.models.game.StateReduced;

public class NextStepMessage extends SocketMessage
{
	private StateReduced actualState;
	private String clientId;

	public NextStepMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

	public StateReduced getActualState()
	{
		return actualState;
	}

	public void setActualState(StateReduced actualState)
	{
		this.actualState = actualState;
	}

}
