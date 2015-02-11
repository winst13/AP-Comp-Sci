/**
 * This is the class for the location called "Go".
 * It is always the first location (index 0)
 * @author winst13
 *
 */
public class Go extends Location
{
	/**
	 * This is the constructor for the "Go" square
	 */
	public Go() 
	{
		super(0, "Go");
	}

	@Override
	/**
	 * Player should collect 400 upon landing on Go
	 */
	public void action(Player landed, Board b) 
	{
		landed.addCash(400);
	}

	/**
	 * This method returns "Go"
	 */
	@Override
	public String getType() 
	{
		return "Go";
	}
}
