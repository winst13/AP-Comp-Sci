import java.io.*;
import java.util.*;

public class ExponentHomework
	{
		public static void main(String[] args) {
			System.out.println("Please input base and exponent (in integer) for me to calculate.\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			double mybase = readnumber(reader);
			int myexp = readint(reader);
			double myresult = 0;
			if (myexp < 0) {
				myresult=expusingsquaring (1/mybase, -myexp);
			}
			else if (myexp==0){
				myresult=1;
			}
			else {
				myresult = expusingsquaring(mybase,myexp);
			}
			
			System.out.println("Result is: " + myresult + "\n");
			
		}

		private static double expusingsquaring(double mybase, int myexp) {
			double myresult=1;
			while (myexp != 0){
				if ((myexp & 1) != 0){
					myresult = myresult * mybase;
				}
			myexp >>= 1;
	        mybase *= mybase;
			}
			return myresult;
		}

		private static int readint(BufferedReader reader) {
			String readline;
			int returnnum=0;
			boolean gotit=false;
			System.out.println("Please enter exponent");
			while (!gotit){
				try{
					readline=reader.readLine();
					returnnum = Integer.parseInt(readline);
					gotit=true;
				}
				catch (NumberFormatException exception) {
	                System.out.println("Please enter a valid integer");
	            }
	            catch (Exception exception) {
	                exception.printStackTrace();
	            }
			}
			return returnnum;
		}

		private static double readnumber(BufferedReader reader) {
			double returnnum=0;
			String readline;
			boolean gotit=false;
			System.out.println("Please enter base:");
			while (!gotit){
				try{
					readline = reader.readLine();
					returnnum=Double.parseDouble(readline);
					gotit=true;
				}
				catch (NumberFormatException exception) {
	                System.out.println("Please enter a valid number:");
	            }
	            catch (Exception exception) {
	                exception.printStackTrace();
	            }
			}
			return returnnum;
		}
}