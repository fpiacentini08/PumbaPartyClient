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
import pumba.sockets.Connector;
import pumba.sockets.Listener;

public class GameController
{

	public void startTestGame(Connector connector) throws PumbaException
	{
		SocketMessage message = new StartTestGameMessage();
		connector.setMessage(message);
		connector.run();
	}

	public void getPlayers(Connector connector) throws PumbaException
	{
		SocketMessage message = new GetPlayersMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void nextStep(Connector connector) throws PumbaException
	{
		SocketMessage message = new NextStepMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void throwDice(Connector connector) throws PumbaException
	{
		SocketMessage message = new ThrowDiceMessage();
		connector.setMessage(message);
		connector.run();
	}

	public void getPossiblePositions(Connector connector)
	{
		SocketMessage message = new GetPossiblePositionsMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void move(Connector connector, PositionReduced positionReduced)
	{
		SocketMessage message = new MoveMessage(positionReduced);
		connector.setMessage(message);
		connector.run();

	}

	public void applyCellEffect(Connector connector)
	{
		SocketMessage message = new ApplyCellEffectMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void getActivePlayerActions(Connector connector)
	{
		SocketMessage message = new GetActivePlayerActionsMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void playAction(Connector connector, String actionDescription)
	{
		SocketMessage message = new PlayActionMessage(actionDescription);
		connector.setMessage(message);
		connector.run();

	}

	public void finishTurn(Connector connector)
	{
		SocketMessage message = new FinishTurnMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void finishRound(Connector connector)
	{
		SocketMessage message = new FinishRoundMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void throwDice(Listener listener)
	{
		SocketMessage message = new FinishRoundMessage();
		listener.setMessage(message);
		listener.run();
		
	}

	public void move(Listener listener, PositionReduced positionReduced)
	{
		SocketMessage message = new MoveMessage(positionReduced);
		listener.setMessage(message);
		listener.run();
		
	}
}
