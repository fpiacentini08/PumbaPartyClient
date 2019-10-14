package pumba.minigame.throwthedice.interfaces;

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

public class ThrowTheDiceMinigameBackgroundPanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;

	protected static final BufferedImage image = loadImages();

	private static Integer IMAGE_WIDTH = 580;
	private static Integer IMAGE_HEIGHT = 600;

	protected static BufferedImage loadImages()
	{
		try
		{
			return ImageIO.read(new File("src/resources/img/Background1.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public ThrowTheDiceMinigameBackgroundPanel()
	{
		super();
		setVisible(true);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);

	}

	private BufferedImage getImage(BufferedImage image)
	{
		return image.getSubimage(0, 0, Math.min(image.getWidth(), IMAGE_WIDTH), Math.min(image.getHeight(), IMAGE_HEIGHT));
	}

	private void doDrawing(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		TexturePaint slateTp = new TexturePaint(getImage(image), new Rectangle(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT));
		g2d.setPaint(slateTp);
		g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		g2d.dispose();

	}
}
