package pumba.messages;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.rooms.Room;
import pumba.models.users.User;

public class CreateRoomMessage extends SocketMessage
{
	private User user;
	private Room room;

	public CreateRoomMessage(User user)
	{
		this.user = user;
	}

	@Override
	public void processResponse(Object object) throws PumbaException
	{
		Connector connector = (Connector) object;

		if (!connector.getMessage().getApproved())
		{
			this.setErrorMessage(connector.getMessage().getErrorMessage());
		}
		else
		{
			System.out.println(this.room);
		}

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
