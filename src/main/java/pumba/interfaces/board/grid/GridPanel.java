package pumba.interfaces.board.grid;

import java.util.List;

import javax.swing.JPanel;

import pumba.interfaces.board.cells.NonWalkableCellPanel;
import pumba.interfaces.board.cells.WalkableCellPanel;
import pumba.models.board.cells.CellReduced;

public class GridPanel extends JPanel
{

	public static final Integer CELL_WIDTH = 48;
	private static final long serialVersionUID = -6825966139733003662L;

	public GridPanel(List<CellReduced> cells, Integer dimension)
	{
		for (CellReduced cell : cells)
		{
			JPanel cellPanel;
			if (cell.getWalkable())
			{
				cellPanel = new WalkableCellPanel(cell.getWalkable(), CELL_WIDTH);

			}
			else
			{
				cellPanel = new NonWalkableCellPanel(cell.getWalkable(), CELL_WIDTH);

			}
			cellPanel.setLocation(cell.getPosition().getPosX() * CELL_WIDTH, cell.getPosition().getPosY() * CELL_WIDTH);
			cellPanel.setVisible(true);
			add(cellPanel);
		}
		setLayout(null);
		setSize(CELL_WIDTH * dimension, CELL_WIDTH * dimension);
		setVisible(true);

	}

}
