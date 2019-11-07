package pumba.messages;

import java.util.List;

import pumba.messages.utils.SocketMessage;
import pumba.models.rooms.Room;

public class GetAllRoomsMessage extends SocketMessage
{
	private List<Room> rooms;
	private String clientId;

	public GetAllRoomsMessage()
	{
		super();
		this.clientId = SocketMessage.getClientId();
	}

	public List<Room> getRooms()
	{
		return rooms;
	}

	public void setRooms(List<Room> rooms)
	{
		this.rooms = rooms;
	}

}
