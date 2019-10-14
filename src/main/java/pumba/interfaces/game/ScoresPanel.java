package pumba.interfaces.game;

import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pumba.models.players.PlayerReduced;

public class ScoresPanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;
	private JTable table = new JTable();

	public ScoresPanel(List<PlayerReduced> players)
	{
		Collections.sort(players, Collections.reverseOrder());

		String[] columns = { "Jugador", "Bichos" };
		String[][] data = { { "Jugador", "Bichos" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };
		for (int i = 1; i < players.size() + 1; i++)
		{
			data[i][0] = players.get(i - 1).getUsername();
			data[i][1] = players.get(i - 1).getCoins().toString();
		}
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		dtm.setDataVector(data, columns);
		table.setVisible(true);
		table.setLayout(null);
		table.setSize(200, (players.size() + 1) * 16);
		add(table);
		setLayout(null);
		setVisible(true);
		setSize(800, 600);

	}
}
