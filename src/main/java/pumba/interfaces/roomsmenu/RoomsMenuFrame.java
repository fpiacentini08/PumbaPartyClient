package pumba.interfaces.roomsmenu;

import java.awt.Color;

import javax.swing.JFrame;

import pumba.exceptions.PumbaException;
import pumba.models.users.User;
import pumba.sockets.Connector;

public class RoomsMenuFrame extends JFrame
{
	private static final long serialVersionUID = 8141531665265978108L;
	private RoomsMenuPanel contentPane;

	/**
	 * Create the frame.
	 * @param user 
	 * 
	 * @throws PumbaException
	 */
	public RoomsMenuFrame(User user) throws PumbaException
	{
		super("Pumba Party");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		Connector connector = new Connector();
		contentPane = new RoomsMenuPanel(connector, user);
		setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
	}

}
