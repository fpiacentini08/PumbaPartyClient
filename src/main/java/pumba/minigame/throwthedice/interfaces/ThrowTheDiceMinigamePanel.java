package pumba.minigame.throwthedice.interfaces;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.commons.lang3.StringUtils;

import pumba.exceptions.PumbaException;
import pumba.interfaces.game.GamePanel;
import pumba.interfaces.game.steps.ThrowDicePanel;
import pumba.messages.ThrowTheDiceMinigameFinishTurnMessage;
import pumba.messages.ThrowTheDiceMinigameGetPlayers;
import pumba.messages.ThrowTheDiceMinigameNextStepMessage;
import pumba.messages.ThrowTheDiceMinigameStart;
import pumba.messages.ThrowTheDiceMinigameThrowDiceMessage;
import pumba.messages.utils.SocketMessage;
import pumba.minigame.throwthedice.controllers.ThrowTheDiceController;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameStateEnum;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameStateReduced;
import pumba.sockets.Connector;
import pumba.sockets.Listener;

public class ThrowTheDiceMinigamePanel extends JPanel
{
	private static final long serialVersionUID = -6825966139733003662L;
	private static final ThrowTheDiceController minigameController = new ThrowTheDiceController();

	private static final Integer BACKGROUND_LAYER = 2;
	private static final Integer DICE_LAYER = 3;
	private static final Integer SCORES_LAYER = 4;
	private static final Integer TITTLE_LAYER = 5;
	private static final Integer LOGGER_LAYER = 6;

	JLayeredPane mainLayeredPane = new JLayeredPane();
	JLayeredPane backgroundLayer = new JLayeredPane();
	JLayeredPane diceLayeredPane = new JLayeredPane();
	JLayeredPane scoresLayer = new JLayeredPane();
	JLayeredPane loggerLayer = new JLayeredPane();

	JTextPane logger = new JTextPane();

	Map<String, Integer> players = new HashMap<>();;

	private ThrowTheDiceMinigameStateReduced actualState;

	private GamePanel gamePanel;
	
	public ThrowTheDiceMinigamePanel(Connector connector, Listener listener, List<String> playersNames, GamePanel gamePanel)
			throws PumbaException
	{
		this.gamePanel = gamePanel;
		mainLayeredPane.setVisible(true);
		mainLayeredPane.setSize(800, 600);
		add(mainLayeredPane);
		drawBackground();
		drawTitle();
		startMinigame(connector, playersNames);
		drawScores();
		drawLogger();
		nextStep(connector, listener);
		setSize(800, 600);
		setLayout(null);
		setVisible(true);

	}

	private void drawBackground()
	{
		backgroundLayer.setBounds(0, 0, 800, 600);
		mainLayeredPane.add(backgroundLayer, BACKGROUND_LAYER);
		JPanel background = new ThrowTheDiceMinigameBackgroundPanel();
		background.setBounds(0, 0, 580, 600);
		backgroundLayer.add(background);
	}

	private void startMinigame(Connector connector, List<String> playersNames)
	{
		synchronized (this)
		{
			minigameController.start(connector, playersNames);
			if (connector.getMessage().getApproved())
			{
				ThrowTheDiceMinigameStart message = (ThrowTheDiceMinigameStart) connector.getMessage();
				this.players = message.getPlayers();
			}
		}
	}

