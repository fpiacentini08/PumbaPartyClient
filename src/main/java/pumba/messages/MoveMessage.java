package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.PositionReduced;

public class MoveMessage extends SocketMessage
{
	PositionReduced destination;
	List<PositionReduced> possiblePositions = new ArrayList<>();

	public MoveMessage(PositionReduced destination)
	{
		super();
		this.destination = destination;
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
