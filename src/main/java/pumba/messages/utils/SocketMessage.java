package pumba.messages.utils;

import com.google.gson.Gson;

import pumba.exceptions.PumbaException;

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

	public abstract void processResponse(Object object) throws PumbaException;

	@Override
	public String toString()
	{
		Gson gson = new Gson();
		return gson.toJson(this, this.getClass());
	}

}
