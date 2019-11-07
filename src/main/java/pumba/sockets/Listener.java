package pumba.sockets;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import pumba.exceptions.PumbaException;

public class Listener extends PumbaSocket
{

	private SwingWorker<Listener, Void> associatedWorker;

	public Listener(Connector connector) throws PumbaException
	{
		super(connector);

	}

	public Listener() throws PumbaException
	{
		super();
	}

	public void listenToServer()
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
			if (associatedWorker != null && !associatedWorker.isDone() && !associatedWorker.isCancelled())
			{
				System.out.println("Cancelo el worker");
				associatedWorker.cancel(true);
			}
			System.exit(1);
		}
		catch (PumbaException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void run()
	{
		synchronized (this)
		{
			listenToServer();
		}
	}

	public SwingWorker<Listener, Void> getAssociatedWorker()
	{
		return associatedWorker;
	}

	public void setAssociatedWorker(SwingWorker<Listener, Void> associatedWorker)
	{
		this.associatedWorker = associatedWorker;
	}

}
