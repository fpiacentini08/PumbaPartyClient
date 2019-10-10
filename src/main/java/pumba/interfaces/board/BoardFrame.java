package pumba.interfaces.board;

import java.awt.Color;

import javax.swing.JFrame;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;

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
	public BoardFrame() throws PumbaException
	{
		super("Pumba Party");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		Connector connector = new Connector();
		contentPane = new BoardPanel(connector);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
	}

}
