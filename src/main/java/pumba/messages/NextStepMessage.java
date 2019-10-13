package pumba.messages;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.game.StateReduced;

public class NextStepMessage extends SocketMessage
{
	private StateReduced actualState;

	public NextStepMessage()
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

	public StateReduced getActualState()
	{
		return actualState;
	}

	public void setActualState(StateReduced actualState)
	{
		this.actualState = actualState;
	}

}
