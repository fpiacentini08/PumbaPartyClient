package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;

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
