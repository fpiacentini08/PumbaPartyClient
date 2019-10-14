package pumba.minigame.throwthedice.controllers;

import java.util.List;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.ThrowTheDiceMinigameFinishTurnMessage;
import pumba.messages.ThrowTheDiceMinigameGetPlayers;
import pumba.messages.ThrowTheDiceMinigameNextStepMessage;
import pumba.messages.ThrowTheDiceMinigameStart;
import pumba.messages.ThrowTheDiceMinigameThrowDiceMessage;
import pumba.messages.utils.SocketMessage;

public class ThrowTheDiceController
{
	public void getPlayers(Connector connector)
			throws PumbaException
	{
		SocketMessage message = new ThrowTheDiceMinigameGetPlayers();
		connector.setMessage(message);
		connector.run();
	}

	public void nextStep(Connector connector)
	{
		SocketMessage message = new ThrowTheDiceMinigameNextStepMessage();
		connector.setMessage(message);
		connector.run();		
	}

	public void finishRound(Connector connector)
	{
		// TODO Auto-generated method stub
		
	}

	public void finishTurn(Connector connector)
	{
		SocketMessage message = new ThrowTheDiceMinigameFinishTurnMessage();
		connector.setMessage(message);
		connector.run();		
		
	}

	public void throwDice(Connector connector)
	{
		SocketMessage message = new ThrowTheDiceMinigameThrowDiceMessage();
		connector.setMessage(message);
		connector.run();		
		
	}

	public void start(Connector connector, List<String> playersNames)
	{
		SocketMessage message = new ThrowTheDiceMinigameStart(playersNames);
		connector.setMessage(message);
		connector.run();		
		
	}

}
