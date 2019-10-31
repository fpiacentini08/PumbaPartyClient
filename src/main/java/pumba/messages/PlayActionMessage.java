package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;
import pumba.sockets.Connector;

public class PlayActionMessage extends SocketMessage
{
	String actionDescription;
	String resultDescription;
	List<PlayerReduced> players = new ArrayList<>();
	private String clientId;

	public String getActionDescription()
	{
		return actionDescription;
	}

	public void setActionDescription(String actionDescription)
	{
		this.actionDescription = actionDescription;
	}

	public PlayActionMessage(String actionDescription)
	{
		super();
		this.actionDescription = actionDescription;
		this.clientId = SocketMessage.getClientId();

	}

	public List<PlayerReduced> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<PlayerReduced> players)
	{
		this.players = players;
	}

	public PlayActionMessage()
	{
		super();
	}

	public String getResultDescription()
	{
		return resultDescription;
	}

	public void setResultDescription(String resultDescription)
	{
		this.resultDescription = resultDescription;
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

}
