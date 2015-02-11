import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

/*
 * This program is the actual class for the flag.  It takes methods from Stripes and Star to create
 * the flag.  It contains a constructor, methods to paint the flag, as well as methods that come from
 * ComponentListener.
*/
@SuppressWarnings("serial")
public class Flag extends JFrame implements ComponentListener
{
	private int height = 480;//500 is initial frame height, but the top bar is 20 pixels
	
	/*
	 * Creates a new Flag object with height myheight and width 1.9*myheight
	 * All distances are calculated by proportions, starting from myheight
	 * @param int myheight
	 */
	public Flag(int myheight)
	{
		height = myheight;//Sets the size
		init(myheight);
	}
	
	/*
	 * Draws the frame
	 * @param int myheight
	 * @return void
	 */
	public void init(int myheight)
	{
		setSize((int) (1.9*myheight), myheight);//Sets frame size
		setBackground(Color.BLACK);//Sets background color to black
		repaint();
	}
	
	/*
	 * Paints the flag
	 * @param Graphics g
	 * @return void
	 */
	public void paint(Graphics g)
	{
		//ComponentListener to detect resizes
		addComponentListener(this); 
		//Stripes
		Stripes.paintStripes(g, 13, (int) (1.9*height), height);
		//Blue rectangle
		Rectangle.drawBlueRectangle(g, height);
		//Stars
		Star.paintStarRows(g, height);
	}

	/*
	 * Detects when the frame is resized
	 * @param ComponentEvent e
	 * @return void
	 */
	public void componentResized(ComponentEvent e) 
	{
		this.height = this.getHeight();//Detects the new frame height
		this.init(height);//Changes the flag size to new height
	}

	/*
	 * Detects when the frame is moved
	 * @param ComponentEvent e
	 * @return void
	 */
	public void componentMoved(ComponentEvent e) 
	{
		
	}

	/*
	 * Detects when the frame is shown
	 * @param ComponentEvent e
	 * @return void
	 */
	public void componentShown(ComponentEvent e) 
	{
		
	}

	/*
	 * Detects when the frame is hidden
	 * @param ComponentEvent e
	 * @return void
	 */
	public void componentHidden(ComponentEvent e) 
	{
		
	}
}
