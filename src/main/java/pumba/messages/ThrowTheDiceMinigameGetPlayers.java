package pumba.messages;

import java.util.Map;

import pumba.messages.utils.SocketMessage;

public class ThrowTheDiceMinigameGetPlayers extends SocketMessage
{
	private Map<String, Integer> players;

	private String clientId;

	public ThrowTheDiceMinigameGetPlayers()
	{
		super();
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

}
