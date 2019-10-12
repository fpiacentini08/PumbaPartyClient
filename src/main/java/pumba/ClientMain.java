package pumba;

import java.awt.EventQueue;

import pumba.exceptions.PumbaException;
import pumba.interfaces.board.BoardFrame;

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
//					LoginFrame frame = new LoginFrame();
					BoardFrame frame = new BoardFrame();
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
