import java.awt.*;
import javax.swing.JFrame;

public class Stripes extends JFrame
{
	public static void paintStripes(Graphics g, int nofstripes, int width, int height)
	{
		for (int i=1; i<= nofstripes; i++)
		{
			if ((int)(i/2) == i/2)
			{
				g.setColor(Color.WHITE);
				g.fillRect(0, (int)(height/nofstripes*(i-1)), (int)width, (int)(height/nofstripes));
			}
			else
			{
				g.setColor(Color.RED);
				g.fillRect(0, (int)(height/nofstripes*(i-1)), (int)width, (int)(height/nofstripes));
			}
		}
	}
}
