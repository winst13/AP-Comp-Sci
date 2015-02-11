/**
 * This is the class for a specific location called Chance.
 * There are several chance locations across the Monopoly board.
 * When a player lands on the chance location, a random
 * chance card is drawn and the action described by the 
 * card is taken
 * @author winst13
 *
 */
public class Chance extends Location
{	
	/**
	 * This is the constructor for a chance location.  It only
	 * takes the index, since the name is by default "Chance"
	 * @param index
	 */
	public Chance(int index) 
	{
		super(index, "Chance");
	}

	/**
	 * This method performs the action prescribed by this location
	 * and applies it to the player that landed on the chance location
	 */
	@Override
	public void action(Player landed, Board b) 
	{
		Deck.randChance(landed, b);
	}

	/**
	 * This method returns "Chance" for a chance location
	 */
	@Override
	public String getType() 
	{
		return "Chance";
	}
}
