import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main 
{
	public static void main(String[] args)
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of terms in the recursive equation. \n"
				+ "This should be an integer:");
		int dim = readint(reader);
		double coeff[] = new double[dim];
		double init[] = new double[dim];
		int index = 0;
		for (int i = 0; i < dim; i++)
		{
			System.out.println("Enter coefficient #"+(i+1)+":");
			coeff[i] = readnumber(reader);
		}
		for (int i = 0; i < dim; i++)
		{
			System.out.println("Enter initial value #"+(i+1)+":");
			init[i] = readnumber(reader);
		}
		
		System.out.println("Enter the term number");
		index = readint(reader);
		
		GeneralizedFib seq = new GeneralizedFib(coeff, init);
		System.out.println("Result:");
		for (int i = 0; i < index; i++)
		{
			System.out.println(seq.findTerm(i));
		}
	}
	
	/**
	 * This method reads input and makes sure that it is an int.  It is used
	 * to get the exponent
	 * @param reader to read input
	 * @return an integer
	 */
	private static int readint(BufferedReader reader) 
	{
		String readline;
		int returnnum=0;
		boolean gotit=false;//will keep prompting until int
		while (!gotit)
		{
			try{
				readline=reader.readLine();
				returnnum = Integer.parseInt(readline);
				gotit=true;
			}
			catch (NumberFormatException exception) //if it is not an int, then this will happen
			{
                System.out.println("Please enter a valid integer");
            }
            catch (Exception exception) //anything unexpected
			{
                exception.printStackTrace();
            }
		}
		return returnnum;
	}

	/**
	 * This does the same thing as readint, except instead of int, it returns a double.
	 * It is used to get the base
	 * @param reader to read the line
	 * @return a double
	 */
	private static double readnumber(BufferedReader reader) 
	{
		double returnnum=0;
		String readline;
		boolean gotit=false;
		while (!gotit)
		{
			try
			{
				readline = reader.readLine();
				returnnum=Double.parseDouble(readline);
				gotit=true;
			}
			catch (NumberFormatException exception) 
			{
                System.out.println("Please enter a valid number:");
            }
            catch (Exception exception) 
			{
                exception.printStackTrace();
            }
		}
		return returnnum;
	}
}
