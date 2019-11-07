package pumba.minigame.throwthedice.models;

public class ThrowTheDiceMinigameStateReduced
{
	private Integer activeTurn;
	private String activePlayer;
	private ThrowTheDiceMinigameStateEnum activeStep;

	public ThrowTheDiceMinigameStateReduced()
	{
		super();
	}

	public Integer getActiveTurn()
	{
		return activeTurn;
	}

	public void setActiveTurn(Integer activeTurn)
	{
		this.activeTurn = activeTurn;
	}

	public String getActivePlayer()
	{
		return activePlayer;
	}

	public void setActivePlayer(String activePlayer)
	{
		this.activePlayer = activePlayer;
	}

	public ThrowTheDiceMinigameStateEnum getActiveStep()
	{
		return activeStep;
	}

	public void setActiveStep(ThrowTheDiceMinigameStateEnum activeStep)
	{
		this.activeStep = activeStep;
	}

}