	private void drawLogger()
	{
		loggerLayer.setBounds(0, 0, 800, 600);
		loggerLayer.setVisible(true);
		mainLayeredPane.add(loggerLayer, LOGGER_LAYER);
		logger.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		writeLogger("Minijuego: los dados de Pumba");
		writeLogger("Los jugadores tiraran el dado de a uno.");
		writeLogger("El jugador obtendra 100 bichos ");
		writeLogger("multiplicado por el valor del dado.");
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

	private void nextStep(Connector connector, Listener listener) throws PumbaException
	{
		synchronized (this)
		{
			minigameController.nextStep(connector);
			if (connector.getMessage().getApproved())
			{
				ThrowTheDiceMinigameNextStepMessage message = (ThrowTheDiceMinigameNextStepMessage) connector
						.getMessage();
				actualState = message.getActualState();
				processNextStep(connector, listener);
			}
		}
	}

	private void processNextStep(Connector connector, Listener listener) throws PumbaException
	{
		System.out.println("Paso por aca!");
		if (actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.THROW_DICE))
		{
			SwingUtilities.invokeLater(new Runnable()
			{

				@Override
				public void run()
				{
					drawThrowDice(connector, listener);

				}
			});

		}
		if (actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.WAIT))
		{

			SwingUtilities.invokeLater(new Runnable()
			{

				@Override
				public void run()
				{
					processWait(connector, listener);

				}
			});
		}
		if (actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.END))
		{
			writeLogger("El minijuego ha terminado.");
			writeLogger("Llevense sus bichos.");
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						endGame(connector, listener);
					}
					catch (PumbaException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

	}

	private void processWait(Connector connector, Listener listener)
	{
		ActionListener taskPerformer = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				try
				{
					finishTurn(connector, listener);
				}
				catch (PumbaException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};

		if (itIsMyTurn())
		{
			Timer timer = new Timer(GamePanel.delay, taskPerformer);
			timer.setRepeats(false);
			timer.start();
		}
		else
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						finishTurn(connector, listener);
					}
					catch (PumbaException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		}

	}

	private void endGame(Connector connector, Listener listener) throws PumbaException
	{
		JPanel gamePanel = new GamePanel(connector, listener);
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		gamePanel.setVisible(true);
		frame.setContentPane(gamePanel);
		frame.revalidate();
	}

	private void finishTurn(Connector connector, Listener listener) throws PumbaException
	{
		synchronized (this)
		{
			ThrowTheDiceMinigameFinishTurnMessage message;
			if (itIsMyTurn())
			{
				minigameController.finishTurn(connector);
				message = (ThrowTheDiceMinigameFinishTurnMessage) connector.getMessage();
			}
			else
			{
				minigameController.finishTurn(listener);
				message = (ThrowTheDiceMinigameFinishTurnMessage) listener.getMessage();

			}
			logger.setText(null);
			if (message.getApproved())
			{
				nextStep(connector, listener);
			}
		}
	}

	private void drawThrowDice(Connector connector, Listener listener)
	{
		if (itIsMyTurn())
		{
			System.out.println("Es mi turno y dibujo el dado");

			mainLayeredPane.add(diceLayeredPane, JLayeredPane.POPUP_LAYER, DICE_LAYER);
			diceLayeredPane.setBounds(ThrowDicePanel.DICE_POS_X, ThrowDicePanel.DICE_POS_Y, ThrowDicePanel.DICE_SIZE,
					ThrowDicePanel.DICE_SIZE);
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
			System.out.println("Activo el timer");

			diceLayeredPane.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					try
					{
						timer.stop();
						System.out.println("Ejecuto throwDice y es mi turno");
						throwDice(connector, listener);

					}
					catch (InterruptedException | PumbaException e1)
					{
						e1.printStackTrace();
					}
				}

			});
		}
		else
		{
			System.out.println("No es mi turno, SWING haz lo tuyo");

			try
			{
				System.out.println("Ejecuto throwDice y no es mi turno");
				throwDice(connector, listener);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (PumbaException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		writeLogger("Es el turno de " + actualState.getActivePlayer());
		writeLogger("Hace click en el dado para tirar.");
		System.out.println("Termino la rutina drawThrowDice");

	}

	private void throwDice(Connector connector, Listener listener) throws InterruptedException, PumbaException
	{
		System.out.println("Entro a la rutina throwDice");

		JPanel throwDice = null;
		mainLayeredPane.remove(diceLayeredPane);
		diceLayeredPane = new JLayeredPane();
		diceLayeredPane.setBounds(ThrowDicePanel.DICE_POS_X, ThrowDicePanel.DICE_POS_Y, ThrowDicePanel.DICE_SIZE,
				ThrowDicePanel.DICE_SIZE);
		mainLayeredPane.add(diceLayeredPane, JLayeredPane.DEFAULT_LAYER, DICE_LAYER);
		ThrowTheDiceMinigameThrowDiceMessage message;

		synchronized (this)
		{
			System.out.println("Estoy por tirar el dado");
			if (itIsMyTurn())
			{
				minigameController.throwDice(connector);
				message = (ThrowTheDiceMinigameThrowDiceMessage) connector.getMessage();
				System.out.println("Tire el dado");
			}
			else
			{
				minigameController.throwDice(listener);
				message = (ThrowTheDiceMinigameThrowDiceMessage) listener.getMessage();
				System.out.println("Tire el dado");
			}

		}
		if (message.getApproved())
		{
			throwDice = new ThrowDicePanel(message.getResult().getDiceResult());
			throwDice.setSize(ThrowDicePanel.DICE_SIZE, ThrowDicePanel.DICE_SIZE);
			throwDice.setVisible(true);
			diceLayeredPane.add(throwDice, JLayeredPane.POPUP_LAYER);
			writeLogger(message.getResult().getDescription());
			getPlayers(connector);
			drawScores();
			nextStep(connector, listener);
		}
	}

	private void drawScores()
	{
		mainLayeredPane.remove(scoresLayer);
		scoresLayer = new JLayeredPane();
		scoresLayer.setBounds(0, 0, 800, 600);
		scoresLayer.setVisible(true);
		JPanel scoresPanel = new ThrowTheDiceMinigameScoresPanel(players);
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
			try
			{
				minigameController.getPlayers(connector);
			}
			catch (PumbaException e)
			{
				e.printStackTrace();
			}
			if (connector.getMessage().getApproved())
			{
				ThrowTheDiceMinigameGetPlayers message = (ThrowTheDiceMinigameGetPlayers) connector.getMessage();
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

		JPanel title = new ThrowTheDiceMinigameTitlePanel();
		title.setLocation(582, 6);
		title.setVisible(true);
		title.setSize(200, 50);
		titleLayer.add(title, JLayeredPane.POPUP_LAYER);

	}

	private Boolean itIsMyTurn()
	{
		if (actualState != null && actualState.getActivePlayer() != null)
		{
			return actualState.getActivePlayer().equals(SocketMessage.getClientId());
		}
		return true;
	}
}
