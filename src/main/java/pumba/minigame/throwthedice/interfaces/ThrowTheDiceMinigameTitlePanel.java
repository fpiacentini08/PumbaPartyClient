package pumba.minigame.throwthedice.interfaces;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class ThrowTheDiceMinigameTitlePanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;

	public ThrowTheDiceMinigameTitlePanel()
	{
		JLabel lblPumbaParty = DefaultComponentFactory.getInstance().createTitle("Los dados de Pumba!");
		lblPumbaParty.setBounds(15, 16, 131, 20);
		add(lblPumbaParty);
		setVisible(true);
		setLayout(null);
		setSize(224, 45);

	}
}
