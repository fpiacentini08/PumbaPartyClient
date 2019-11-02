package pumba.messages;

import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameResult;

public class ThrowTheDiceMinigameThrowDiceMessage extends SocketMessage
{
	private ThrowTheDiceMinigameResult result;

	private String clientId;
	
	public ThrowTheDiceMinigameThrowDiceMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

	public ThrowTheDiceMinigameResult getResult()
	{
		return result;
	}

	public void setResult(ThrowTheDiceMinigameResult result)
	{
		this.result = result;
	}

}
