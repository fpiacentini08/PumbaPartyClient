package pumba.messages;

import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.PositionReduced;
import pumba.sockets.Connector;

public class GetPossiblePositionsMessage extends SocketMessage
{
	List<PositionReduced> possiblePositions = new ArrayList<>();
	private String clientId;

	public GetPossiblePositionsMessage()
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

	public List<PositionReduced> getPossiblePositions()
	{
		return possiblePositions;
	}

	public void setPossiblePositions(List<PositionReduced> possiblePositions)
	{
		this.possiblePositions = possiblePositions;
	}
	
	

}
