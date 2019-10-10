package pumba.models.board.cells;

public abstract class CellReduced
{

	protected PositionReduced position;
	
	public CellReduced(PositionReduced position)
	{
		super();
		this.position = position;
	}

	
	public PositionReduced getPosition()
	{
		return position;
	}

	
	
}
