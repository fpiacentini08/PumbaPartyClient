package pumba.minigame;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import pumba.minigame.throwthedice.interfaces.ThrowTheDiceMinigamePanel;
import pumba.models.players.PlayerReduced;
import pumba.sockets.Connector;

public class MinigameSelector
{
	private static final Random rand = new Random();

	public static JPanel randomMinigame(Connector connector, List<PlayerReduced> players)
	{
		List<String> playersNames = players.stream().map(p -> p.getUsername()).collect(Collectors.toList());

		JPanel minigamePanel;
		switch (rand.nextInt(1))
		{
			default:
				minigamePanel = new ThrowTheDiceMinigamePanel(connector, playersNames);
		}
		return minigamePanel;
	}
}
