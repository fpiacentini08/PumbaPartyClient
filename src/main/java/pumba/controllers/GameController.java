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

	public void getPlayers(Connector connector, Boolean itIsMyTurn) throws PumbaException
	{
		SocketMessage message = new GetPlayersMessage();
		connector.setMessage(message);
		if (itIsMyTurn)
		{
			connector.run();
		}
		else
		{
			Listener listener = new Listener();
			listener.setMessage(message);
			listener.run();
		}

	}

	public void nextStep(Connector connector, Boolean itIsMyTurn)
	{
		SocketMessage message = new NextStepMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void throwDice(Connector connector, Boolean itIsMyTurn)
	{
		SocketMessage message = new ThrowDiceMessage();
		connector.setMessage(message);
		connector.run();
	}

	public void getPossiblePositions(Connector connector, Boolean itIsMyTurn)
	{
		SocketMessage message = new GetPossiblePositionsMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void move(Connector connector, PositionReduced positionReduced, Boolean itIsMyTurn)
	{
		SocketMessage message = new MoveMessage(positionReduced);
		connector.setMessage(message);
		connector.run();

	}

	public void applyCellEffect(Connector connector, Boolean itIsMyTurn)
	{
		SocketMessage message = new ApplyCellEffectMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void getActivePlayerActions(Connector connector, Boolean itIsMyTurn)
	{
		SocketMessage message = new GetActivePlayerActionsMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void playAction(Connector connector, String actionDescription, Boolean itIsMyTurn)
	{
		SocketMessage message = new PlayActionMessage(actionDescription);
		connector.setMessage(message);
		connector.run();

	}

	public void finishTurn(Connector connector, Boolean itIsMyTurn)
	{
		SocketMessage message = new FinishTurnMessage();
		connector.setMessage(message);
		connector.run();

	}

	public void finishRound(Connector connector, Boolean itIsMyTurn)
	{
		SocketMessage message = new FinishRoundMessage();
		connector.setMessage(message);
		connector.run();

	}
}
