
public class Mho 
{
	final static int REGULAR = 0;
	final static int SPECIAL = 1;
	
	private int x;
	private int y;
	private int type;
	private boolean isDead;
	
	public Mho(int xPos, int yPos, int type)
	{ 
		x = xPos;
		y = yPos;
		this.type = type;
		isDead = false;
	}
	public void setX(int xPos)
	{
		x = xPos;
	}
	public void setDead(boolean dead)
	{
		isDead = dead;
	}
	public boolean getDead()
	{
		return isDead;
	}
	public void setY(int yPos)
	{
		y = yPos;
	}
	public void setType(int typeName)
	{
		type = typeName;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getType()
	{
		return type;
	}
}
