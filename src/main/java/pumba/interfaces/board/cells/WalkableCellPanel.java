package pumba.interfaces.board.cells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import pumba.ClientMain;

public class WalkableCellPanel extends CellPanel
{

	private static final long serialVersionUID = 2523539419055360451L;
	protected static final BufferedImage texture = loadImages();

	public WalkableCellPanel(Boolean walkable, Integer cellwidth)
	{
		super(walkable, cellwidth);
	}

	protected static BufferedImage loadImages()
	{
		try
		{
			return ImageIO.read(ClientMain.class.getResource("/RoadTexture.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);

	}

	protected void doDrawing(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		TexturePaint slateTp = new TexturePaint(texture, new Rectangle(0, 0, this.cellwidth, this.cellwidth));
		g2d.setPaint(slateTp);
		g2d.fillRect(0, 0, cellwidth, cellwidth);
		drawRect(g2d);
		g2d.dispose();

	}

	protected void drawRect(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, cellwidth, cellwidth);
	}
}
