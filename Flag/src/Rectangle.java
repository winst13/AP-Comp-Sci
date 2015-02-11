import java.awt.Color;
import java.awt.Graphics;

public class Rectangle 
{
	public Color c;
	public int myheight;
	public Graphics g;
	
	public Rectangle(Graphics g, int myheight, Color c)
	{
		this.c = c;
		this.myheight = myheight;
		this.g = g;
	}
	
	public void drawRectangle()
	{
		this.g.setColor(this.c);
	}
	
	//learn how to automatically create constructors, getters, setters
	
	public static void drawBlueRectangle(Graphics g, int myheight)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 20, (int)(.76*(myheight-20)), (int)((double) 7/13*(myheight-20)));
	}
	
	public static void drawRectangle(Graphics g, int myheight, Color c)
	{
		g.setColor(c);
		g.fillRect(0, 20, (int)(.76*(myheight-20)), (int)((double) 7/13*(myheight-20)));
	}
}
