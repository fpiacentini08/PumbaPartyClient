package pumba.messages;

import java.util.List;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.models.rooms.Room;

public class GetAllRoomsMessage extends SocketMessage
{
	private List<Room> rooms;

	public GetAllRoomsMessage()
	{
		super();
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
			System.out.println(this.rooms);
		}

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
