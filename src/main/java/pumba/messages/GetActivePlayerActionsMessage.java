package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.actions.ActionReduced;

public class GetActivePlayerActionsMessage extends SocketMessage
{
	List<ActionReduced> actions = new ArrayList<>();
	private String clientId;
	
	
	public GetActivePlayerActionsMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
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
