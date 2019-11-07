package pumba.messages;

import pumba.messages.utils.SocketMessage;
import pumba.models.rooms.Room;
import pumba.models.users.User;

public class CreateRoomMessage extends SocketMessage
{
	private User user;
	private Room room;
	private String clientId;

	public CreateRoomMessage(User user)
	{
		this.user = user;
		this.clientId = SocketMessage.getClientId();
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Room getRoom()
	{
		return room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

}
