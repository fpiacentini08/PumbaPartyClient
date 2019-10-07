package pumba;

import java.awt.EventQueue;

import pumba.exceptions.PumbaException;
import pumba.interfaces.login.LoginFrame;

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
					LoginFrame frame = new LoginFrame();
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
