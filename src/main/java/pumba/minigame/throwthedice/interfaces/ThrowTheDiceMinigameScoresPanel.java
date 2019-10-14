package pumba.minigame.throwthedice.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ThrowTheDiceMinigameScoresPanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;
	private JTable table = new JTable();

	public ThrowTheDiceMinigameScoresPanel(Map<String, Integer> players)
	{
		String[] columns = { "Jugador", "Bichos" };
		String[][] data = { { "Jugador", "Bichos" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };

		List<String> keys = new ArrayList<>(players.keySet());
		for (int i = 1; i < keys.size() + 1; i++)
		{
			String key = keys.get(i - 1);
			data[i][0] = key;
			data[i][1] = players.get(key).toString();
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
