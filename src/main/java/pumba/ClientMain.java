package pumba;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import pumba.exceptions.PumbaException;
import pumba.interfaces.game.GameFrame;
import pumba.sockets.Connector;
import pumba.sockets.Listener;

public class ClientMain
{

	private static String username = StringUtils.EMPTY;

	public static void main(String[] args) throws PumbaException
	{

		while (username != null && StringUtils.isBlank(username))
		{
			username = JOptionPane
					.showInputDialog("Bienvenido a Pumba Party\n" + "Es un juego de tablero por turnos con 3 rondas.\n"
							+ "El ganador ser� qui�n tenga m�s bichos al final del juego.\n"
							+ "En cada ronda, los jugadores tendr�n un turno donde \n"
							+ "tirar�n un dado, se mover�n por el tablero y recibir�n \n"
							+ "premios y castigos. Tambi�n podr�n realizar acciones.\n"
							+ "Por favor, ingrese su nombre como jugador.");
		}

		if (username == null)
		{
			System.exit(1);
		}

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					// LoginFrame frame = new LoginFrame();
					Connector connector = new Connector();
					Listener listener = new Listener(connector);
					Connector allTimeConnector = new Connector();
					Listener allTimeListener = new Listener(allTimeConnector);
					GameFrame frame = new GameFrame(username, connector, listener, allTimeConnector, allTimeListener);
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

}
