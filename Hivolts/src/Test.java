import java.util.ArrayList;


public class Test 
{
	public static void main(String args[])
	{
		
	}
	
	public static int fib(int n)
	{
		if (n==1) return 1;
		else if (n==2) return 1;
		else return (fib(n-1)+fib(n-2));
	}
	
	public static void printFences()
	{
		ArrayList<GridObject> listoffences = new ArrayList<GridObject>();
		for (int i = 0; i < 20; i++)
		{
			ElectricFenceList.newRandomFence(listoffences);
		}
		for (int i = 0; i < 20; i++)
		{
			System.out.println(i + ":  x = " + listoffences.get(i).getx());
			System.out.println("y = " + listoffences.get(i).gety());
		}
	}
}
