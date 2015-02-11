import java.awt.Color;
import java.awt.Graphics;

public class ElectricFence extends GridObject
{	
	public static final int NO_OF_FENCES = 20;
	private static final int FENCE_SEPARATION = 10;
	private static final int SIZE_OF_FENCE = 30;
	
	/**
	 * Creates a new ElectricFence with given coordinates
	 * @param myxcoordinate
	 * @param myycoordinate
	 */
	public ElectricFence(int myxcoordinate, int myycoordinate)
	{
		setx(myxcoordinate);
		sety(myycoordinate);
	}
	
	/**
	 * Paints a single fence.  This can also be changed
	 * @param g
	 */
	public void paintFence(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.drawRect(this.getx()*Hivolt.SIZE_OF_CELL+FENCE_SEPARATION, 
				this.gety()*Hivolt.SIZE_OF_CELL+FENCE_SEPARATION+GridLines.SIZE_OF_TOP_BAR, 
				SIZE_OF_FENCE, SIZE_OF_FENCE);
	}
}
