import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;

/**
 * This is the new fitting method that I am programming
 * It takes 5 points and forces a fit.  It then averages all the
 * fitted data, returning the mean, standard deviation, and the
 * sample size
 * @author winst13
 *
 */
public class SampleFit 
{
	public double lambda = 0.000000000004336;
	public double a = 0.0000000125;
	public double d = 0.00000013;
	public double I = 1;
	
	public static String expfilename = "1-Data.csv";
	//public static String expfilename = "1-Theoretical.csv";
	
	/*public double founda = 0;
	public double foundd = 0;
	public double foundI = 0;*/
	
	public double meanV = 0;
	public double stdevV = 0;
	
	public ArrayList<Double> theta = new ArrayList<Double>();
	public ArrayList<Double> yvals = new ArrayList<Double>();
	
	/*public static final int A_ITERATIONS = 200;
	public static final int D_ITERATIONS = 200;
	public static final int V_ITERATIONS = 200;
	public static final int I_ITERATIONS = 200;
	public static final double A_ERROR = 0.00000000007;
	public static final double D_ERROR = 0.000000000754;
	public static final double V_ERROR = 0.005;
	public static final double I_ERROR = 0.005;*/
	
	/**
	 * This is the main method that executes the 
	 * @param args
	 */
	public static void main(String args[])
	{
		try
		{
			SampleFit myfit = new SampleFit(expfilename);
			System.out.println("Mean of V:  "+myfit.meanV);
			System.out.println("Stdev of V:  "+myfit.stdevV);
			//System.out.println("A:  "+myfit.founda);
			//System.out.println("D:  "+myfit.foundd);
			//System.out.println("I:  "+myfit.foundI);
			System.out.println("");
			myfit.findV();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public SampleFit(String expfilename) throws IOException
	{
		CSVReader reader = new CSVReader(new FileReader(expfilename));
	    String[] nextLine;
	    
	    while ((nextLine = reader.readNext()) != null) 
		{
	    	try {// nextLine[] is an array of values from the line
	    	Double addition = Double.parseDouble(nextLine[0]);
	    	theta.add(addition);		
	    	System.out.println(addition);
	    	Double nextyval = Double.parseDouble(nextLine[3]);
	    	yvals.add(nextyval);
	    	//System.out.println(nextyval);
	    	this.calculateDistance(a, d, 1, I, addition, nextyval);
	    	}
	    	catch (Exception e){}
		}
	    reader.close();
	    
	    //fitAD();
	    fit();
	}
	
	/*public void fitAD()
	{
		double currenta = 0.00000001406;
		double currentd = 0.00000015073;
		double currentI = 1;
		double founddistance = Double.MAX_VALUE;
		for (int i = 0; i<A_ITERATIONS; i++)
		{
			if (i%2 == 0)
			{
				currenta = currenta-i*A_ERROR;
			}
			else
			{
				currenta = currenta+i*A_ERROR;
			}
			for (int j = 0; j<D_ITERATIONS; j++)
			{
				if (i%2 == 0)
				{
					currentd = currentd-j*D_ERROR;
				}
				else
				{
					currentd = currentd+j*D_ERROR;
				}
				for (int k = 0; k<I_ITERATIONS; k++)
				{
					if (i%2 == 0)
					{
						currentI = currentI-k*I_ERROR;
					}
					else
					{
						currentI = currentI+k*I_ERROR;
					}
				}
				
				fit();
				
				double distance = calculateAveDistance(currenta,currentd,currentI,meanV);
				if (distance<founddistance)
				{
					founda=currenta;
					foundd=currentd;
					foundI=currentI;
					founddistance = distance;
				}
			}
		}
	}*/
	
	public double calculateAveDistance(double a, double d, double I, double V)
	{
		double totalDistSquare = 0;
		for (int i = 0; i<theta.size(); i++)
		{
			totalDistSquare = totalDistSquare + Math.pow(calculateDistance(a, d, I, V, theta.get(i), yvals.get(i)), 2);
		}
		//double ave = totalDistSquare/theta.size();
		//return ave;
		return totalDistSquare;
	}
	
	public double calculateDistance(double a, double d, double V, double I, double theta, double y)
	{
		double retval = 0;
		double firstterm = Math.pow((Math.sin(Math.PI*a*Math.sin(theta)/lambda)/
				(Math.PI*a*Math.sin(theta)/lambda)), 2);
		double secondterm = 1+V*Math.cos(2*Math.PI*d*theta/lambda);
		retval=I*firstterm*secondterm;
		if ( Double.isNaN(retval))
		{
			return 0;
		}
		System.out.println("calculating distance: input y="+ y + " calculated y:"+ retval);
		return y-retval;
	}
	public void findV(){
		double beginv = 0.3;
		double[] alldistances = new double[100];
		double foundv = 0;
		double founddistance = Double.MAX_VALUE;
				for (int vstep=0; vstep<80; vstep++){	
					double inV = beginv+vstep*0.01;
					double distance = this.calculateAveDistance(a, d, I, inV);
					alldistances[vstep]=distance;
					System.out.println(" V:"+inV+" distance:"+distance);
					if (distance<founddistance){
						foundv=inV;
						founddistance = distance;
					}
				}
			
		System.out.println(" Found V:"+foundv+" distance:"+founddistance);
	
	}
	public void fit()
	{
		double totV = 0;
		for(int i = 0; i < theta.size()&&i<yvals.size(); i++)
		{
			double addition = solveYoung(yvals.get(i), theta.get(i));
			totV = totV + addition;
			//System.out.println(addition);
			//System.out.println(totV);
		}
		//System.out.println("............................"+totV);
		meanV = totV/(theta.size());
		double totDiffVSquare = 0;
		for(int i = 0; i < theta.size()&&i<yvals.size(); i++)
		{
			double diff = solveYoung(yvals.get(i), theta.get(i))-meanV;
			totDiffVSquare = totDiffVSquare + Math.pow(diff,2);
		}
		stdevV = Math.sqrt(totDiffVSquare/(theta.size()-1));
	}
	
	/**
	 * returns V value
	 * @return
	 */
	public double solveYoung(double y, double theta)
	{
		System.out.println(theta);
		//System.out.println(y);
		double inside1 = Math.PI*a*theta/lambda;
		//System.out.println(inside1);
		double part1 = Math.sin(inside1)/inside1;
		//System.out.println(part1);
		double part2 = (y/I)/(Math.pow(part1, 2))-1;
		//System.out.println(part2);
		double inside2 = 2*Math.PI*d*theta/lambda;
		//System.out.println(inside2);
		double part3 = Math.cos(inside2);
		//System.out.println(part3);
		double v = part2/part3;
		System.out.println(v);
		System.out.println("");
		if (theta==0)
		{
			return 1;
		}
		return v;
	}
/*	
	public ArrayList<Integer> sample()
	{
		Random r = new Random(theta.length-1);
		ArrayList<Integer> sample = new ArrayList<Integer>();
		while (sample.size() < 4)
		{
			int next = r.nextInt();
			boolean good = true;
			for (int i = 0; i < sample.size(); i++)
			{
				if (next==sample.get(i))
				{
					good = false;
				}
			}
			
			if (good)
			{
				Integer tobeadded = new Integer(next);
				sample.add(tobeadded);
			}
		}
		
		//debugging
		if (sample.size()!=4)
		{
			System.out.println("Not 4 elements in sample");
		}
		
		return sample;
	}*/
}

/*
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;

/**
 * This is the fitting method that takes values for a and d
 * and finds a fitting parameter V.  Ideally, V is one
 * @author winst13
 *
 */
/*public class SampleFit 
{
	/**
	 * This value of lambda was calculated from the fact that the
	 * electron energy coming out of the TEM was 80keV
	 */
/*	public double lambda = 0.000000000004336;
	
	/**
	 * These values of a and d were 
	 */
/*	public double a = 0.0000000125;
	public double d = 0.00000013;
	
	/**
	 * Since intensity has arbitrary units, I is selected to make the
	 * maximum intensity of the data equal to 1
	 */
/*	public double I = 2;
	
	/**
	 * The name of the files with the data
	 */
	//public static String expfilename = "1-Data.csv";
/*	public static String expfilename = "1-Theoretical.csv";
	
	/*public double founda = 0;
	public double foundd = 0;
	public double foundI = 0;*/
	
	/**
	 * This is where the results come out
	 */
/*	public double meanV = 0;
	public double stdevV = 0;
	
	/**
	 * The data goes into these ArrayLists
	 */
/*	public ArrayList<Double> theta = new ArrayList<Double>();
	public ArrayList<Double> yvals = new ArrayList<Double>();
	
	/*public static final int A_ITERATIONS = 200;
	public static final int D_ITERATIONS = 200;
	public static final int V_ITERATIONS = 200;
	public static final int I_ITERATIONS = 200;
	public static final double A_ERROR = 0.00000000007;
	public static final double D_ERROR = 0.000000000754;
	public static final double V_ERROR = 0.005;
	public static final double I_ERROR = 0.005;*/
	
	/**
	 * This is the main method that executes the fitting function
	 * and prints the results
	 * @param args
	 */
/*	public static void main(String args[])
	{
		try
		{
			SampleFit myfit = new SampleFit(expfilename);
			System.out.println("Mean of V:  "+myfit.meanV);
			System.out.println("Stdev of V:  "+myfit.stdevV);
			//System.out.println("A:  "+myfit.founda);
			//System.out.println("D:  "+myfit.foundd);
			//System.out.println("I:  "+myfit.foundI);
			System.out.println("");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This constructor reads the data and fits it
	 * @param expfilename
	 * @throws IOException
	 */
/*	public SampleFit(String expfilename) throws IOException
	{
		CSVReader reader = new CSVReader(new FileReader(expfilename));
	    String[] nextLine;
	    
	    while ((nextLine = reader.readNext()) != null) 
		{
	    	try {// nextLine[] is an array of values from the line
	    	Double addition = Double.parseDouble(nextLine[2]);
	    	theta.add(addition);		
	    	System.out.println(addition);
	    	Double nextyval = Double.parseDouble(nextLine[3]);
	    	yvals.add(nextyval);
	    	//System.out.println(nextyval);
	    	this.calculateDistance(a, d, 1, I, addition, nextyval);
	    	}
	    	catch (Exception e){}
		}
	    reader.close();
	    
	    /**
	     * The fitting method called here appears later
	     */
	    //fitAD();
/*	    fit();
	}
	
	/**
	 * This is the program that we used to find an approximate a value
	 * and d value
	 * @param a
	 * @param d
	 * @param I
	 * @param V
	 * @return
	 */
	/*public void fitAD()
	{
		double currenta = 0.00000001406;
		double currentd = 0.00000015073;
		double currentI = 1;
		double founddistance = Double.MAX_VALUE;
		for (int i = 0; i<A_ITERATIONS; i++)
		{
			if (i%2 == 0)
			{
				currenta = currenta-i*A_ERROR;
			}
			else
			{
				currenta = currenta+i*A_ERROR;
			}
			for (int j = 0; j<D_ITERATIONS; j++)
			{
				if (i%2 == 0)
				{
					currentd = currentd-j*D_ERROR;
				}
				else
				{
					currentd = currentd+j*D_ERROR;
				}
				for (int k = 0; k<I_ITERATIONS; k++)
				{
					if (i%2 == 0)
					{
						currentI = currentI-k*I_ERROR;
					}
					else
					{
						currentI = currentI+k*I_ERROR;
					}
				}
				
				fit();
				
				double distance = calculateAveDistance(currenta,currentd,currentI,meanV);
				if (distance<founddistance)
				{
					founda=currenta;
					foundd=currentd;
					foundI=currentI;
					founddistance = distance;
				}
			}
		}
	}*/
	
	/**
	 * This method calculates the average distance squared.
	 * It is utilized in the fitting method in order to minimize
	 * the deviation from the theoretical predictions
	 * @param a
	 * @param d
	 * @param I
	 * @param V
	 * @return
	 */
/*	public double calculateAveDistance(double a, double d, double I, double V)
	{
		double totalDist = 0;
		for (int i = 0; i<theta.size(); i++)
		{
			totalDist = totalDist + calculateDistance(a, d, I, V, theta.get(i), yvals.get(i));
		}
		double ave = totalDist/theta.size();
		return ave;
	}
	
	/**
	 * This program calculates the distance squared for a given
	 * point.  It is used in the method that calculates the average
	 * distance squared
	 * @param a
	 * @param d
	 * @param V
	 * @param I
	 * @param theta
	 * @param y
	 * @return
	 */
/*	public double calculateDistance(double a, double d, double V, double I, double theta, double y)
	{
		double retval = 0;
		double firstterm = Math.pow((Math.sin(Math.PI*a*Math.sin(theta)/lambda)/
				(Math.PI*a*Math.sin(theta)/lambda)), 2);
		double secondterm = 1+V*Math.cos(2*Math.PI*d*theta/lambda);
		retval=I*firstterm*secondterm;
		if ( Double.isNaN(retval))
		{
			return 0;
		}
		System.out.println("calculating distance: input y="+ y + " calculated y:"+ retval);
		return Math.pow(y-retval,2);
	}
	
	/**
	 * This method fits for V given a and d values.  It does so by taking 
	 * each data point and plugging it into Young's equation.  This returns
	 * a V value.  All the V values are then averaged
	 */
/*	public void fit()
	{
		double totV = 0;
		for(int i = 0; i < theta.size()&&i<yvals.size(); i++)
		{
			double addition = solveYoung(yvals.get(i), theta.get(i));
			totV = totV + addition;
			//System.out.println(addition);
			//System.out.println(totV);
		}
		//System.out.println("............................"+totV);
		meanV = totV/(theta.size());
		double totDiffVSquare = 0;
		for(int i = 0; i < theta.size()&&i<yvals.size(); i++)
		{
			double diff = solveYoung(yvals.get(i), theta.get(i))-meanV;
			totDiffVSquare = totDiffVSquare + Math.pow(diff,2);
		}
		stdevV = Math.sqrt(totDiffVSquare/(theta.size()-1));
	}
	
	/**
	 * This method uses Young's equation to find V given an a, d, I, y, and theta
	 * @return
	 */
/*	public double solveYoung(double y, double theta)
	{
		System.out.println(theta);
		//System.out.println(y);
		double inside1 = Math.PI*a*theta/lambda;
		//System.out.println(inside1);
		double part1 = Math.sin(inside1)/inside1;
		//System.out.println(part1);
		double part2 = (y/I)/(Math.pow(part1, 2))-1;
		//System.out.println(part2);
		double inside2 = 2*Math.PI*d*theta/lambda;
		//System.out.println(inside2);
		double part3 = Math.cos(inside2);
		//System.out.println(part3);
		double v = part2/part3;
		System.out.println(v);
		System.out.println("");
		if (theta==0)
		{
			return 1;
		}
		return v;
	}
	
	/**
	 * This is an artifact from an old fitting method that may
	 * be useful in the future
	 */
	/*	
	public ArrayList<Integer> sample()
	{
		Random r = new Random(theta.length-1);
		ArrayList<Integer> sample = new ArrayList<Integer>();
		while (sample.size() < 4)
		{
			int next = r.nextInt();
			boolean good = true;
			for (int i = 0; i < sample.size(); i++)
			{
				if (next==sample.get(i))
				{
					good = false;
				}
			}
			
			if (good)
			{
				Integer tobeadded = new Integer(next);
				sample.add(tobeadded);
			}
		}
		
		//debugging
		if (sample.size()!=4)
		{
			System.out.println("Not 4 elements in sample");
		}
		
		return sample;
	}*/
//}
