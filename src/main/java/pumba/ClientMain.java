package pumba;

import java.awt.EventQueue;

import pumba.exceptions.PumbaException;
import pumba.interfaces.game.GameFrame;
import pumba.sockets.Connector;
import pumba.sockets.Listener;

public class ClientMain
{

	public static void main(String[] args) throws PumbaException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					String username = args[0];
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
