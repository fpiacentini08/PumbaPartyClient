package pumba.messages;

import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.CellReduced;;

public class GetBoardMessage extends SocketMessage
{
	private List<CellReduced> cells;
	private Integer dimension;
	private String clientId;

	public GetBoardMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

	public List<CellReduced> getCells()
	{
		return cells;
	}

	public void setCells(List<CellReduced> cells)
	{
		this.cells = cells;
	}

	public Integer getDimension()
	{
		return dimension;
	}

	public void setDimension(Integer dimension)
	{
		this.dimension = dimension;
	}

	
}
