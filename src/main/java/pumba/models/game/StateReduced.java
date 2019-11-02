package pumba.models.game;

import pumba.models.players.PlayerReduced;

public class StateReduced
{
	private Integer activeTurn;
	private PlayerReduced activePlayer;
	private String activeStep;
	private Integer activeRound;

	public StateReduced()
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

	public PlayerReduced getActivePlayer()
	{
		return activePlayer;
	}

	public void setActivePlayer(PlayerReduced activePlayer)
	{
		this.activePlayer = activePlayer;
	}

	public String getActiveStep()
	{
		return activeStep;
	}

	public void setActiveStep(String activeStep)
	{
		this.activeStep = activeStep;
	}

	public Integer getActiveRound()
	{
		return activeRound;
	}

	public void setActiveRound(Integer activeRound)
	{
		this.activeRound = activeRound;
	}

	
}
