import java.util.*;

/**
 * This class describes a player
 * @author winst13
 *
 */
public abstract class Player 
{
	/**
	 * This is the amount of cash a player has.  Its default
	 * is $2000, and it can only be modified by the "addCash()"
	 * function
	 */
	protected int cash;
	
	/**
	 * This is the list of properties that the player owns
	 */
	protected ArrayList<Property> properties;
	
	/**
	 * This is the location the player is at
	 */
	protected int location;
	
	/**Does not support a change avatar function
	 * Thus, avatar cannot be changed after it its
	 * set
	 */
	protected int avatar;
	
	/**
	 * This allows the program to set the order very easily
	 */
	protected Player nextPlayer;
	
	/**
	 * Used for utilities
	 */
	protected int lastMove;
	
	protected int numDoubles;
	
	protected boolean inJail;
	
	protected boolean freeOutCard = false;
	
	/**
	 * This is the default constructor for a Player.  It
	 * sets the cash to 2000
	 */
	public Player()
	{
		cash = 2000;
	}
	
	/**
	 * This is a more specific constructor.  
	 * @param cash
	 * @param avatar
	 */
	public Player(int cash, int avatar)
	{
		this.cash=cash;
		this.avatar=avatar;
		inJail = false;
	}
	
	/**
	 * This returns the amount of cash a player has
	 * @return
	 */
	public int getCash()
	{
		return cash;
	}
	
	/**
	 * This method adds money to the player.  Note that 
	 * this method also allows the use of negative numbers
	 * @param money
	 */
	public void addCash(int money)
	{
		cash = cash + money;
	}
	
	/**
	 * This returns the list of properties a player has
	 * @return
	 */
	public ArrayList<Property> getProperties()
	{
		return properties;
	}
	
	/**
	 * This returns the location number that the player is on
	 * @return
	 */
	public int getLocation()
	{
		return location;
	}
	
	/**
	 * This returns the avatar that the player is using
	 * The number corresponds to an image
	 * @return
	 */
	public int getAvatar()
	{
		return avatar;
	}
	
	/**
	 * This method moves the player a random number of spaces
	 */
	public void move(Board b)
	{
		int first = Board.dice();
		int second = Board.dice();
		
		if (inJail)
		{
			if (first == second)
			{
				changeJail();
				move(b);
			}
			else
			{
				decideBail(b);
			}
		}
		else
		{
			location = (location+first+second)%40;
			lastMove = first+second;
			if (location!=0 && location < lastMove)
			{
				addCash(200);
				System.out.println("Passed Go");
			}
			
			if (numDoubles == 2 && first==second)
			{
				changeJail();
			}
			
			b.getLocation(location).action(this, b);
			
			if (first == second)
			{
				numDoubles++;
				move(b);
			}
			else
			{
			//performs the action prescribed by the location
			//Still need to implement doubles/3 doubles = jail
			}
		}
	}
	
	/**
	 * This returns the next player
	 * @return
	 */
	public Player nextPlayer()
	{
		return nextPlayer;
	}
	
	/**
	 * This method returns the distance of the last move, for the
	 * purposes of utilities
	 * @return
	 */
	public int getLastMove()
	{
		return lastMove;
	}
	
	/**
	 *  This method changes depending on whether a human or computer is
	 *  playing.
	 */
	public abstract void act(Board b);
	
	/**
	 * This method should transfer money from new owner to old owner,
	 * and make the new owner the new owner.  If there was no old owner,
	 * then cash goes to bank (disappears)
	 * @param p
	 * @param o
	 */
	public void buy(Property p, Player o)
	{
		properties.add(p);
		o.addCash(-p.getValue());
	}
	
	/**
	 * This method should mortgage a property.  If player o and the
	 * owner of the property do not match, throw an error.  Otherwise, 
	 * gives the player o 1/10 of the value of the property.  Also inactivates
	 * property p.  If mortgaged already, throw another error.
	 * @param p
	 * @param o
	 */
	public void mortgage(Property p, Player o)
	{
		if (p.getMortgaged())
		{
			System.out.println("Mortgaged Already");
		}
		else
		{
			p.changeMortgaged();
			o.addCash(p.getValue()/10);
		}
	}
	
	/**
	 * This method should unmortgage a property.  If the player o and the owner
	 * of the property do not match, throw an error.  If player o does not have
	 * enough money, throw an error.  Deducts 1.1/10 the value of the property
	 * from player o's account and activates the property
	 * @param p
	 */
	public void unmortgage(Property p, Player o)
	{
		if (p.getMortgaged())
		{
			p.changeMortgaged();
			o.addCash(-p.getValue()/10);
		}
		else
		{
			System.out.println("Mortgaged Already");
		}
	}
	
	/**
	 * This method can handle any sort of trade, except for ones dealing with
	 * get out of jail free cards.  This method executes the trade
	 * @param o1
	 * @param o2
	 * @param p1 List of properties player o1 is offering
	 * @param p2 List of properties player o2 is offering
	 * @param cash Cash transfer from player 1 to player 2
	 */
	public void trade(Player o1, Player o2, ArrayList<Property> p1, ArrayList<Property> p2
			, int cash)
	{
		o1.properties.addAll(p2);
		o2.properties.addAll(p1);
		o2.addCash(cash);
		o1.addCash(-cash);
	}
	
	/**
	 * This method can handle any sort of trade, except for ones dealing with
	 * get out of jail free cards.  This method notifies the other player of
	 * the trade and asks for approval
	 * @param o1
	 * @param o2
	 * @param p1 List of properties player o1 is offering
	 * @param p2 List of properties player o2 is offering
	 * @param cash Cash transfer from player 1 to player 2
	 */
	public void offer(Player o, ArrayList<Property> p1, ArrayList<Property> p2
			, int cash)
	{
		o.decideTrade(p1, p2, cash);
	}
	
	public void changeJail()
	{
		if (inJail)
		{
			inJail = false;
		}
		else
		{
			inJail = true;
			location = Jail.LOCATION;
		}
	}
	
	/**
	 * This method should 
	 */
	public abstract void decideMortgage();
	
	public abstract boolean decideTrade(ArrayList<Property> p1, ArrayList<Property> p2, int cash);
	
	public abstract boolean decideBuy(Property p);
	
	public abstract boolean decideBail(Board b);
}
