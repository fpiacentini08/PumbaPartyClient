package pumba.controllers;

import pumba.connector.Connector;
import pumba.messages.CreateRoomMessage;
import pumba.messages.GetAllRoomsMessage;
import pumba.messages.utils.SocketMessage;
import pumba.models.users.User;

public class RoomsMenuController
{

	public void getAllRooms(Connector connector)
	{
		SocketMessage message = new GetAllRoomsMessage();
		connector.setMessage(message);
		connector.run();
		
	}

	public void createRoom(Connector connector, User user)
	{
		SocketMessage message = new CreateRoomMessage(user);
		connector.setMessage(message);
		connector.run();
		
	}
}
