package pumba.interfaces.board.cells;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class PossiblePositionCell extends WalkableCellPanel
{

	public PossiblePositionCell(Boolean walkable, Integer cellwidth)
	{
		super(walkable, cellwidth);
	}

	@Override
	protected void drawRect(Graphics2D g2d)
	{
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawRect(0, 0, cellwidth, cellwidth);
	}

}
