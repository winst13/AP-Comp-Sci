
public class Mho extends GamePiece 
{
	private static final long serialVersionUID = 1L;

	/**
	 * This method is the constructor for a new mho.  It takes
	 * the constructor for a GamePiece and changes the icon
	 * to a mho icon
	 * @param i
	 * @param j
	 * @param p
	 */
	public Mho(int i, int j, GameBoard p) 
	{
		super(i, j, p);
		this.setIcon(GamePiece.mhoicon);
	}

	@Override
	/**
	 * This method allows any instance of the Mho class to be 
	 * identified as a mho
	 */
	public boolean ismho()
	{
		return true;
	}

	@Override
	/**
	 * This method checks whether or not a move is viable.  It returns
	 * what is occupying the space the actor wants to move to.  0 
	 * corresponds to nothing, 1 is a fence, and 2 is a mho, and 3 is
	 * an actor
	 */
	public int checkmove(int newx, int newy) 
	{
		int returnval = 0;
		GamePiece standingthere = parentboard.getpiece(newx, newy);
		if (standingthere==null) 
		{
			returnval = 0;
		}
		else if (standingthere.isfence()) 
		{
			returnval = 1;
		}
		else if (standingthere.ismho()) 
		{
			returnval = 2;
		}
		else if (standingthere.isactor())
		{
			returnval = 3;
		}
		return returnval;
	}
	
	/**
	 * This method calculates the correct move for the mhos to make.
	 * @return
	 */
	public int calculatemove()
	{
		int returnval = 0;
		int runnerx = parentboard.getActorX();
		int runnery = parentboard.getActorY();
		int xdistance = x - runnerx;
		int ydistance = y - runnery;
		
		//If the mho has same x value, it will move, even if there is a fence
		if (x == runnerx)
		{
			if (y > runnery)
			{
				returnval = checkmove(x, y-1);
				if (returnval == 1 || returnval == 2)
				{
					parentboard.remove(this);
				}
				else
				{
					moveUp();
					return returnval;
				}
			}
			else
			{
				returnval = checkmove(x, y+1);
				if (returnval == 1 || returnval == 2)
				{
					parentboard.remove(this);
				}
				else
				{
					moveDown();
					return returnval;
				}
			}
		}
		//If the mho has the same y value, it will move, even if there is a fence
		else if (y == runnery)
		{
			if (x > runnerx)
			{
				returnval = checkmove(x-1, y);
				if (returnval == 1 || returnval == 2)
				{
					parentboard.remove(this);
				}
				else
				{
					moveLeft();
					return returnval;
				}
			}
			else
			{
				returnval = checkmove(x+1, y);
				if (returnval == 1 || returnval == 2)
				{
					parentboard.remove(this);
				}
				else
				{
					moveRight();
					return returnval;
				}
			}
		}
		//If the mho does not share the same x or y values
		else
		{		
			//Tries to move diagonally
			if (xdistance > 0 && ydistance > 0)
			{
				returnval = checkmove(x-1, y-1);
				if (returnval !=1 && returnval != 2)
				{
					moveUpLeft();
					return returnval;
				}
			}
			else if (xdistance < 0 && ydistance > 0)
			{
				returnval = checkmove(x+1, y-1);
				if (returnval !=1 && returnval != 2)
				{
					moveUpRight();
					return returnval;
				}
			}
			else if (xdistance > 0 && ydistance < 0)
			{
				returnval = checkmove(x-1, y+1);
				if (returnval !=1 && returnval != 2)
				{
					moveDownLeft();
					return returnval;
				}
			}
			else
			{
				returnval = checkmove(x+1, y+1);
				if (returnval !=1 && returnval != 2)
				{
					moveDownRight();
					return returnval;
				}
			}
			
			//If moving diagonally does not work
			if ((returnval == 1 || returnval == 2) && Math.abs(xdistance) >= Math.abs(ydistance))
			{
				if (xdistance > 0)
				{
					returnval = checkmove(x-1, y);
					if(returnval != 1 || returnval != 2)
					{
						moveLeft();
						return returnval;
					}
				}
				else if (xdistance < 0)
				{
					returnval = checkmove(x+1, y);
					if(returnval != 1 || returnval != 2)
					{
						moveRight();
						return returnval;
					}
				}
			}
			else if ((returnval == 1 || returnval == 2) && Math.abs(xdistance) <= Math.abs(ydistance))
			{
				if (ydistance > 0)
				{
					returnval = checkmove(x, y-1);
					if(returnval != 1 || returnval != 2)
					{
						moveUp();
						return returnval;
					}
				}
				else if (ydistance < 0)
				{
					returnval = checkmove(x, y+1);
					if(returnval != 1 || returnval != 2)
					{
						moveDown();
						return returnval;
					}
				}
			}
				
			//If none of the above work
			if ((returnval == 1 || returnval == 2))
			{	
				//Tries to move diagonally
				if (xdistance > 0 && ydistance > 0)
				{
					returnval = checkmove(x-1, y-1);
					if (returnval != 2)
					{
						moveUpLeft();
						return returnval;
					}
				}
				else if (xdistance < 0 && ydistance > 0)
				{
					returnval = checkmove(x+1, y-1);
					if (returnval != 2)
					{
						moveUpRight();
						return returnval;
					}
				}
				else if (xdistance > 0 && ydistance < 0)
				{
					returnval = checkmove(x-1, y+1);
					if (returnval != 2)
					{
						moveDownLeft();
						return returnval;
					}
				}
				else
				{
					returnval = checkmove(x+1, y+1);
					if (returnval != 2)
					{
						moveDownRight();
						return returnval;
					}
				}
				
				//If moving diagonally does not work
				if ((returnval == 2 || returnval == 1) && Math.abs(xdistance) >= Math.abs(ydistance))
				{
					if (xdistance > 0)
					{
						returnval = checkmove(x-1, y);
						if (returnval == 1)
						{
							parentboard.remove(this);
						}
						else if (returnval != 2)
						{
							moveLeft();
							return returnval;
						}
					}
					else if (xdistance < 0)
					{
						returnval = checkmove(x+1, y);
						if (returnval == 1)
						{
							parentboard.remove(this);
						}
						else if (returnval != 2)
						{
							moveRight();
							return returnval;
						}
					}
				}
				if ((returnval == 2 || returnval == 1) && Math.abs(xdistance) <= Math.abs(ydistance))
				{
					if (ydistance > 0)
					{
						returnval = checkmove(x, y-1);
						if (returnval == 1)
						{
							parentboard.remove(this);
						}
						else if (returnval != 2)
						{
							moveUp();
							return returnval;
						}
					}
					else if (ydistance > 0)
					{
						returnval = checkmove(x, y+1);
						if (returnval == 1)
						{
							parentboard.remove(this);
						}
						else if (returnval != 2)
						{
							moveDown();
							return returnval;
						}
					}
				}
			}
		}

		return returnval;
	}
}
