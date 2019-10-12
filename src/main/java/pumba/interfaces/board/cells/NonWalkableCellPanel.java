package pumba.interfaces.board.cells;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NonWalkableCellPanel extends CellPanel
{

	private static final long serialVersionUID = 603342230072250703L;
	protected static final BufferedImage texture = loadImages();

	public NonWalkableCellPanel(Boolean walkable, Integer cellwidth)
	{
		super(walkable, cellwidth);
	}

	protected static BufferedImage loadImages()
	{
		try
		{
			return ImageIO.read(new File("src/resources/img/JungleTexture.jpg"));
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

	private void doDrawing(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		TexturePaint slateTp = new TexturePaint(texture, new Rectangle(0, 0, this.cellwidth, this.cellwidth));
		g2d.setPaint(slateTp);
		g2d.fillRect(0, 0, cellwidth, cellwidth);
		g2d.dispose();

	}

}
