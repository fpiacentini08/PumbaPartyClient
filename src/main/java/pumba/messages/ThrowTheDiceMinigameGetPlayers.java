package pumba.messages;

import java.util.Map;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;

public class ThrowTheDiceMinigameGetPlayers extends SocketMessage
{
	private Map<String, Integer> players;

	public ThrowTheDiceMinigameGetPlayers()
	{
		super();
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
