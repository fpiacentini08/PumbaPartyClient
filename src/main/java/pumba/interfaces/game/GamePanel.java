
package pumba.interfaces.game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import pumba.connector.Connector;
import pumba.controllers.GameController;
import pumba.interfaces.board.BoardPanel;
import pumba.interfaces.board.cells.PossiblePositionCell;
import pumba.interfaces.board.grid.GridPanel;
import pumba.interfaces.game.steps.ThrowDicePanel;
import pumba.interfaces.players.PlayerPanel;
import pumba.messages.ApplyCellEffectMessage;
import pumba.messages.GetActivePlayerActionsMessage;
import pumba.messages.GetPlayersMessage;
import pumba.messages.GetPossiblePositionsMessage;
import pumba.messages.MoveMessage;
import pumba.messages.NextStepMessage;
import pumba.messages.PlayActionMessage;
import pumba.messages.ThrowDiceMessage;
import pumba.minigame.MinigameSelector;
import pumba.models.actions.ActionReduced;
import pumba.models.board.cells.PositionReduced;
import pumba.models.game.StateReduced;
import pumba.models.game.StepEnum;
import pumba.models.players.PlayerReduced;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = -6825966139733003662L;
	private static final GameController gameController = new GameController();
	
	public static final Integer delay = 1200;
	
	private static final Integer BOARD_LAYER = 1;
	private static final Integer PLAYERS_LAYER = 2;
	private static final Integer DICE_LAYER = 3;
	private static final Integer SCORES_LAYER = 4;
	private static final Integer TITTLE_LAYER = 5;
	private static final Integer LOGGER_LAYER = 6;
	private static final Integer ACTIONS_LAYER = 7;

	
	JLayeredPane mainLayeredPane = new JLayeredPane();
	JLayeredPane diceLayeredPane = new JLayeredPane();
	JLayeredPane scoresLayer = new JLayeredPane();
	JLayeredPane loggerLayer = new JLayeredPane();
	JLayeredPane playersLayeredPane = new JLayeredPane();
	JLayeredPane actionsLayer = new JLayeredPane();

	JTextPane logger = new JTextPane();
	JLabel lblRound = new JLabel();
	List<PlayerReduced> players;

	private StateReduced actualState;

	public GamePanel(Connector connector)
	{
		mainLayeredPane.setVisible(true);
		mainLayeredPane.setSize(800, 600);
		add(mainLayeredPane);
		drawBoard(connector);
		drawTitle();
		getPlayers(connector);
		drawPlayers();
		drawScores();
		drawLogger();
		nextStep(connector);
		setSize(800, 600);
		setLayout(null);
		setVisible(true);

	}

	private void drawLogger()
	{
		loggerLayer.setBounds(0, 0, 800, 600);
		loggerLayer.setVisible(true);
		mainLayeredPane.add(loggerLayer, LOGGER_LAYER);
		logger.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		writeLogger("Bienvenido a Pumba Party.");
		logger.setBounds(582, 250, 200, 180);
		loggerLayer.add(logger, JLayeredPane.POPUP_LAYER);

	}

	private void writeLogger(String message)
	{
		String text = logger.getText();
		String[] lines = text.split("\n");
		StringBuilder scrolledText = new StringBuilder("");
		int j = 0;
		if (lines.length > 8)
		{
			j = 2;
		}
		for (int i = j; i < lines.length; i++)
		{
			if (!StringUtils.isBlank(lines[i]))
			{
				scrolledText.append(lines[i] + "\n");
			}
		}

		logger.setText(null);

		StyledDocument doc = logger.getStyledDocument();

		Style styleSimple = logger.addStyle("", null);
		StyleConstants.setForeground(styleSimple, Color.black);
		StyleConstants.setBackground(styleSimple, Color.white);

		try
		{
			doc.insertString(doc.getLength(), scrolledText.toString(), styleSimple);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}

		Style style = logger.addStyle("", null);
		StyleConstants.setForeground(style, Color.black);
		StyleConstants.setBackground(style, Color.white);
		StyleConstants.setUnderline(style, true);
		try
		{
			doc.insertString(doc.getLength(), message, style);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}

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
				updateRound(actualState.getActiveRound());
				processNextStep(connector);
			}
		}
	}

	private void updateRound(Integer activeRound)
	{
		lblRound.setText(RegExUtils.replacePattern(lblRound.getText(), "[0-9]", activeRound.toString()));
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
			applyCellEffect(connector);
		}
		else if (actualState.getActiveStep().equals(StepEnum.SELECT_ACTION.ordinal()))
		{
			getActivePlayerActions(connector);
		}
		else if (actualState.getActiveStep().equals(StepEnum.WAIT.ordinal()))
		{
			writeLogger("------------");
			mainLayeredPane.remove(actionsLayer);

			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					finishTurn(connector);
				}
			};

			Timer timer = new Timer(delay, taskPerformer);
			timer.setRepeats(false);
			timer.start();
		}
		else if (actualState.getActiveStep().equals(StepEnum.MINIGAME.ordinal()))
		{
			playMinigame(connector);
			finishRound(connector);
		}
		else if (actualState.getActiveStep().equals(StepEnum.END.ordinal()))
		{
			writeLogger("TERMINO EL JUEGO");
			writeLogger("Gano " + this.players.get(0).getUsername());
		}
	}

	private void playMinigame(Connector connector)
	{
		JPanel minigamePanel = MinigameSelector.randomMinigame(connector, players);
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		minigamePanel.setVisible(true);
		frame.setContentPane(minigamePanel);
		frame.revalidate();
	}

	private void finishRound(Connector connector)
	{
		synchronized (this)
		{
			gameController.finishRound(connector);
			if (connector.getMessage().getApproved())
			{
				nextStep(connector);
			}
		}
	}

	private void finishTurn(Connector connector)
	{
		synchronized (this)
		{
			gameController.finishTurn(connector);
			logger.setText(null);
			mainLayeredPane.remove(actionsLayer);
			if (connector.getMessage().getApproved())
			{
				nextStep(connector);
			}
		}
	}

	private void getActivePlayerActions(Connector connector)
	{
		synchronized (this)
		{
			gameController.getActivePlayerActions(connector);
			if (connector.getMessage().getApproved())
			{
				GetActivePlayerActionsMessage message = (GetActivePlayerActionsMessage) connector.getMessage();
				drawActions(connector, message.getActions());
			}
		}

	}

	private void drawActions(Connector connector, List<ActionReduced> actions)
	{
		final Integer ACTION_BUTTON_HEIGHT = 40;
		writeLogger("Hace click en una accion abajo.");
		actionsLayer = new JLayeredPane();
		actionsLayer.setBounds(0, 0, 800, 600);
		mainLayeredPane.add(actionsLayer, ACTIONS_LAYER);
		int i = 0;
		for (ActionReduced action : actions)
		{
			final JButton actionButton = new JButton();
			actionButton.setBounds(582, 440 + ACTION_BUTTON_HEIGHT * i++, 200, ACTION_BUTTON_HEIGHT);
			actionButton.setText(action.getActionDescription());
			actionButton.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					executeAction(connector, action.getActionDescription());
					mainLayeredPane.remove(actionsLayer);
				}
			});

			if (!action.getAvailable())
			{
				actionButton.setEnabled(false);
			}
			else
			{
				actionButton.setEnabled(true);

			}
			actionsLayer.add(actionButton);
		}
	}

	private void executeAction(Connector connector, String actionDescription)
	{
		synchronized (this)
		{
			gameController.playAction(connector, actionDescription);
			if (connector.getMessage().getApproved())
			{
				PlayActionMessage message = (PlayActionMessage) connector.getMessage();
				writeLogger("Has decidido " + message.getActionDescription().toLowerCase());
				writeLogger(message.getResultDescription());
				players = message.getPlayers();
				drawScores();
				nextStep(connector);
			}
		}
	}

	private void applyCellEffect(Connector connector)
	{
		synchronized (this)
		{
			gameController.applyCellEffect(connector);
			if (connector.getMessage().getApproved())
			{
				ApplyCellEffectMessage message = (ApplyCellEffectMessage) connector.getMessage();
				writeLogger(message.getEffectDescription());
				players = message.getPlayers();
				drawScores();
				nextStep(connector);
			}
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
				mainLayeredPane.add(possiblePositionsLayer, JLayeredPane.POPUP_LAYER);
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
					mainLayeredPane.remove(diceLayeredPane);
					getPlayers(connector);
					drawPlayers();
					writeLogger("Te has movido.");
					nextStep(connector);
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
		mainLayeredPane.add(diceLayeredPane, JLayeredPane.POPUP_LAYER, DICE_LAYER);
		diceLayeredPane.setBounds(ThrowDicePanel.DICE_POS_X, ThrowDicePanel.DICE_POS_Y, ThrowDicePanel.DICE_SIZE, ThrowDicePanel.DICE_SIZE);
		ActionListener taskPerformer = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				JPanel throwDice = new ThrowDicePanel();
				throwDice.setSize(ThrowDicePanel.DICE_SIZE, ThrowDicePanel.DICE_SIZE);
				throwDice.setVisible(true);
				diceLayeredPane.add(throwDice, JLayeredPane.POPUP_LAYER);
			}
		};
		Timer timer = new Timer(1000 / 30, taskPerformer);
		timer.setRepeats(true);
		timer.start();

		diceLayeredPane.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					timer.stop();
					throwDice(connector);

				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}

		});

		writeLogger("Es el turno de " + actualState.getActivePlayer().getUsername());
		writeLogger("Hace click en el dado para tirar.");
	}

	private void throwDice(Connector connector) throws InterruptedException
	{
		JPanel throwDice = null;
		mainLayeredPane.remove(diceLayeredPane);
		diceLayeredPane = new JLayeredPane();
		diceLayeredPane.setBounds(ThrowDicePanel.DICE_POS_X, ThrowDicePanel.DICE_POS_Y, ThrowDicePanel.DICE_SIZE, ThrowDicePanel.DICE_SIZE);
		mainLayeredPane.add(diceLayeredPane, DICE_LAYER);
		synchronized (this)
		{
			gameController.throwDice(connector);
			if (connector.getMessage().getApproved())
			{
				ThrowDiceMessage message = (ThrowDiceMessage) connector.getMessage();
				throwDice = new ThrowDicePanel(message.getDiceResult());
				throwDice.setSize(ThrowDicePanel.DICE_SIZE, ThrowDicePanel.DICE_SIZE);
				throwDice.setVisible(true);
				diceLayeredPane.add(throwDice, JLayeredPane.POPUP_LAYER);
				writeLogger("Salio un " + message.getDiceResult() + ".");
				writeLogger("Hace click donde te quieras mover.");
				nextStep(connector);
			}
		}

	}

	private void drawPlayers()
	{
		mainLayeredPane.remove(playersLayeredPane);
		playersLayeredPane = new JLayeredPane();
		playersLayeredPane.setBounds(0, 0, 800, 600);
		mainLayeredPane.add(playersLayeredPane, JLayeredPane.POPUP_LAYER, PLAYERS_LAYER);
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
					GridPanel.CELL_WIDTH * player.getPosition().getPosY() + 20, 100, 30);
			playersLayeredPane.add(lblNewLabel, JLayeredPane.POPUP_LAYER);
			playersLayeredPane.setVisible(true);
		}
		playersLayeredPane.repaint();
	}

	private void drawScores()
	{
		mainLayeredPane.remove(scoresLayer);
		scoresLayer = new JLayeredPane();
		scoresLayer.setBounds(0, 0, 800, 600);
		scoresLayer.setVisible(true);
		JPanel scoresPanel = new ScoresPanel(players);
		scoresPanel.setSize(200, 100);
		scoresPanel.setLocation(582, 70);
		scoresPanel.setVisible(true);
		scoresPanel.setLayout(null);
		scoresLayer.add(scoresPanel, JLayeredPane.POPUP_LAYER);
		mainLayeredPane.add(scoresLayer, SCORES_LAYER);

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
		JLayeredPane titleLayer = new JLayeredPane();
		titleLayer.setBounds(0, 0, 800, 600);
		titleLayer.setVisible(true);
		mainLayeredPane.add(titleLayer, TITTLE_LAYER);

		JPanel title = new TitlePanel();
		title.setLocation(582, 6);
		title.setVisible(true);
		title.setSize(200, 50);
		titleLayer.add(title, JLayeredPane.POPUP_LAYER);

		lblRound = new JLabel("Ronda: 0");
		lblRound.setBounds(420, 5, 200, 20);
		lblRound.setFont(new Font("Courier", Font.BOLD, 18));
		lblRound.setBackground(Color.BLACK);
		titleLayer.add(lblRound, JLayeredPane.POPUP_LAYER);

	}

	private void drawBoard(Connector connector)
	{
		synchronized (this)
		{
			JLayeredPane boardLayer = new JLayeredPane();
			boardLayer.setBounds(0, 0, 800, 600);
			boardLayer.setVisible(true);
			mainLayeredPane.add(boardLayer, BOARD_LAYER);
			JPanel boardPanel = new BoardPanel(connector);
			boardPanel.setVisible(true);
			boardPanel.setSize(GridPanel.CELL_WIDTH * 12, GridPanel.CELL_WIDTH * 12);
			boardLayer.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
		}
	}
}
