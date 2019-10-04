package main.java.pumba.login;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.pumba.exceptions.PumbaException;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = -8244521690183588112L;

	private JTextField textFieldUser;
	private JTextField textFieldPassword;

	private LoginController controller;
	
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		controller = new LoginController();
		setLayout(null);
		
		JLabel lblError = new JLabel("");
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
		add(btnLogin);
		
		JButton btnRegister = new JButton("Registrarse");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					controller.registerUser(textFieldUser.getText(), textFieldPassword.getText());
				}
				catch (PumbaException pe)
				{
					lblError.setText("<html>"+ pe.getMessage() +"</html>");
				}
			}
		});

		btnRegister.setBounds(246, 138, 111, 29);
		add(btnRegister);
		

	}

}