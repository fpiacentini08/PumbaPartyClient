package pumba.sockets;

import java.io.IOException;

import javax.swing.JOptionPane;

import pumba.exceptions.PumbaException;

public class Listener extends PumbaSocket
{

	public Listener(Connector connector) throws PumbaException
	{
		super(connector);

	}

	@Override
	public void run()
	{
		try
		{
			synchronized (this)
			{
				this.message = this.receiveMessage();
				this.message.processResponse(this);
			}
		}

		catch (IOException | ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
			System.exit(1);
		}
		catch (PumbaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}
