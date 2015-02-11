
public class IncomeTax extends Location
{
	public IncomeTax() 
	{
		super(4, "Income Tax");
	}

	@Override
	public void action(Player landed, Board b) 
	{
		landed.addCash(-200);
	}

	@Override
	public String getType() 
	{
		return "Income Tax";
	}
}
