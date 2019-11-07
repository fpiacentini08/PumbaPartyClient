package pumba.controllers;

import pumba.exceptions.PumbaException;
import pumba.messages.ApplyCellEffectMessage;
import pumba.messages.FinishRoundMessage;
import pumba.messages.FinishTurnMessage;
import pumba.messages.GetActivePlayerActionsMessage;
import pumba.messages.GetPlayersMessage;
import pumba.messages.GetPossiblePositionsMessage;
import pumba.messages.MoveMessage;
import pumba.messages.NextStepMessage;
import pumba.messages.PlayActionMessage;
import pumba.messages.StartTestGameMessage;
import pumba.messages.ThrowDiceMessage;
import pumba.messages.utils.SocketMessage;
import pumba.models.board.cells.PositionReduced;
import pumba.sockets.PumbaSocket;

public class GameController
{

	public void startTestGame(PumbaSocket socket) throws PumbaException
	{
		SocketMessage message = new StartTestGameMessage();
		socket.setMessage(message);
		socket.run();
	}

	public void getPlayers(PumbaSocket socket) throws PumbaException
	{
		SocketMessage message = new GetPlayersMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void nextStep(PumbaSocket socket) throws PumbaException
	{
		SocketMessage message = new NextStepMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void throwDice(PumbaSocket socket) throws PumbaException
	{
		SocketMessage message = new ThrowDiceMessage();
		socket.setMessage(message);
		socket.run();
	}

	public void getPossiblePositions(PumbaSocket socket)
	{
		SocketMessage message = new GetPossiblePositionsMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void move(PumbaSocket socket, PositionReduced positionReduced)
	{
		SocketMessage message = new MoveMessage(positionReduced);
		socket.setMessage(message);
		socket.run();

	}

	public void applyCellEffect(PumbaSocket socket)
	{
		SocketMessage message = new ApplyCellEffectMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void getActivePlayerActions(PumbaSocket socket)
	{
		SocketMessage message = new GetActivePlayerActionsMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void playAction(PumbaSocket socket, String actionDescription)
	{
		SocketMessage message = new PlayActionMessage(actionDescription);
		socket.setMessage(message);
		socket.run();

	}

	public void finishTurn(PumbaSocket socket)
	{
		SocketMessage message = new FinishTurnMessage();
		socket.setMessage(message);
		socket.run();

	}

	public void finishRound(PumbaSocket socket)
	{
		SocketMessage message = new FinishRoundMessage();
		socket.setMessage(message);
		socket.run();

	}

}
