
public abstract class Location 
{
	/**
	 * This is the location of the Location
	 * on the monopoly board
	 */
	private int index;
	
	/**
	 * This is the name of the Location.  It does not have
	 * any computational effect
	 */
	private String name;
	
	/**
	 * This is the method that determines what
	 * the Player does when it lands on the Location
	 */
	public abstract void action(Player landed, Board b);
	
	/**
	 * This method creates a new location with an index
	 * specified
	 * @param index
	 */
	public Location(int index, String name)
	{
		this.index = index;
		this.name = name;
	}
	
	/**
	 * This method returns the location of the Location
	 * @return
	 */
	public int getIndex()
	{
		return index;
	}
	
	/**
	 * This returns the name of the location
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * This method returns the class of the object as a String. 
	 * For example, for an object of the class Utility this method would
	 * return "Utility" 
	 * @return
	 */
	public abstract String getType();
}
