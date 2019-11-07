package pumba.messages;

import pumba.messages.utils.SocketMessage;

public class ThrowDiceMessage extends SocketMessage
{
	private Integer diceResult;
	private String clientId;

	public ThrowDiceMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();

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
