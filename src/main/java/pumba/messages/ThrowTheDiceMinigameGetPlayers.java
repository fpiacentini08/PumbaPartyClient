package pumba.messages;

import java.util.Map;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;

public class ThrowTheDiceMinigameGetPlayers extends SocketMessage
{
	private Map<String, Integer> players;

	private String clientId;

	public ThrowTheDiceMinigameGetPlayers()
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

	public Map<String, Integer> getPlayers()
	{
		return players;
	}

	public void setPlayers(Map<String, Integer> players)
	{
		this.players = players;
	}

}
