package main.java.pumba.messages;

import com.google.gson.Gson;

public abstract class SocketMessage
{
	private String content;

	public SocketMessage(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public abstract void process(Object object);
	
	@Override
	public String toString()
	{
		Gson gson = new Gson();
		return gson.toJson(this, SocketMessage.class);
	}

}
