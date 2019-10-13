package pumba.messages;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;

public class ThrowDiceMessage extends SocketMessage
{
	private Integer diceResult;

	public ThrowDiceMessage()
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

	public Integer getDiceResult()
	{
		return diceResult;
	}

	public void setDiceResult(Integer diceResult)
	{
		this.diceResult = diceResult;
	}

}
