import java.awt.*;
import java.awt.Color;

import javax.swing.JFrame;

public class Flag extends JFrame
{
	public Flag(int width, int height)
	{
		init(width, height);
	}
	
	public void init(int width, int height)
	{
		setSize(width, height);
		setBackground(Color.BLACK);
		repaint();
	}
	
	public void paint(Graphics g)
	{
		Stripes.paintStripes(g, 13, 500, 500);
	}
}
