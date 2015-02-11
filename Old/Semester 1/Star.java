import java.awt.*;
import javax.swing.JFrame;

public class Star extends JFrame
{
	public static void paintStar(Graphics g, int startx, int starty, double scalefactor)
	{
		g.setColor(Color.BLACK);
		g.drawLine((int)(startx), (int)(starty-1*scalefactor), (int)(startx+.587785252292*scalefactor), (int)(starty+.809016994375*scalefactor));
		g.drawLine((int)(startx+.587785252292*scalefactor), (int)(starty+.809016994375*scalefactor), (int)(startx-.951056516295*scalefactor), (int)(starty-.309016994375*scalefactor));
		g.drawLine((int)(startx-.951056516295*scalefactor), (int)(starty-.309016994375*scalefactor), (int)(startx+.951056516295*scalefactor), (int)(starty-.309016994375*scalefactor));
		g.drawLine((int)(startx+.951056516295*scalefactor), (int)(starty-.309016994375*scalefactor), (int)(startx-.587785252292*scalefactor), (int)(starty+.809016994375*scalefactor));
		g.drawLine((int)(startx-.587785252292*scalefactor), (int)(starty+.809016994375*scalefactor), (int)(startx), (int)(starty-1*scalefactor));
	}
}