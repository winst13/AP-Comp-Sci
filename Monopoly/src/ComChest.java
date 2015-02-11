/**
 * This class is almost exactly like the chance class, 
 * except that there is a different pool of cards that
 * the player draws from
 * @author winst13
 *
 */
public class ComChest extends Location
{	
	/**
	 * This is the constructor for the community chest.  Unlike
	 * some of the other locations, this constructor must specify
	 * index, since there will be multiple community chest
	 * locations
	 * @param index
	 */
	public ComChest(int index) 
	{
		super(index, "Community Chest");
	}

	/**
	 * This method performs an action on the player; namely, it
	 * draws a random Community Chest card and performs
	 * the action on that card
	 */
	@Override
	public void action(Player landed, Board b) 
	{
		Deck.randComChest(landed, b);
	}

	/**
	 * This method returns "Community Chest"
	 */
	@Override
	public String getType() 
	{
		return "Community Chest";
	}
}
