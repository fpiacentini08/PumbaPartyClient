package pumba.interfaces.board;

import java.awt.Color;

import javax.swing.JFrame;

import pumba.exceptions.PumbaException;
import pumba.sockets.Connector;
import pumba.sockets.Listener;

public class BoardFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3550997263122558903L;
	private BoardPanel contentPane;

	/**
	 * Create the frame.
	 * @throws PumbaException 
	 */
	public BoardFrame(Connector connector, Listener listener) throws PumbaException
	{
		super("Pumba Party");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new BoardPanel(connector, listener);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
//		setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		setUndecorated(true);

	}

}
