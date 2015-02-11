import java.util.Random;

public class Actor extends GamePiece
{
	private static final long serialVersionUID = 1L;
	Random randomGenerator = new Random();

	/**
	 * This method is the constructor for a new actor.  It takes
	 * the constructor for a GamePiece and changes the icon
	 * to an actor icon
	 * @param i
	 * @param j
	 * @param p
	 */
	public Actor(int i, int j, GameBoard p) 
	{
		super(i, j, p);
		this.setIcon(GamePiece.actoricon);
	}

	@Override
	/**
	 * This method overrides the isactor() method in GamePiece, which
	 * means that any instance of an actor can be identified as an actor
	 */
	public boolean isactor()
	{
		return true;
	}

	@Override
	/**
	 * This method checks whether or not a move is viable.  It returns
	 * what is occupying the space the actor wants to move to.  0 
	 * corresponds to nothing, 1 is a fence, and 2 is a mho
	 */
	public int checkmove(int newx, int newy) 
	{
		int returnval = 0;
		GamePiece standingthere = parentboard.getpiece(newx, newy);
		if (standingthere==null) 
		{
			returnval=0;
		}
		else if (standingthere.isfence()) 
		{
			returnval=1;
		}
		else if (standingthere.ismho()) 
		{
			returnval=2;
		}
		
		return returnval;
	}
	
	/**
	 * This method sends the actor to a random square that is
	 * not occupied by a fence.  It does introduce the possibility of
	 * landing on a mho
	 * @return
	 */
	public int jump()
	{
		int returnval = 1;
		while (returnval == 1)
		{
			int newx = randomGenerator.nextInt(11);
			int newy = randomGenerator.nextInt(11);
			returnval = checkmove(newx, newy);
			if (returnval==0)
			{
				int oldx = x;
				int oldy = y;
				x = newx;
				y = newy;
				parentboard.move(oldx, oldy, this);
			}
		}
		
		return returnval;
	}
}
