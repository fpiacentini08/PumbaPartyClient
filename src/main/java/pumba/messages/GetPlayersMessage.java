package pumba.messages;

import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;

public class GetPlayersMessage extends SocketMessage
{
	private List<PlayerReduced> players;
	private String clientId;

	public GetPlayersMessage()
	{
		super();
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

}
