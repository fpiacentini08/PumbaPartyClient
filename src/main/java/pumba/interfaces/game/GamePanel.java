package pumba.interfaces.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import pumba.connector.Connector;
import pumba.controllers.GameController;
import pumba.interfaces.board.BoardPanel;
import pumba.interfaces.board.cells.PossiblePositionCell;
import pumba.interfaces.board.grid.GridPanel;
import pumba.interfaces.game.steps.ThrowDicePanel;
import pumba.interfaces.players.PlayerPanel;
import pumba.messages.GetPlayersMessage;
import pumba.messages.GetPossiblePositionsMessage;
import pumba.messages.MoveMessage;
import pumba.messages.NextStepMessage;
import pumba.messages.ThrowDiceMessage;
import pumba.models.board.cells.PositionReduced;
import pumba.models.game.StateReduced;
import pumba.models.game.StepEnum;
import pumba.models.players.PlayerReduced;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = -6825966139733003662L;
	private static final GameController gameController = new GameController();
	private static final Integer PLAYERS_LAYER = 1;
	JLayeredPane layeredPane = new JLayeredPane();
	JLayeredPane diceLayeredPane = new JLayeredPane();
	JTextArea logger = new JTextArea();

	List<PlayerReduced> players;

	private StateReduced actualState;

	public GamePanel(Connector connector)
	{
		drawBoard(connector);
		drawTitle();
		getPlayers(connector);
		drawScores();
		drawPlayers();
		drawLogger();
		layeredPane.setVisible(true);
		layeredPane.setSize(800, 600);
		nextStep(connector);
		add(layeredPane);
		setSize(800, 600);
		setLayout(null);
		setVisible(true);

	}

	private void drawLogger()
	{
		logger.setText("Bienvenido a Pumba Party.");
		logger.setBounds(582, 250, 200, 180);
		layeredPane.add(logger, JLayeredPane.POPUP_LAYER);

	}

	private void nextStep(Connector connector)
	{
		synchronized (this)
		{
			gameController.nextStep(connector);
			if (connector.getMessage().getApproved())
			{
				NextStepMessage message = (NextStepMessage) connector.getMessage();
				actualState = message.getActualState();
				processNextStep(connector);
			}
		}
	}

	private void processNextStep(Connector connector)
	{
		if (actualState.getActiveStep().equals(StepEnum.THROW_DICE.ordinal()))
		{
			drawThrowDice(connector);
		}
		else if (actualState.getActiveStep().equals(StepEnum.GET_POSSIBLE_POSITIONS.ordinal()))
		{
			getPossiblePositions(connector);
		}
		else if (actualState.getActiveStep().equals(StepEnum.CELL_EFFECT.ordinal()))
		{

		}
		else if (actualState.getActiveStep().equals(StepEnum.UPDATE_COINS.ordinal()))
		{

		}
		else if (actualState.getActiveStep().equals(StepEnum.SELECT_ACTION.ordinal()))
		{

		}
		else if (actualState.getActiveStep().equals(StepEnum.PLAY_ACTION.ordinal()))
		{

		}
		else if (actualState.getActiveStep().equals(StepEnum.WAIT.ordinal()))
		{

		}
	}

	private void getPossiblePositions(Connector connector)
	{
		synchronized (this)
		{
			gameController.getPossiblePositions(connector);
			if (connector.getMessage().getApproved())
			{
				JLayeredPane possiblePositionsLayer = new JLayeredPane();
				layeredPane.add(possiblePositionsLayer, JLayeredPane.POPUP_LAYER);
				possiblePositionsLayer.setVisible(true);
				possiblePositionsLayer.setSize(800, 600);

				GetPossiblePositionsMessage message = (GetPossiblePositionsMessage) connector.getMessage();
				for (PositionReduced pos : message.getPossiblePositions())
				{
					JPanel possiblePositionCell = new PossiblePositionCell(true, GridPanel.CELL_WIDTH);
					possiblePositionCell.setBounds(pos.getPosX() * GridPanel.CELL_WIDTH,
							pos.getPosY() * GridPanel.CELL_WIDTH, GridPanel.CELL_WIDTH, GridPanel.CELL_WIDTH);
					possiblePositionCell.setVisible(true);
					possiblePositionCell.addMouseListener(new MouseAdapter()
					{
						@Override
						public void mouseClicked(MouseEvent e)
						{
							move(connector, new PositionReduced(possiblePositionCell.getBounds()));
							possiblePositionsLayer.setVisible(false);

						}

					});

					possiblePositionsLayer.add(possiblePositionCell);
				}

			}
		}

	}

	private void move(Connector connector, PositionReduced positionReduced)
	{
		synchronized (this)
		{
			gameController.move(connector, positionReduced);
			if (connector.getMessage().getApproved())
			{
				MoveMessage message = (MoveMessage) connector.getMessage();
				if (message.getDestination() != null)
				{
					getPlayers(connector);
					drawPlayers();
					logger.setText(logger.getText() + "\n" + "Te has movido.\n");
				}
				else
				{
					getPossiblePositions(connector);
				}
			}
		}

	}

	private void drawThrowDice(Connector connector)
	{
		layeredPane.add(diceLayeredPane, JLayeredPane.POPUP_LAYER);
		diceLayeredPane.setBounds(582, 200, 30, 30);
		ActionListener taskPerformer = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				JPanel throwDice = new ThrowDicePanel();
				throwDice.setSize(30, 30);
				throwDice.setVisible(true);
				diceLayeredPane.add(throwDice, JLayeredPane.POPUP_LAYER);
			}
		};
		Timer timer = new Timer(200, taskPerformer);
		timer.setRepeats(true);
		timer.start();

		diceLayeredPane.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					diceLayeredPane.setVisible(false);
					throwDice(connector);

				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}

		});

		StringBuilder message = new StringBuilder("Es el turno de " + actualState.getActivePlayer().getUsername());
		message.append("\nHace click en el dado para tirar.");
		logger.setText(message.toString());
	}

	private void throwDice(Connector connector) throws InterruptedException
	{
		JPanel throwDice = null;
		JLayeredPane diceLayeredPane = new JLayeredPane();
		diceLayeredPane.setBounds(582, 200, 30, 30);
		layeredPane.add(diceLayeredPane, JLayeredPane.POPUP_LAYER);
		synchronized (this)
		{
			gameController.throwDice(connector);
			if (connector.getMessage().getApproved())
			{
				ThrowDiceMessage message = (ThrowDiceMessage) connector.getMessage();
				throwDice = new ThrowDicePanel(message.getDiceResult());
				throwDice.setSize(30, 30);
				throwDice.setVisible(true);
				diceLayeredPane.add(throwDice, JLayeredPane.POPUP_LAYER);
				logger.setText(logger.getText() + "\n" + "Salio un " + message.getDiceResult()
						+ ".\nHace click donde te quieras mover.");
				nextStep(connector);
			}
		}

	}

	private void drawPlayers()
	{
		JLayeredPane playersLayeredPane = new JLayeredPane();
		playersLayeredPane.setBounds(0, 0, 800, 600);
		if (layeredPane.getComponentsInLayer(PLAYERS_LAYER) != null)
		{
			layeredPane.remove(PLAYERS_LAYER);
		}
		layeredPane.add(playersLayeredPane, JLayeredPane.POPUP_LAYER, PLAYERS_LAYER);
		for (PlayerReduced player : players)
		{
			JPanel playerPanel = new PlayerPanel(player);
			playerPanel.setBounds(GridPanel.CELL_WIDTH * player.getPosition().getPosX(),
					GridPanel.CELL_WIDTH * player.getPosition().getPosY(), 30, 30);
			playerPanel.setSize(30, 30);
			playersLayeredPane.add(playerPanel, JLayeredPane.MODAL_LAYER);
			JLabel lblNewLabel = new JLabel(player.getUsername());
			lblNewLabel.setForeground(Color.YELLOW);
			lblNewLabel.setBounds(GridPanel.CELL_WIDTH * player.getPosition().getPosX(),
					GridPanel.CELL_WIDTH * player.getPosition().getPosY() + 20, 10, 30);
			lblNewLabel.setSize(30, 30);
			playersLayeredPane.add(lblNewLabel, JLayeredPane.POPUP_LAYER);
			playersLayeredPane.setVisible(true);
		}
		playersLayeredPane.repaint();
	}

	private void drawScores()
	{
		JPanel scoresPanel = new ScoresPanel(players);
		scoresPanel.setSize(200, 100);
		scoresPanel.setLocation(582, 70);
		scoresPanel.setVisible(true);
		scoresPanel.setLayout(null);
		layeredPane.add(scoresPanel, JLayeredPane.POPUP_LAYER);
	}

	private void getPlayers(Connector connector)
	{
		synchronized (this)
		{
			gameController.getPlayers(connector);
			if (connector.getMessage().getApproved())
			{
				GetPlayersMessage message = (GetPlayersMessage) connector.getMessage();
				players = message.getPlayers();
			}
		}
	}

	private void drawTitle()
	{
		JPanel title = new TitlePanel();
		title.setLocation(582, 6);
		title.setVisible(true);
		title.setSize(200, 50);
		layeredPane.add(title, JLayeredPane.POPUP_LAYER);

	}

	private void drawBoard(Connector connector)
	{
		synchronized (this)
		{
			JPanel boardPanel = new BoardPanel(connector);
			boardPanel.setVisible(true);
			boardPanel.setSize(GridPanel.CELL_WIDTH * 12, GridPanel.CELL_WIDTH * 12);
			layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
		}
	}
}
