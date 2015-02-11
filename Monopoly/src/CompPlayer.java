import java.util.ArrayList;
import java.util.Random;

/**
 * This is the class for a computer player.  It includes
 * the methods that determine its intelligent character.
 * It is able to make decisions based on the current situation, although
 * it is impossible to account for future situations since much of the
 * game is controlled by random dice
 * @author winst13
 *
 */
public class CompPlayer extends Player
{
	/**
	 * This is the default constructor for a computer player.
	 */
	public CompPlayer()
	{
		super();
	}
	
	/**
	 * This constructor for a player takes initial cash amount
	 * and avatar as arguments.  The avatar is how the player is
	 * shown in the game
	 * @param cash
	 * @param avatar
	 */
	public CompPlayer(int cash, int avatar)
	{
		super(cash, avatar);
	}
	
	/**
	 * This method takes care of the Computer's side
	 * of Monopoly.  The computer should automatically roll the dice,
	 * buy any unowned property, mortgage/unmortgage properties if needed,
	 * handle trades.
	 */
	@Override
	public void act(Board b) 
	{
		move(b);
		int index = this.getLocation();
		b.getLocations().get(index).action(this, b);
	}
	
	/**
	 * This method implements the method in Player.  It chooses
	 * which properties should be mortgaged
	 */
	public void decideMortgage()
	{
		Random r = new Random();
		int size = properties.size();
		while (cash < 0)
		{
			properties.get(r.nextInt(size)).changeMortgaged();//changeMortgaged should handle the cash
		}
	}
	
	/**
	 * This method should decide whether the trade is good or not
	 */
	public boolean decideTrade(ArrayList<Property> p1, ArrayList<Property> p2, int cash)
	{
		//Need to implement
		
		return false;
	}
	
	/**
	 * This method returns if the player should buy the property or not.
	 * Because gaining properties is so valuable, the computer will always
	 * buy a property when available
	 */
	public boolean decideBuy(Property p)
	{
		cash = cash - p.value;
		this.properties.add(p);
		if (cash < 0)
		{
			decideMortgage();
		}
		return true;
	}

	public boolean decideBail(Board b) 
	{
		int propertyBought = 0;
		for (int i = 0; i < b.getPlayers().size(); i ++)
		{
			propertyBought = propertyBought + b.getPlayers().get(i).getProperties().size();
		}
		
		if (propertyBought > 0.75*b.getLocations().size())
		{
			System.out.println("Not bailed");
			return false;
		}
		else
		{
			if (freeOutCard)
			{
				freeOutCard = false;
			}
			else
			{
				this.addCash(-Jail.BAIL);
			}
			System.out.println("Bailed");
			changeJail();
			return true;
		}
	}
}
