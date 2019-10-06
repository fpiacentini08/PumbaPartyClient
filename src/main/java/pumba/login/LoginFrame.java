package pumba.login;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import pumba.connector.Connector;
import pumba.exceptions.PumbaException;

public class LoginFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3550997263122558903L;
	private LoginPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

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
