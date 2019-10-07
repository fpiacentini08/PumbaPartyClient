package pumba.interfaces.roomsmenu;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import pumba.connector.Connector;
import pumba.exceptions.ErrorMessages;
import pumba.messages.CreateRoomMessage;
import pumba.messages.GetAllRoomsMessage;
import pumba.models.rooms.Room;
import pumba.models.users.User;

public class RoomsMenuPanel extends JPanel
{

	private static final long serialVersionUID = -8244521690183588112L;

	private RoomsMenuController controller;

	private DefaultListModel<String> model;

	/**
	 * Create the panel.
	 * 
	 * @param connector
	 * @param user
	 */
	public RoomsMenuPanel(Connector connector, User user)
	{
		controller = new RoomsMenuController();
		setLayout(null);

		JLabel lblTitle = new JLabel("Bienvenido a Pumba Party!");
		lblTitle.setBounds(43, 34, 203, 20);
		add(lblTitle);

		JLabel lblCreateRoom = new JLabel("Puede crear una sala nueva haciendo click");
		lblCreateRoom.setBounds(43, 70, 301, 20);
		add(lblCreateRoom);

		JLabel lblLinkCreateRoom = new JLabel("AQUI");
		lblLinkCreateRoom.setBounds(290, 70, 69, 20);

		lblLinkCreateRoom.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				createRoom(connector, user);
			}

		});

		add(lblLinkCreateRoom);

		JLabel lblEnterExistingRoom = new JLabel("O entrar a una de las salas ya creadas:");
		lblEnterExistingRoom.setBounds(43, 107, 317, 20);
		add(lblEnterExistingRoom);

		getAllRooms(connector, user);

	}

	private void getAllRooms(Connector connector, User user)
	{
		synchronized (this)
		{
			controller.getAllRooms(connector);
			if (connector.getMessage().getApproved())
			{
				GetAllRoomsMessage message = (GetAllRoomsMessage) connector.getMessage();
				if (!message.getRooms().isEmpty())
				{
					model = new DefaultListModel<>();
					for (Room r : message.getRooms())
					{
						model.addElement("Sala nro " + r.getId());
					}
					JList<String> existingRoomList = new JList<>(model);
					existingRoomList.setBounds(43, 143, 333, 141);

					JScrollBar scrollB2 = new JScrollBar(JScrollBar.VERTICAL, 10, 60, 0, 100);
					existingRoomList.add(scrollB2, BorderLayout.EAST);

					add(existingRoomList);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), ErrorMessages.ERROR_GETALL_ROOM);
			}
		}

	}

	private void createRoom(Connector connector, User user)
	{
		synchronized (this)
		{
			controller.createRoom(connector, user);
			CreateRoomMessage message = (CreateRoomMessage) connector.getMessage();
			if (connector.getMessage().getApproved())
			{
				JPanel createPanel = new RoomPanel(connector, user, message.getRoom());
				createPanel.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), ErrorMessages.ERROR_CREATE_ROOM);
			}
		}

	}
}
