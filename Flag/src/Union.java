import java.awt.Color;
import java.awt.Graphics;

public class Union 
{
	public Union(Color c)
	{
		
	}
	
	public static void paintUnion(Graphics g, int height)
	{
		Rectangle.drawBlueRectangle(g, height);
		Star.paintStarRows(g, height);
	}
}
