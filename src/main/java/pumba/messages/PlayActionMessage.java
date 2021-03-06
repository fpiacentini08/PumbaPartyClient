package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;

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
		this.clientId = SocketMessage.getClientId();
	}

	public String getResultDescription()
	{
		return resultDescription;
	}

	public void setResultDescription(String resultDescription)
	{
		this.resultDescription = resultDescription;
	}

}
