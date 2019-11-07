package pumba.minigame.throwthedice.controllers;

import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.messages.ThrowTheDiceMinigameFinishTurnMessage;
import pumba.messages.ThrowTheDiceMinigameGetPlayers;
import pumba.messages.ThrowTheDiceMinigameNextStepMessage;
import pumba.messages.ThrowTheDiceMinigameStart;
import pumba.messages.ThrowTheDiceMinigameThrowDiceMessage;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.PumbaSocket;

public class ThrowTheDiceController
{
	public void getPlayers(PumbaSocket socket) throws PumbaException
	{
		SocketMessage message = new ThrowTheDiceMinigameGetPlayers();
		socket.setMessage(message);
		socket.run();
	}

	public void nextStep(PumbaSocket socket)
	{
		SocketMessage message = new ThrowTheDiceMinigameNextStepMessage();
		socket.setMessage(message);
		socket.run();
	}

	public void finishTurn(PumbaSocket socket)
	{
		SocketMessage message = new ThrowTheDiceMinigameFinishTurnMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void start(PumbaSocket socket, List<String> playersNames)
	{
		SocketMessage message = new ThrowTheDiceMinigameStart(playersNames);
		socket.setMessage(message);
		socket.run();

	}
	
	public void throwDice(PumbaSocket socket)
	{
		SocketMessage message = new ThrowTheDiceMinigameThrowDiceMessage();
		socket.setMessage(message);
		socket.run();
	}
	


}
