package pumba.messages.utils;

import com.google.gson.Gson;

import pumba.exceptions.PumbaException;
import pumba.sockets.Connector;
import pumba.sockets.Listener;

public abstract class SocketMessage
{
	private static String clientId = "NOT_STARTED";
	
	private String errorMessage;
	private Boolean approved;

	public SocketMessage()
	{
		super();
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public Boolean getApproved()
	{
		return approved;
	}

	public void setApproved(Boolean approved)
	{
		this.approved = approved;
	}

	public static String getClientId()
	{
		return clientId;
	}

	public static void setClientId(String clientId)
	{
		SocketMessage.clientId = clientId;
	}

	public void processResponse(Object object) throws PumbaException
	{
		try
		{
			Connector connector = (Connector) object;

			if (!connector.getMessage().getApproved())
			{
				this.setErrorMessage(connector.getMessage().getErrorMessage());
			}

		}
		catch (ClassCastException e)
		{
			Listener listener = (Listener) object;

			if (!listener.getMessage().getApproved())
			{
				this.setErrorMessage(listener.getMessage().getErrorMessage());
			}

		}
	}

	@Override
	public String toString()
	{
		Gson gson = new Gson();
		return gson.toJson(this, this.getClass());
	}

}
