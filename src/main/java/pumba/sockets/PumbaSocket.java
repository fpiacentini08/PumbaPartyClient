package pumba.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import pumba.exceptions.ErrorCodes;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.log.Log;
import pumba.messages.utils.SocketMessage;
import pumba.messages.utils.SocketMessageSerializer;

public abstract class PumbaSocket extends Thread
{

	protected Socket socket;
	protected String localIp;
	protected ObjectInputStream in;
	protected ObjectOutputStream out;

	private final static String clientId = String.valueOf(new Random().nextInt(999));

	protected SocketMessage message;

	protected Gson gson;

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

	public PumbaSocket() throws PumbaException
	{
		super();
		try
		{
			socket = new Socket("localhost", 9999);
		}
		catch (IOException e)
		{
			Log.debug(ErrorMessages.BAD_CONNECTION);
			throw new PumbaException(ErrorMessages.BAD_CONNECTION, ErrorCodes.BAD_CONNECTION);
		}
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

		GsonBuilder gsonBilder = new GsonBuilder().registerTypeAdapter(SocketMessage.class,
				new SocketMessageSerializer());
		this.gson = gsonBilder.create();
	}

	public PumbaSocket(Connector connector) throws PumbaException
	{
		super();
		this.socket = connector.getSocket();
		localIp = connector.getLocalIp();
		this.in = connector.getIn();
		this.out = connector.getOut();
		this.gson = connector.getGson();

	}
	
	public SocketMessage receiveMessage() throws JsonSyntaxException, ClassNotFoundException, IOException
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

	public Socket getSocket()
	{
		return socket;
	}

	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}

	public Gson getGson()
	{
		return gson;
	}

	public void setGson(Gson gson)
	{
		this.gson = gson;
	}

}
