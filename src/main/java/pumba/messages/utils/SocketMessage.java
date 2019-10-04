package main.java.pumba.messages.utils;

import com.google.gson.Gson;

public abstract class SocketMessage
{
	private String content;
	private Boolean approved;

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Boolean getApproved()
	{
		return approved;
	}

	public void setApproved(Boolean approved)
	{
		this.approved = approved;
	}

	public abstract void processResponse(Object object);

	@Override
	public String toString()
	{
		Gson gson = new Gson();
		return gson.toJson(this, SocketMessage.class);
	}

}
