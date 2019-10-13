package pumba.interfaces.game;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class TitlePanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;

	public TitlePanel()
	{
		JLabel lblPumbaParty = DefaultComponentFactory.getInstance().createTitle("Pumba Party!");
		lblPumbaParty.setBounds(15, 16, 131, 20);
		add(lblPumbaParty);
		setVisible(true);
		setLayout(null);
		setSize(224, 45);

	}
}
