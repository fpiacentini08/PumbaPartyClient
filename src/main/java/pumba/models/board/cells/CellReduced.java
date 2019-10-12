package pumba.models.board.cells;

public class CellReduced
{

	protected PositionReduced position;
	protected Boolean walkable;

	public CellReduced(PositionReduced position)
	{
		super();
		this.position = position;
	}

	public PositionReduced getPosition()
	{
		return position;
	}

	public Boolean getWalkable()
	{
		return walkable;
	}

	public void setWalkable(Boolean walkable)
	{
		this.walkable = walkable;
	}

	public void setPosition(PositionReduced position)
	{
		this.position = position;
	}

}
