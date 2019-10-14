package pumba.minigame.throwthedice.models;

public class ThrowTheDiceMinigameStateReduced
{
	private Integer activeTurn;
	private String activePlayer;
	private Integer activeStep;

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

	public Integer getActiveStep()
	{
		return activeStep;
	}

	public void setActiveStep(Integer activeStep)
	{
		this.activeStep = activeStep;
	}

}
