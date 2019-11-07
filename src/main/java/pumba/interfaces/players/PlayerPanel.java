package pumba.interfaces.players;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pumba.ClientMain;
import pumba.models.players.PlayerReduced;

public class PlayerPanel extends JPanel
{

	private static final long serialVersionUID = 2523539419055360451L;
	protected BufferedImage image = null;
	protected static final List<BufferedImage> imageList = loadImages();
	private Boolean activePlayer;

	public PlayerPanel(PlayerReduced player, Integer playerNumber, Boolean activePlayer)
	{
		super();
		this.activePlayer = activePlayer;
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
			list.add( ImageIO.read(ClientMain.class.getResource("/SimbaFace.jpg")));
			list.add( ImageIO.read(ClientMain.class.getResource("/TimonFace.jpg")));
			list.add( ImageIO.read(ClientMain.class.getResource("/PumbaFace.jpg")));
			list.add( ImageIO.read(ClientMain.class.getResource("/ScarFace.jpg")));
			list.add( ImageIO.read(ClientMain.class.getResource("/ZazuFace.jpg")));
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
		if (activePlayer)
		{
			g2d.setColor(Color.GREEN);
			g2d.setStroke(new BasicStroke(4));
		}
		else
		{
			g2d.setColor(Color.BLACK);
		}
		g2d.drawRect(0, 0, 31, 31);
		g2d.dispose();
	}
}
