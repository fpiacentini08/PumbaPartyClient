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

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.interfaces.game.GamePanel;
import pumba.interfaces.game.steps.ThrowDicePanel;
import pumba.messages.ThrowTheDiceMinigameGetPlayers;
import pumba.messages.ThrowTheDiceMinigameNextStepMessage;
import pumba.messages.ThrowTheDiceMinigameStart;
import pumba.messages.ThrowTheDiceMinigameThrowDiceMessage;
import pumba.minigame.throwthedice.controllers.ThrowTheDiceController;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameStateEnum;
import pumba.minigame.throwthedice.models.ThrowTheDiceMinigameStateReduced;

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

	public ThrowTheDiceMinigamePanel(Connector connector, List<String> playersNames)
	{
		mainLayeredPane.setVisible(true);
		mainLayeredPane.setSize(800, 600);
		add(mainLayeredPane);
		drawBackground();
		drawTitle();
		startMinigame(connector, playersNames);
		drawScores();
		drawLogger();
		nextStep(connector);
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

	private void nextStep(Connector connector)
	{
		synchronized (this)
		{
			minigameController.nextStep(connector);
			if (connector.getMessage().getApproved())
			{
				ThrowTheDiceMinigameNextStepMessage message = (ThrowTheDiceMinigameNextStepMessage) connector
						.getMessage();
				actualState = message.getActualState();
				processNextStep(connector);
			}
		}
	}

	private void processNextStep(Connector connector)
	{
		if (actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.THROW_DICE.ordinal()))
		{
			drawThrowDice(connector);
		}
		if (actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.WAIT.ordinal()))
		{
			writeLogger("------------");
			finishTurn(connector);
		}
		if (actualState.getActiveStep().equals(ThrowTheDiceMinigameStateEnum.END.ordinal()))
		{
			writeLogger("El minijuego ha terminado.");
			writeLogger("Llevense sus bichos.");
			endGame(connector);
		}
	}

	private void endGame(Connector connector)
	{
		JPanel gamePanel = new GamePanel(connector);
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		gamePanel.setVisible(true);
		frame.setContentPane(gamePanel);
		frame.revalidate();
	}

	private void finishTurn(Connector connector)
	{
		synchronized (this)
		{
			minigameController.finishTurn(connector);
			if (connector.getMessage().getApproved())
			{
				nextStep(connector);
			}
		}
	}

	private void drawThrowDice(Connector connector)
	{
		mainLayeredPane.add(diceLayeredPane, JLayeredPane.POPUP_LAYER, DICE_LAYER);
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

		writeLogger("Es el turno de " + actualState.getActivePlayer());
		writeLogger("Hace click en el dado para tirar.");
	}

	private void throwDice(Connector connector) throws InterruptedException
	{
		JPanel throwDice = null;
		mainLayeredPane.remove(diceLayeredPane);
		diceLayeredPane = new JLayeredPane();
		diceLayeredPane.setBounds(582, 200, 30, 30);
		mainLayeredPane.add(diceLayeredPane, JLayeredPane.DEFAULT_LAYER, DICE_LAYER);
		synchronized (this)
		{
			minigameController.throwDice(connector);
			if (connector.getMessage().getApproved())
			{
				ThrowTheDiceMinigameThrowDiceMessage message = (ThrowTheDiceMinigameThrowDiceMessage) connector
						.getMessage();
				throwDice = new ThrowDicePanel(message.getResult().getDiceResult());
				throwDice.setSize(30, 30);
				throwDice.setVisible(true);
				diceLayeredPane.add(throwDice, JLayeredPane.POPUP_LAYER);
				writeLogger(message.getResult().getDescription());
				getPlayers(connector);
				drawScores();
				nextStep(connector);
			}
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

}
