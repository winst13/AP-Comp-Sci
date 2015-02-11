
public class GridObject 
{
	private int x;
	private int y;
	
	/**
	 * Gets the x coordinate
	 * @return
	 */
	public int getx()
	{
		return x;
	}
	
	/**
	 * Gets the y coordinate
	 * @return
	 */
	public int gety()
	{
		return y;
	}
	
	/**
	 * Sets x coordinate
	 * @param x
	 */
	public void setx(int newx)
	{
		x = newx;
	}
	
	/**
	 * Gets y coordinate
	 * @param y
	 */
	public void sety(int newy)
	{
		y = newy;
	}
	
	public void moveUp()
	{
		sety(gety()-1);
	}
	
	public void moveDown()
	{
		sety(gety()+1);
	}
	
	public void moveLeft()
	{
		setx(getx()-1);
	}
	
	public void moveRight()
	{
		setx(getx()+1);
	}
	
	public void moveUpLeft()
	{
		moveUp();
		moveLeft();
	}
	
	public void moveUpRight()
	{
		moveUp();
		moveRight();
	}
	
	public void moveDownLeft()
	{
		moveDown();
		moveLeft();
	}
	
	public void moveDownRight()
	{
		moveDown();
		moveRight();
	}
}
