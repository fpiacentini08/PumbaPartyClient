package pumba.minigame.throwthedice.controllers;

import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.messages.ThrowTheDiceMinigameFinishTurnMessage;
import pumba.messages.ThrowTheDiceMinigameGetPlayers;
import pumba.messages.ThrowTheDiceMinigameNextStepMessage;
import pumba.messages.ThrowTheDiceMinigameStart;
import pumba.messages.ThrowTheDiceMinigameThrowDiceMessage;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;
import pumba.sockets.PumbaSocket;

public class ThrowTheDiceController
{
	public void getPlayers(Connector connector) throws PumbaException
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

	public void finishTurn(PumbaSocket socket)
	{
		SocketMessage message = new ThrowTheDiceMinigameFinishTurnMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void start(Connector connector, List<String> playersNames)
	{
		SocketMessage message = new ThrowTheDiceMinigameStart(playersNames);
		connector.setMessage(message);
		connector.run();

	}
	
	public void throwDice(PumbaSocket socket)
	{
		SocketMessage message = new ThrowTheDiceMinigameThrowDiceMessage();
		socket.setMessage(message);
		socket.run();
	}
	
//	public void throwDice(Listener socket)
//	{
//		SocketMessage message = new ThrowTheDiceMinigameThrowDiceMessage();
//		socket.setMessage(message);
//		socket.run();
//	}
//
//	public void throwDice(Connector socket)
//	{
//		SocketMessage message = new ThrowTheDiceMinigameThrowDiceMessage();
//		socket.setMessage(message);
//		socket.run();
//	}

}
