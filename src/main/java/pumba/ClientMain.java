package pumba;

import java.awt.EventQueue;

import pumba.exceptions.PumbaException;
import pumba.interfaces.game.GameFrame;

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
					GameFrame frame = new GameFrame(username);
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
