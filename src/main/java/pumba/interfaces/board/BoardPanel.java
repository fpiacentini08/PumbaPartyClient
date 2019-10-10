package pumba.interfaces.board;

import javax.swing.JPanel;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;

public class BoardPanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;
	private static BoardController controller = new BoardController();

	public BoardPanel(Connector connector)
	{
		getBoard(connector);
		setLayout(null);

	}

	private void getBoard(Connector connector)
	{
		synchronized (this)
		{
			try
			{
				controller.getBoard(connector);
			}
			catch (PumbaException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
