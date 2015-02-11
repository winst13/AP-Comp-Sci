
public class Set 
{
	private double[] vals = new double[5];
	
	public void setVals(double y1, double y2, double y3, double y4, double y5)
	{
		vals[1] = y1;
		vals[2] = y2;
		vals[3] = y3;
		vals[4] = y4;
		vals[5] = y5;
	}
	
	public double[] getSet()
	{
		return vals;
	}
}
