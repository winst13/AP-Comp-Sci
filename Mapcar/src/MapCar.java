import java.io.*;
import java.util.*;

/**
 * This class encodes a generalized mapcar for ArrayLists.  In order
 * to apply a function to the arraylist, a class must be made
 * that extends ReturnValFunction.  Then the function mapcar()
 * in this class will perform the method execute() on each
 * element of the arraylist.  
 * @author winst13
 *
 */
public class MapCar
{
	/**
	 * This interface gives a function, specifically the execute() function,
	 * the ability to be passed as an argument.  This has a return of
	 * void
	 * @author winst13
	 *
	 */
	public interface Function 
	{
		public void execute(Object data);
	}
	   
	/**
	 * This is a test function that implements the interface
	 * Function.  In the main method, it is used to test the
	 * viability of this method of passing functions as
	 * arguments
	 * @author winst13
	 *
	 */
	public class PrintFunction implements Function 
	{
		public void execute(Object data) 
		{
			System.out.println(data.toString());
		}  
	}
	    
	/**
	 * This allows a function that returns a value to be passed
	 * as an argument.  It returns any object
	 * @author winst13
	 *
	 */
	public interface ReturnValFunction
	{
		public Object execute(Object data);
	}
	  
	/**
	 * This function implements ReturnValFunction so that
	 * it can be passed as an argument in the mapcar() method.  
	 * The function execute is the function that will be performed
	 * on all of the elements in the ArrayList
	 * @author winst13
	 *
	 */
	public class IncrementFunction implements ReturnValFunction
	{
		/**
		 * This method adds one to a number
		 */
		public Object execute (Object inputdata)
		{
			Double resultdata =  (Double)inputdata + 1;
			return resultdata;
		}	
	}
	    
	/**
	 * This method executes the method execute() on one data
	 * point
	 * @param function
	 * @param data
	 */
	public static void car(Function function, Object data) 
	{
		function.execute(data);
	}
	  
	/**
	 * This method executes the execute() method on all of the elements
	 * of an ArrayList
	 * @param function
	 * @param input
	 * @return
	 */
	public static ArrayList<Object> mapcar(ReturnValFunction function, ArrayList<Object> input)
	{
		ArrayList<Object> result = new ArrayList<Object>(input.size());
		for (int i=0; i<input.size(); i++)
		{
			result.add(function.execute(input.get(i)));
		}	
		return result;
	}

	/**
	 * This is the main method that tests mapcar
	 * @param args
	 */
	public static void main(String[] args)
	{
		MapCar mymapcar = new MapCar();
		car(mymapcar.new PrintFunction(), "test");
		ArrayList<Object> inputlist = new ArrayList<Object>(10);
		Random numbergenerator = new Random();
		for (int i = 0; i<10; i++)
		{
			int num = numbergenerator.nextInt(10);
			double add = (double)num;
			inputlist.add(add);
		}
		car(mymapcar.new PrintFunction(), inputlist);
		ArrayList<Object> result = mapcar(mymapcar.new IncrementFunction(), inputlist);
		car(mymapcar.new PrintFunction(), result);
	}
}