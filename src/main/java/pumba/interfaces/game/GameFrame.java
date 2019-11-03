package pumba.interfaces.game;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

import pumba.controllers.GameController;
import pumba.exceptions.PumbaException;
import pumba.messages.utils.SocketMessage;
import pumba.sockets.Connector;
import pumba.sockets.Listener;

public class GameFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3550997263122558903L;
	private GamePanel contentPane;
	private GameController gameController = new GameController();

	/**
	 * Create the frame.
	 * @param listener 
	 * @param allTimeListener 
	 * @param allTimeConnector 
	 * 
	 * @throws PumbaException
	 */
	public GameFrame(String username, Connector connector, Listener listener, Connector allTimeConnector, Listener allTimeListener) throws PumbaException
	{
		super("Pumba Party " + username );
		SocketMessage.setClientId(username);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		gameController.startTestGame(connector);
		contentPane = new GamePanel(connector, listener, allTimeConnector, allTimeListener);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		
	    addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	try
				{
					allTimeListener.getSocket().close();
				}
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                e.getWindow().dispose();
            }
        });


	}

}
