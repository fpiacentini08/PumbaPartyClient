package pumba.interfaces.login;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import pumba.connector.Connector;
import pumba.controllers.LoginController;
import pumba.exceptions.PumbaException;
import pumba.interfaces.roomsmenu.RoomsMenuFrame;
import pumba.models.users.User;

public class LoginPanel extends JPanel
{

	private static final long serialVersionUID = -8244521690183588112L;

	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private JLabel lblError;
	private JButton btnRegister;

	private LoginController controller;

	/**
	 * Create the panel.
	 * 
	 * @param connector
	 */
	public LoginPanel(Connector connector)
	{
		controller = new LoginController();
		setLayout(null);

		lblError = new JLabel("");
		lblError.setBounds(160, 183, 197, 81);
		lblError.setBackground(Color.RED);
		add(lblError);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(160, 54, 197, 26);
		add(textFieldUser);
		textFieldUser.setColumns(10);

		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setBounds(56, 57, 69, 20);
		add(lblUser);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(56, 99, 91, 20);
		add(lblPassword);

		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(160, 96, 197, 26);
		add(textFieldPassword);
		textFieldPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(160, 138, 71, 29);
		btnLogin.setFocusable(true);
		btnLogin.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					login(connector);
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				// TODO Auto-generated method stub
			}
		});
		btnLogin.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				login(connector);
			}
		});

		add(btnLogin);

		btnRegister = new JButton("Registrarse");
		btnRegister.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					register(connector);
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				// TODO Auto-generated method stub
			}
		});

		btnRegister.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				register(connector);
			}
		});

		btnRegister.setBounds(246, 138, 111, 29);
		add(btnRegister);

	}

	private void login(Connector connector)
	{
		synchronized (this)
		{
			try
			{
				controller.loginUser(connector, textFieldUser.getText(), textFieldPassword.getText());
				if (connector.getMessage().getApproved())
				{
					JFrame roomsMenuFrame = new RoomsMenuFrame(new User(textFieldUser.getText(), textFieldPassword.getText()));
					roomsMenuFrame.setVisible(true);
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
					frame.dispose();
					lblError.setText("<html>" + "Login exitoso." + "</html>");
				}
				else
				{
					lblError.setText("<html>" + connector.getMessage().getErrorMessage() + "</html>");
				}
			}
			catch (PumbaException pe)
			{
				lblError.setText("<html>" + pe.getMessage() + "</html>");
			}
		}

	}

	private void register(Connector connector)
	{
		synchronized (this)
		{
			try
			{
				controller.registerUser(connector, textFieldUser.getText(), textFieldPassword.getText());
				if (connector.getMessage().getApproved())
				{
					lblError.setText(
							"<html>" + "Usuario registrado correctamente.<br>Por favor, realice el login." + "</html>");
					btnRegister.setVisible(false);
				}
				else
				{
					lblError.setText("<html>" + connector.getMessage().getErrorMessage() + "</html>");
				}
			}
			catch (PumbaException pe)
			{
				lblError.setText("<html>" + pe.getMessage() + "</html>");
			}
		}

	}

}
