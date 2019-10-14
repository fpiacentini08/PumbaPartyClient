package pumba.messages;

import java.util.List;
import java.util.Map;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;

public class ThrowTheDiceMinigameStart extends SocketMessage
{
	private Map<String, Integer> players;
	private List<String> playersNames;

	public ThrowTheDiceMinigameStart(List<String> playersNames)
	{
		super();
		this.playersNames = playersNames;
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

	public Map<String, Integer> getPlayers()
	{
		return players;
	}

	public void setPlayers(Map<String, Integer> players)
	{
		this.players = players;
	}

	public List<String> getPlayersNames()
	{
		return playersNames;
	}

	public void setPlayersNames(List<String> playersNames)
	{
		this.playersNames = playersNames;
	}

}
