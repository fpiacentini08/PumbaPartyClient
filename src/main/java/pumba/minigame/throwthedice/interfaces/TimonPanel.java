package pumba.minigame.throwthedice.interfaces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TimonPanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;

	protected BufferedImage image;

	private static final int SPRITE_SIZE = 200;

	public TimonPanel(BufferedImage image)
	{
		super();
		this.image = image;
		setVisible(true);
		setLayout(null);
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
		TexturePaint slateTp = new TexturePaint(image, new Rectangle(0, 0, SPRITE_SIZE, SPRITE_SIZE));
		g2d.setPaint(slateTp);
		g2d.fillRect(0, 0, SPRITE_SIZE, SPRITE_SIZE);
		g2d.dispose();

	}
}
