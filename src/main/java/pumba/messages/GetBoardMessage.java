package pumba.messages;

import java.util.List;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.CellReduced;

public class GetBoardMessage extends SocketMessage
{
	private List<CellReduced> cells;
	private Integer dimension;

	public GetBoardMessage()
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
		System.out.println(cells);
		System.out.println(dimension);

	}

}
