package pumba.interfaces.game.steps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ThrowDicePanel extends JPanel
{

	private static final long serialVersionUID = -6825966139733003662L;

	protected static final BufferedImage dice = loadImages();

	public static final Integer DICE_SIZE = 50;
	public static final Integer DICE_POS_X = 650;
	public static final Integer DICE_POS_Y = 180;
	
	private static Integer DICE_WIDTH = 360 / 6;
	protected static Integer diceNumber;

	private Random rand = new Random();

	protected static BufferedImage loadImages()
	{
		try
		{
			return ImageIO.read(new File("src/resources/img/DiceSprites.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public ThrowDicePanel()
	{
		super();
		diceNumber = rand.nextInt(6);
		setVisible(true);
		setLayout(null);


	}

	public ThrowDicePanel(Integer diceResult)
	{
		super();
		diceNumber = diceResult - 1;
		setVisible(true);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);

	}

	private BufferedImage getDice(BufferedImage dice)
	{
		DICE_WIDTH = dice.getWidth()/6;
		return dice.getSubimage(diceNumber * DICE_WIDTH, diceNumber * DICE_WIDTH, DICE_WIDTH, DICE_WIDTH);
	}

	private void doDrawing(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		TexturePaint slateTp = new TexturePaint(getDice(dice), new Rectangle(0, 0, DICE_SIZE, DICE_SIZE));
		g2d.setPaint(slateTp);
		g2d.fillRect(0, 0, DICE_SIZE, DICE_SIZE);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, DICE_SIZE, DICE_SIZE);
		g2d.dispose();

	}
}
