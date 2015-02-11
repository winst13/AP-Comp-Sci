import java.io.*;
import java.util.*;

public class MapCar
{
	    public interface Function 
	    {
	        public void execute(Object data);
	    }
	   
	    public class ExamplePrintFunction implements Function 
	    {
	        public void execute(Object data) 
	        {
	            System.out.println(data.toString());
	        }  

	    }
	    public interface ReturnValFunction{
	    	public Object execute(Object data);
	    }
	    
	    public class ExampleIncrementFunction implements ReturnValFunction{
		    public Object execute (Object inputdata){
		    	Double resultdata =  (Double)inputdata + 1;
		        return resultdata;
		    }	
	    }
	    public static void car(Function function, Object data) 
	    {
	        function.execute(data);
	    }
	    public static ArrayList<Object> mapcar(ReturnValFunction function, ArrayList<Object> input){
	    	ArrayList<Object> result = new ArrayList<Object>(input.size());
	    	for (int i=0; i<input.size(); i++){
	    		result.add(function.execute(input.get(i)));
	    	}	
	    	return result;
	    }

	    public static void main(String[] args)
	    {
	    	MapCar mymapcar = new MapCar();
	        car(mymapcar.new ExamplePrintFunction(), "hello world");
	        ArrayList<Object> inputlist = new ArrayList<Object>(10);
	        Random numbergenerator = new Random();
	        for (int i = 0; i<10; i++){
	        	inputlist.add(numbergenerator.nextDouble());
	        }
	        ArrayList<Object> result = mapcar(mymapcar.new ExampleIncrementFunction(), inputlist);
	        car(mymapcar.new ExamplePrintFunction(), result);
	    }
}
