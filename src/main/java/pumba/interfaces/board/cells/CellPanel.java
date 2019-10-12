package pumba.interfaces.board.cells;

import javax.swing.JPanel;

public abstract class CellPanel extends JPanel
{

	private static final long serialVersionUID = -5245118342369289132L;

	protected Integer cellwidth;

	public CellPanel(Boolean walkable, Integer cellwidth)
	{
		this.cellwidth = cellwidth;
		setSize(cellwidth, cellwidth);
		setVisible(true);

	}

}
