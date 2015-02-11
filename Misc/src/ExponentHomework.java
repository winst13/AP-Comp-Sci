import java.io.*;

/**
 * This is the homework, due 10/2/14.  This is the only class in this program
 * The program, once run, should allow the input of two numbers, the base and
 * the exponent. 
 * @author Winston Wang
 *
 */
public class ExponentHomework
	{
		/**
		 * This is the main method.  It creates a BufferedReader to obtain input.  It also
		 * lets the program accept negative exponents. 
		 * @param args
		 */
		public static void main(String[] args) 
		{
			System.out.println("Please input base and exponent (in integer) for me to calculate.\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			double mybase = readnumber(reader);
			int myexp = readint(reader);
			double myresult = 0;
			if (myexp < 0) 
			{
				myresult=expusingsquaring (1/mybase, -myexp);
			}
			else if (myexp==0)
			{
				myresult=1;
			}
			else 
			{
				myresult = expusingsquaring(mybase,myexp);
			}
			
			System.out.println("Result is: " + myresult + "\n");
			
		}

		/**
		 * This is the function that implements the exponentiation.  The exponent
		 * is separated using its binary representation into parts.
		 * @param mybase the base, which is a double
		 * @param myexp the exponent the base is raised to.  It can only be a positive integer
		 * @return
		 */
		private static double expusingsquaring(double mybase, int myexp) 
		{
			double myresult=1;//will be final answer after the loop
			while (myexp != 0)
			{
				if ((myexp & 1) != 0)
				{
					myresult = myresult * mybase;
				}
				
			myexp >>= 1;//shifts binary representation over by 1
	        mybase *= mybase;//squares mybase
			}
			return myresult;
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
			System.out.println("Please enter exponent");
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
			System.out.println("Please enter base:");
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