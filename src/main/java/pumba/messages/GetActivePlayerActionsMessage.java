package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.actions.ActionReduced;
import pumba.sockets.Connector;

public class GetActivePlayerActionsMessage extends SocketMessage
{
	List<ActionReduced> actions = new ArrayList<>();
	private String clientId;
	
	
	public GetActivePlayerActionsMessage()
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

	public List<ActionReduced> getActions()
	{
		return actions;
	}

	public void setActions(List<ActionReduced> actions)
	{
		this.actions = actions;
	}

}
