import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class Mho extends GridObject
{
	private final int WIDTH_OF_MHO = 30;
	private final int HEIGHT_OF_MHO = 30;
	private final int SEPARATION_OF_MHO = 10;
	
	/**
	 * This is the constructor for an Mho.  It only
	 * has two arguments, the x and y coordinates
	 * @param myx
	 * @param myy
	 */
	public Mho(int myx, int myy)
	{
		setx(myx);
		sety(myy);
	}
	
	/**
	 * This method paints an individual Mho.  It is possible
	 * to change this to display anything that will fit inside
	 * a single cell
	 * @param g
	 */
	public void paintMho(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawOval(getx()*Hivolt.SIZE_OF_CELL+SEPARATION_OF_MHO, 
				gety()*Hivolt.SIZE_OF_CELL+SEPARATION_OF_MHO+GridLines.SIZE_OF_TOP_BAR, 
				WIDTH_OF_MHO, HEIGHT_OF_MHO);
	}
	
	/**
	 * This method adds an Mho to an ArrayList of Mhos
	 * @param listofmhos
	 * @return the exact same as listofmhos, except with a
	 * new Mho at the end
	 */
	public static void addRandomMho(ArrayList<GridObject> listofmhos)
	{
		Random z = new Random();
		int x = z.nextInt(Hivolt.COLUMNS_IN_GRID-2)+1;
		int y = z.nextInt(Hivolt.ROWS_IN_GRID-2)+1;
		GridObject newmho = new Mho(x,y);
		boolean equals = false;
		
		for (int j = 0; j < Hivolt.NUMBER_OF_FENCES; j++)
		{
			if (newmho.equals(Hivolt.interior.listOfFences.get(j)))
			{
				equals = true;
				j = Hivolt.NUMBER_OF_FENCES;
			}
		}
		
		for (int j = 0; j < listofmhos.size(); j++)
		{
			if (newmho.equals(listofmhos.get(j)))
			{
				equals = true;
			}
		}

		if (equals == false)
		{
			listofmhos.add(newmho);
		}
		else
		{
			addRandomMho(listofmhos);
		}
	}
	
	public void followActor()
	{
		
	}
}