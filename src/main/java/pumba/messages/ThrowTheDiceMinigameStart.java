package pumba.messages;

import java.util.List;
import java.util.Map;

import pumba.messages.utils.SocketMessage;

public class ThrowTheDiceMinigameStart extends SocketMessage
{
	private Map<String, Integer> players;
	private List<String> playersNames;

	
	private String clientId;
	
	public ThrowTheDiceMinigameStart(List<String> playersNames)
	{
		super();
		this.playersNames = playersNames;
		this.clientId = SocketMessage.getClientId();
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
