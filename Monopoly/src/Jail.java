
public class Jail extends Location
{
	public static final int BAIL = 50;
	public static final int LOCATION = 10;
	
	/**
	 * This is the constructor for a jail.  It 
	 */
	public Jail() 
	{
		super(LOCATION, "Jail");
	}

	public void action(Player landed, Board b) 
	{
		if (landed.inJail)
		{
			landed.decideBail(b);
		}
		else
		{
			//Nothing
		}
	}

	@Override
	public String getType() 
	{
		return "Jail";
	}
}
