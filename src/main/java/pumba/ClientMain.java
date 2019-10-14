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
					// LoginFrame frame = new LoginFrame();
					GameFrame frame = new GameFrame();
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
