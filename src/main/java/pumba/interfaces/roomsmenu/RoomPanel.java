package pumba.interfaces.roomsmenu;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pumba.connector.Connector;
import pumba.controllers.RoomsMenuController;
import pumba.models.rooms.Room;
import pumba.models.users.User;

public class RoomPanel extends JPanel
{

	private static final long serialVersionUID = -8244521690183588112L;

	private RoomsMenuController controller;

	private DefaultListModel<String> model;

	/**
	 * Create the panel.
	 * 
	 * @param connector
	 * @param user
	 * @param room 
	 */
	public RoomPanel(Connector connector, User user, Room room)
	{
		controller = new RoomsMenuController();
		setLayout(null);

		JLabel lblTitle = new JLabel("Sala creada!");
		lblTitle.setBounds(43, 34, 203, 20);
		add(lblTitle);
		this.setVisible(true);
		JOptionPane.showMessageDialog(new JFrame(), "Cuarto creado!");

	}

}
