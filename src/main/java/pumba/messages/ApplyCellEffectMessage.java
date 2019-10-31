package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;
import pumba.sockets.Connector;

public class ApplyCellEffectMessage extends SocketMessage
{
	String effectDescription;
	List<PlayerReduced> players = new ArrayList<>();
	private String clientId;

	public ApplyCellEffectMessage()
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

	public String getEffectDescription()
	{
		return effectDescription;
	}

	public void setEffectDescription(String effectDescription)
	{
		this.effectDescription = effectDescription;
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
