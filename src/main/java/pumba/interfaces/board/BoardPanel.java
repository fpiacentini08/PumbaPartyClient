package pumba.interfaces.board;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pumba.connector.Connector;
import pumba.exceptions.ErrorMessages;
import pumba.exceptions.PumbaException;
import pumba.interfaces.board.grid.GridPanel;
import pumba.log.Log;
import pumba.messages.GetBoardMessage;

public class BoardPanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;
	private static BoardController controller = new BoardController();

	public BoardPanel(Connector connector)
	{
		getBoard(connector);
		setLayout(null);
		setVisible(true);

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
				Log.debug(e.getStackTrace().toString());

			}

			if (connector.getMessage().getApproved())
			{
				GetBoardMessage message = (GetBoardMessage) connector.getMessage();
				if (!message.getCells().isEmpty())
				{
					JPanel gridPanel = new GridPanel(message.getCells(), message.getDimension());
					gridPanel.setVisible(true);
					add(gridPanel);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), ErrorMessages.ERROR_GET_BOARD);
			}

		}

	}

}
