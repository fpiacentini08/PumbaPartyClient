package pumba.interfaces.login;

import java.awt.Color;

import javax.swing.JFrame;

import pumba.exceptions.PumbaException;
import pumba.sockets.Connector;

public class LoginFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3550997263122558903L;
	private LoginPanel contentPane;

	/**
	 * Create the frame.
	 * @throws PumbaException 
	 */
	public LoginFrame() throws PumbaException
	{
		super("Pumba Party");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		Connector connector = new Connector();
		contentPane = new LoginPanel(connector);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
	}

}
