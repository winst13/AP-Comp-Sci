import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Hivolt extends JFrame implements KeyListener
{
	public static final int ROWS_IN_GRID = 12;
	public static final int COLUMNS_IN_GRID = 12;
	public static final int NUMBER_OF_MHOS = 12;
	public static final int NUMBER_OF_FENCES = 20;
	public static final int SIZE_OF_CELL = 50;
	public static final int SIZE_OF_TOP_BAR = 20;
	public Grid mygrid;
	/**
	 * This is the constructor.  Creating an instance of Hivolt
	 * will draw the game
	 */
	public Hivolt()
	{
		init(ROWS_IN_GRID,COLUMNS_IN_GRID);
	}
	public void init(int rows, int columns) 
	{
		setSize(50*columns, 50*rows+20);
		setBackground(Color.BLACK);
		repaint();
	}

	/**
	 * This method paints everything, although everything is encapsulated
	 * in their own classes
	 */
	public void paint(Graphics g)
	{	
		mygrid = new Grid(ROWS_IN_GRID, COLUMNS_IN_GRID);
		g.setColor(Color.WHITE);
		mygrid.paint(g);
		
		for (int i = 0; i < ROWS_IN_GRID; i++)
		{
			for (int j = 0; j < COLUMNS_IN_GRID; j++)
			{
				if (mygrid.grid[i][j]==1)
				{
					ElectricFence fence = new ElectricFence(i,j);
					g.setColor(Color.WHITE);
					fence.paintFence(g);
				}
			}
		}
	}
	
	public void keyTyped(KeyEvent e) 
	{
		
	}

	public void keyPressed(KeyEvent e) 
	{
		
	}

	public void keyReleased(KeyEvent e) 
	{
		
	}
}
