package pumba.models.game;

import pumba.models.players.PlayerReduced;

public class StateReduced
{
	private Integer activeTurn;
	private PlayerReduced activePlayer;
	private Integer activeStep;

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

	public Integer getActiveStep()
	{
		return activeStep;
	}

	public void setActiveStep(Integer activeStep)
	{
		this.activeStep = activeStep;
	}

}
