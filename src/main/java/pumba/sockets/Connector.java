package pumba.sockets;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

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

			catch (IOException | PumbaException | JsonSyntaxException | ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
				e.printStackTrace();
				System.exit(1);
				
			}

		}
	}

}
