package pumba.connector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import pumba.exceptions.ErrorCodes;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.log.Log;
import pumba.messages.utils.SocketMessage;
import pumba.messages.utils.SocketMessageSerializer;

public class Connector extends Thread
{

	private Socket socket;
	private String localIp;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private SocketMessage message;

	private Gson gson;

	public String getLocalIp()
	{
		return localIp;
	}

	public void setLocalIp(String localIp)
	{
		this.localIp = localIp;
	}

	public ObjectInputStream getIn()
	{
		return in;
	}

	public void setIn(ObjectInputStream in)
	{
		this.in = in;
	}

	public ObjectOutputStream getOut()
	{
		return out;
	}

	public void setOut(ObjectOutputStream out)
	{
		this.out = out;
	}

	public SocketMessage getMessage()
	{
		return message;
	}

	public void setMessage(SocketMessage message)
	{
		this.message = message;
	}

	public Connector() throws PumbaException
	{
		try
		{
			socket = new Socket("localhost", 9999);
		}
		catch (IOException e)
		{
			Log.debug(ErrorMessages.BAD_CONNECTION);
			throw new PumbaException(ErrorMessages.BAD_CONNECTION, ErrorCodes.BAD_CONNECTION);
		}
		localIp = socket.getInetAddress().getHostAddress();

		try
		{
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			Log.debug("Error estableciendo conexion de entrada.");
			throw new PumbaException(ErrorMessages.BAD_CONNECTION_IN, ErrorCodes.BAD_CONNECTION_IN);
		}
		try
		{
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e)
		{
			Log.debug("Error estableciendo conexion de salida.");
			throw new PumbaException(ErrorMessages.BAD_CONNECTION_OUT, ErrorCodes.BAD_CONNECTION_OUT);
		}
		GsonBuilder gsonBilder = new GsonBuilder().registerTypeAdapter(SocketMessage.class, new SocketMessageSerializer());
		this.gson = gsonBilder.create();


	}

	public void run()
	{
		synchronized (this)
		{
			try
			{

				// Le envio el comando al servidor
				this.sendMessage(this.message);

				// Recibo el comando desde el servidor
				this.message = this.receiveMessage();

				// Resuelvo el comando recibido
				this.message.processResponse(this);
				// this.notifyAll();
			}

			catch (IOException | ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
				System.exit(1);
				e.printStackTrace();
			}
			catch(PumbaException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	private SocketMessage receiveMessage() throws JsonSyntaxException, ClassNotFoundException, IOException
	{
		SocketMessage message = gson.fromJson((String) this.in.readObject(), SocketMessage.class);
		Log.debugLine();
		Log.debug("INBOUND MESSAGE");
		Log.debug(message.toString());
		Log.debugLine();
		return message;

	}

	public void sendMessage(SocketMessage message) throws IOException
	{
		Log.debugLine();
		Log.debug("OUTBOUND MESSAGE");
		Log.debug(gson.toJson(message, SocketMessage.class));
		Log.debugLine();
		this.out.writeObject(gson.toJson(message, SocketMessage.class));
	}

}
