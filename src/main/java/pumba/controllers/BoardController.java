package pumba.controllers;

import pumba.exceptions.PumbaException;
import pumba.messages.GetBoardMessage;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;

public class BoardController
{

	public void getBoard(Connector connector)
			throws PumbaException
	{
		SocketMessage message = new GetBoardMessage();
		connector.setMessage(message);
		connector.run();
	}

}
