package pumba.sockets;

import java.io.IOException;

import javax.swing.JOptionPane;

import pumba.exceptions.PumbaException;

public class Connector extends PumbaSocket
{

	public Connector() throws PumbaException
	{
		super();
	}

	public void run()
	{
		synchronized (this)
		{
			try
			{
				this.sendMessage(this.message);
				this.message = this.receiveMessage();
				this.message.processResponse(this);
			}

			catch (IOException | ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
				System.exit(1);
				e.printStackTrace();
			}
			catch (PumbaException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

}