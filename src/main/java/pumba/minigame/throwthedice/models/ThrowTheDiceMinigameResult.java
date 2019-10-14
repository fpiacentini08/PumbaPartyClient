package pumba.minigame.throwthedice.models;

public class ThrowTheDiceMinigameResult
{

	private Integer diceResult;
	private Integer bugsEarned;
	private String description;

	public Integer getDiceResult()
	{
		return diceResult;
	}

	public void setDiceResult(Integer diceResult)
	{
		this.diceResult = diceResult;
	}

	public Integer getBugsEarned()
	{
		return bugsEarned;
	}

	public void setBugsEarned(Integer bugsEarned)
	{
		this.bugsEarned = bugsEarned;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public ThrowTheDiceMinigameResult()
	{
		super();
	}

}
