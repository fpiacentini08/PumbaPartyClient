package pumba.interfaces.players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pumba.models.players.PlayerReduced;

public class PlayerPanel extends JPanel
{

	private static final long serialVersionUID = 2523539419055360451L;
	protected BufferedImage image = null;
	protected static final List<BufferedImage> imageList = loadImages();

	public PlayerPanel(PlayerReduced player, Integer playerNumber)
	{
		super();
		setVisible(true);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		image = imageList.get(playerNumber);

	}

	protected static List<BufferedImage> loadImages()
	{
		try
		{
			List<BufferedImage> list = new ArrayList<>();
			list.add(ImageIO.read(new File("src/resources/img/SimbaFace.jpg")));
			list.add(ImageIO.read(new File("src/resources/img/TimonFace.jpg")));
			list.add(ImageIO.read(new File("src/resources/img/PumbaFace.jpg")));
			list.add(ImageIO.read(new File("src/resources/img/ScarFace.jpg")));
			list.add(ImageIO.read(new File("src/resources/img/ZazuFace.jpg")));
			return list;
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
