package pumba.models.players;

import java.util.List;

import pumba.models.board.cells.PositionReduced;

public class PlayerReduced implements Comparable<PlayerReduced>
{

	String username;
	Integer coins;
	PositionReduced position;
	List<String> actions;

	public String getUsername()
	{
		return username;
	}

	public Integer getCoins()
	{
		return coins;
	}

	public void setCoins(Integer coins)
	{
		this.coins = coins;
	}

	public PositionReduced getPosition()
	{
		return position;
	}

	public void setPosition(PositionReduced position)
	{
		this.position = position;
	}

	@Override
	public int compareTo(PlayerReduced otherPlayer)
	{
		if (this.coins > otherPlayer.getCoins())
		{
			return 1;
		}
		else if (this.coins < otherPlayer.getCoins())
		{
			return -1;
		}
		return 0;
	}

}
