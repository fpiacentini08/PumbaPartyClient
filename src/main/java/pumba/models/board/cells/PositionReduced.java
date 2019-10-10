package pumba.models.board.cells;

public class PositionReduced
{
	private Integer posX;
	private Integer posY;

	public Integer getPosX()
	{
		return posX;
	}

	public void setPosX(Integer posX)
	{
		this.posX = posX;
	}

	public Integer getPosY()
	{
		return posY;
	}

	public void setPosY(Integer posY)
	{
		this.posY = posY;
	}

	public PositionReduced(Integer posX, Integer posY)
	{
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public PositionReduced()
	{
		super();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((posX == null) ? 0 : posX.hashCode());
		result = prime * result + ((posY == null) ? 0 : posY.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionReduced other = (PositionReduced) obj;
		if (posX == null)
		{
			if (other.posX != null)
				return false;
		}
		else if (!posX.equals(other.posX))
			return false;
		if (posY == null)
		{
			if (other.posY != null)
				return false;
		}
		else if (!posY.equals(other.posY))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "(" + posX + "," + posY + ")";
	}

}
