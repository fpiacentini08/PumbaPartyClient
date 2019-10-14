//package pumba.minigame.throwthedice.interfaces;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.imageio.ImageIO;
//import javax.swing.JPanel;
//
//public class TimonAnimationPanel extends JPanel implements Runnable
//{
//
//	private static final long serialVersionUID = -6825966139733003662L;
//
//	protected static final List<BufferedImage> images = loadImages();
//
//	protected static List<BufferedImage> loadImages()
//	{
//		try
//		{
//			List<BufferedImage> spriteList = new ArrayList<>();
//			BufferedImage spriteSheet = ImageIO.read(new File("src/resources/img/TimonSpriteSheet.png"));
//			Integer spriteWidth = spriteSheet.getWidth() / 8;
//			Integer spriteHeigth = spriteSheet.getHeight() / 3;
//			for (int i = 0; i < spriteSheet.getWidth() - spriteWidth; i = i + spriteWidth)
//			{
//				for (int j = 0; j < spriteSheet.getHeight() - spriteHeigth; j = j + spriteHeigth)
//				{
//					spriteList.add(spriteSheet.getSubimage(i, j, spriteWidth - 1, spriteHeigth - 1));
//				}
//			}
//			spriteList.remove(spriteList.size() - 1);
//			spriteList.remove(spriteList.size() - 1);
//			return spriteList;
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public TimonAnimationPanel()
//	{
//		super();
//		setVisible(true);
//		setLayout(null);
//		setBounds(200, 200, 200, 200);
//		
//	}
//
//	@Override
//	public void run()
//	{
//		long tick = System.currentTimeMillis();
//		Integer imageIndex = 0;
//		while (true)
//		{
//			System.out.println("Da vueltas sin parar");
//			if (System.currentTimeMillis() > tick)
//			{
//				this.removeAll();
//				JPanel timon = new TimonPanel(images.get(imageIndex));
//				timon.setSize(200, 200);
//				timon.setVisible(true);
//				timon.repaint();
//				this.add(timon);
//				if (imageIndex < images.size() - 1)
//				{
//					imageIndex++;
//				}
//				else
//				{
//					imageIndex = 0;
//				}
//				tick = System.currentTimeMillis();
//			}
//
//		}
//
//	}
//
//}
