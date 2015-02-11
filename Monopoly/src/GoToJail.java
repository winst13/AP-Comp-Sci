
/**
 * This class is for the Go To Jail square
 * @author winst13
 *
 */
public class GoToJail extends Location
{
	public static final int LOCATION = 30;
	
	public GoToJail() 
	{
		super(LOCATION, "Go To Jail");
	}

	@Override
	public void action(Player landed, Board b) 
	{
		landed.changeJail();
	}

	@Override
	public String getType() 
	{
		return "Go To Jail";
	}
}
