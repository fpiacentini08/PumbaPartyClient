package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.PositionReduced;

public class GetPossiblePositionsMessage extends SocketMessage
{
	List<PositionReduced> possiblePositions = new ArrayList<>();
	private String clientId;

	public GetPossiblePositionsMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

	public List<PositionReduced> getPossiblePositions()
	{
		return possiblePositions;
	}

	public void setPossiblePositions(List<PositionReduced> possiblePositions)
	{
		this.possiblePositions = possiblePositions;
	}
	
	

}
