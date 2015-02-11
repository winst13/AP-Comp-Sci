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
	public static ElectricFenceList perimeter;
	public static ElectricFenceList interior;
	public static MhoList mhos;
	public static GridObject actor;
	public static GridObject previous;
	public int turn = 0;
	
	/**
	 * This is the constructor.  Creating an instance of Hivolt
	 * will draw the game
	 */
	public Hivolt()
	{
		
		
		//Added a KeyListener in order to detect keystrokes
		addKeyListener(this);
		
		//Makes a list of ElectricFences for the perimeter only
		perimeter = new ElectricFenceList();
		perimeter.makePerimeterList();
		
		//Makes a list of ElectricFences for the interior only
		interior = new ElectricFenceList();
		interior.makeInteriorList();
		
		//Makes a list of Mhos
		mhos = new MhoList();
		
		//Paint Actor
		actor = new Actor();
		((Actor) actor).RandomXY(actor);
		
		previous = new Actor();
	}
	
	/**
	 * This method creates the frame
	 * @param rows
	 * @param columns
	 */
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
		//g.setColor(Color.WHITE);
		//g.drawRect(0, 0, COLUMNS_IN_GRID*SIZE_OF_CELL, ROWS_IN_GRID*SIZE_OF_CELL+20);
		
		//Creates a new grid object
		GridLines grid = new GridLines(ROWS_IN_GRID, COLUMNS_IN_GRID, SIZE_OF_CELL);
		g.setColor(Color.WHITE);
		grid.paint(g);
		
		//Paints each fence in perimeter
		for (int i = 0; i < perimeter.listOfFences.size(); i++)
		{
			GridObject fence = perimeter.listOfFences.get(i);
			((ElectricFence) fence).paintFence(g);
		}
		
		//Paints each fence in interior
		for (int i = 0; i < interior.listOfFences.size(); i++)
		{
			GridObject fence = interior.listOfFences.get(i);
			((ElectricFence) fence).paintFence(g);
			//Commented line of code below prints the list of fences
			//System.out.println(fence.getx()+", "+fence.gety());
		}
		
		//Paints all the Mhos
		for (int i = 0; i < mhos.mholist.size(); i++)
		{
			GridObject mho = mhos.mholist.get(i);
			((Mho) mho).paintMho(g);
		}
		
		((Actor) actor).paintActor(g);
		
		if (turn > 0)
		{
			g.setColor(Color.BLACK);
			g.drawOval(previous.getx()*SIZE_OF_CELL+((Actor)previous).SEPARATION_OF_ACTOR, 
					previous.gety()*Hivolt.SIZE_OF_CELL+((Actor)previous).SEPARATION_OF_ACTOR
					+GridLines.SIZE_OF_TOP_BAR, ((Actor)previous).WIDTH_OF_ACTOR, 
					((Actor)previous).HEIGHT_OF_ACTOR);
		}
		
		previous = actor;
		System.out.println(turn);
		turn++;
	}

	/**
	 * This method allows the program to move the Actor,
	 * which is an essential part of the project.
	 */
	public void keyTyped(KeyEvent e) 
	{
		String key = Character.toString(e.getKeyChar());
		if (key.equalsIgnoreCase("q"))
		{
			actor.moveUpLeft();
		}
		else if (key.equalsIgnoreCase("w"))
		{
			actor.moveUp();
		}
		else if (key.equalsIgnoreCase("e"))
		{
			actor.moveUpRight();
		}
		else if (key.equalsIgnoreCase("a"))
		{
			actor.moveLeft();
		}
		else if (key.equalsIgnoreCase("d"))
		{
			actor.moveRight();
		}
		else if (key.equalsIgnoreCase("z"))
		{
			actor.moveDownLeft();
		}
		else if (key.equalsIgnoreCase("x"))
		{
			actor.moveDown();
		}
		else if (key.equalsIgnoreCase("c"))
		{
			actor.moveDownRight();
		}
		repaint();
	}

	/**
	 * 
	 */
	public void keyPressed(KeyEvent e) 
	{
		
	}

	/**
	 * 
	 */
	public void keyReleased(KeyEvent e) 
	{
		
	}
}
