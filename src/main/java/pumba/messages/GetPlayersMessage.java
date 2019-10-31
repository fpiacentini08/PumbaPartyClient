package pumba.messages;

import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;
import pumba.sockets.Connector;

public class GetPlayersMessage extends SocketMessage
{
	private List<PlayerReduced> players;
	private String clientId;

	public GetPlayersMessage()
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

	public List<PlayerReduced> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<PlayerReduced> players)
	{
		this.players = players;
	}

	
}
