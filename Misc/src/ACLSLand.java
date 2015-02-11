import java.io.*;


public class ACLSLand 
{	
	public static int startCoordinate;
	public static int endCoordinate;
	public static int mpg;
	public static int mph;
	public static double gasCost;
	
	public static void main(String[] args)
	{
		inputStart();
		//System.out.println("Start Coordinate:"+startCoordinate);
		inputEnd();
		//System.out.println("End Coordinate:"+endCoordinate);
		inputCar();
		//System.out.println("Mpg:"+mpg);
		inputRoad();
		//System.out.println("Mph:"+mph);
		inputGas();
		//System.out.println("Gas:"+gasCost);
		printResult();
	}
	
	public static void inputStart()
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter start city (1 character):");
		boolean goodtogo = false;
		while (goodtogo == false)
		{
			try
			{
				String input = r.readLine();
				char[] inputchar = input.toCharArray();
				if (inputchar.length==1)
				{
					switch (inputchar[0])
					{
						case 'A': startCoordinate = 0;
							goodtogo = true;
							break;
						case 'B': startCoordinate = 450;
							goodtogo = true;
							break;
						case 'C': startCoordinate = 590;
							goodtogo = true;
							break;
						case 'D': startCoordinate = 710;
							goodtogo = true;
							break;
						case 'E': startCoordinate = 1030;
							goodtogo = true;
							break;
						case 'F': startCoordinate = 1280;
							goodtogo = true;
							break;
						case 'G': startCoordinate = 1360;
							goodtogo = true;
							break;
						default:  System.out.println("Please input a valid city");
					}
				}
				else
				{
					System.out.println("Please input a valid city");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void inputEnd()
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter end city (1 character):");
		boolean goodtogo = false;
		while (goodtogo == false)
		{
			try
			{
				String input = r.readLine();
				char[] inputchar = input.toCharArray();
				if (inputchar.length==1)
				{
					switch (inputchar[0])
					{
						case 'A': endCoordinate = 0;
							goodtogo = true;
							break;
						case 'B': endCoordinate = 450;
							goodtogo = true;
							break;
						case 'C': endCoordinate = 590;
							goodtogo = true;
							break;
						case 'D': endCoordinate = 710;
							goodtogo = true;
							break;
						case 'E': endCoordinate = 1030;
							goodtogo = true;
							break;
						case 'F': endCoordinate = 1280;
							goodtogo = true;
							break;
						case 'G': endCoordinate = 1360;
							goodtogo = true;
							break;
						default:  System.out.println("Please input a valid city");
					}
				}
				else
				{
					System.out.println("Please input a valid city");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void inputCar()
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter type of vehicle (1 character):");
		boolean goodtogo = false;
		while (goodtogo == false)
		{
			try
			{
				String input = r.readLine();
				char[] inputchar = input.toCharArray();
				if (inputchar.length==1)
				{
					switch (inputchar[0])
					{
						case 'C': mpg = 28;
							goodtogo = true;
							break;
						case 'M': mpg = 25;
							goodtogo = true;
							break;
						case 'F': mpg = 22;
							goodtogo = true;
							break;
						case 'V': mpg = 20;
							goodtogo = true;
							break;
						default:  System.out.println("Please input a valid car type");
					}
				}
				else
				{
					System.out.println("Please input a valid car type");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void inputRoad()
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		boolean goodtogo = false;
		System.out.println("Enter type of road (1 character):");
		while (goodtogo == false)
		{
			try
			{
				String input = r.readLine();
				char[] inputchar = input.toCharArray();
				if (inputchar.length==1)
				{
					switch (inputchar[0])
					{
						case 'I': mph = 65;
							goodtogo = true;
							break;
						case 'H': mph = 60;
							goodtogo = true;
							break;
						case 'M': mph = 55;
							goodtogo = true;
							break;
						case 'S': mph = 45;
							goodtogo = true;
							break;
						default:  System.out.println("Please input a valid road type");
					}
				}
				else
				{
					System.out.println("Please input a valid road type");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void inputGas()
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter cost of gas");
		try
		{
			String input = r.readLine();
			gasCost = Double.parseDouble(input);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void printResult()
	{
		int distTraveled = Math.abs(startCoordinate-endCoordinate);
		double hrsTime = distTraveled/(double)mph;
		double totCost = distTraveled/(double)mpg*gasCost;
		totCost = totCost*100;
		totCost = Math.round(totCost);
		totCost = totCost/100;
		double minutes = hrsTime%1*60;
		int dispHrs = (int)Math.round(hrsTime-hrsTime%1);
		int dispMin = (int)Math.round(minutes);
		
		System.out.println(distTraveled+", "+dispHrs+":"+dispMin+", $"+totCost);
	}
}
