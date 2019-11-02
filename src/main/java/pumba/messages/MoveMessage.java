package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.PositionReduced;

public class MoveMessage extends SocketMessage
{
	PositionReduced destination;
	List<PositionReduced> possiblePositions = new ArrayList<>();
	private String clientId;

	public MoveMessage(PositionReduced destination)
	{
		super();
		this.destination = destination;
		this.clientId = SocketMessage.getClientId();
	}

	public PositionReduced getDestination()
	{
		return destination;
	}

	public void setDestination(PositionReduced destination)
	{
		this.destination = destination;
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
