import java.util.ArrayList;


public class HumanPlayer extends Player
{
	public HumanPlayer()
	{
		super();
	}
	
	public HumanPlayer(int cash, int avatar)
	{
		super(cash, avatar);
	}

	/**
	 * This method handles the Human side of the game
	 */
	@Override
	public void act(Board b) 
	{
		//Need to implement
	}
	
	/**
	 * This method should prompt the player for a decision on the
	 * trade, and then act accordingly
	 */
	public boolean decideTrade(ArrayList<Property> p1, ArrayList<Property> p2, int cash)
	{
		//Need to implement
		
		return false;
	}
	
	/**
	 * This method should also prompt the player for a decision
	 * on which properties to mortgage, if any
	 */
	public void decideMortgage()
	{
		if (this.cash<0)
		{
			//prompt for decision on mortgage
		}
	}
	
	public boolean decideBuy(Property p)
	{
		//prompt for user input
		
		return false;
	}

	public boolean decideBail(Board b) 
	{
		//prompt for user input
		
		return false;
	}
}
