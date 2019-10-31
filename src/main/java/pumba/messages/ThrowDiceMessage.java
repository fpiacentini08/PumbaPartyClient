package pumba.messages;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;

public class ThrowDiceMessage extends SocketMessage
{
	private Integer diceResult;
	private String clientId;

	public ThrowDiceMessage()
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

	public Integer getDiceResult()
	{
		return diceResult;
	}

	public void setDiceResult(Integer diceResult)
	{
		this.diceResult = diceResult;
	}

}
