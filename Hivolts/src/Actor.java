import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Actor extends GridObject
{
	public final int WIDTH_OF_ACTOR = 30;//width of the circle in the "head"
	public final int HEIGHT_OF_ACTOR = 30;//height of the circle in the "head"
	public final int SEPARATION_OF_ACTOR = 10;//distance between actor image and grid line
	
	public Actor()
	{
		setx(0);
		sety(0);
	}
	
	/**
	 * 
	 */
	public Actor(int myx, int myy)
	{
		setx(myx);
		sety(myy);
	}
	
	public void paintActor(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.drawOval(getx()*Hivolt.SIZE_OF_CELL+SEPARATION_OF_ACTOR, gety()*Hivolt.SIZE_OF_CELL+
				SEPARATION_OF_ACTOR+GridLines.SIZE_OF_TOP_BAR, WIDTH_OF_ACTOR, HEIGHT_OF_ACTOR);
	}
	
	public void RandomXY(GridObject me)
	{
		Random r = new Random();
		int newx = r.nextInt(Hivolt.COLUMNS_IN_GRID-2)+1;
		int newy = r.nextInt(Hivolt.ROWS_IN_GRID-2)+1;
		GridObject a = new Actor(newx, newy);
		boolean equals = false;

		for (int j = 0; j < Hivolt.NUMBER_OF_FENCES; j++)
		{
			if (a.equals(Hivolt.interior.listOfFences.get(j)))
			{
				equals = true;
				System.out.println("");
				j = Hivolt.NUMBER_OF_FENCES;
			}
		}
		
		for (int j = 0; j < Hivolt.NUMBER_OF_MHOS; j++)
		{
			if (a.equals(Hivolt.mhos.mholist.get(j)))
			{
				equals = true;
				j = Hivolt.NUMBER_OF_MHOS;
			}
		}
		
		if(equals == true)
		{
			RandomXY(me);
		}
		else
		{
			me.setx(newx);
			me.sety(newy);
		}
	}
}
