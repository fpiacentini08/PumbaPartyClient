package pumba.interfaces.players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pumba.models.players.PlayerReduced;

public class PlayerPanel extends JPanel
{

	private static final long serialVersionUID = 2523539419055360451L;
	protected static final BufferedImage image = loadImages();

	public PlayerPanel(PlayerReduced player)
	{
		super();
		setVisible(true);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));

	}

	protected static BufferedImage loadImages()
	{
		try
		{
			return ImageIO.read(new File("src/resources/img/SimbaFace.jpg"));
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
		TexturePaint slateTp = new TexturePaint(image, new Rectangle(0, 0, 30, 30));
		g2d.setPaint(slateTp);
		g2d.fillRect(0, 0, 30, 30);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, 30, 30);
		g2d.dispose();
	}
}
