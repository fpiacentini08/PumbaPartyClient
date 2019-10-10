package pumba.interfaces.board;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;
import pumba.messages.GetBoardMessage;
import pumba.messages.utils.SocketMessage;

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
