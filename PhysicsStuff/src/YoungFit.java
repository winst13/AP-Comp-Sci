import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;


public class YoungFit
{
	public static int COL_NUMBER = 7;
	public static int ROW_NUMBER = 353;
	public static String expfilename = "/Users/winst13/Documents/Physics Project/Data/Test.csv";
	public double[] theta = new double[ROW_NUMBER];
	public double[] yvalue = new double[ROW_NUMBER];
	public double[] cola = new double[ROW_NUMBER];
	public double[] colb = new double[ROW_NUMBER];
	public double[] cole = new double[ROW_NUMBER];
	public double[] colf = new double[ROW_NUMBER];
	public double[] colg = new double[ROW_NUMBER];
	public double lambda = 0.000000000004336;
	public static final int A_ITERATIONS = 200;
	public static final int D_ITERATIONS = 200;
	public static final int V_ITERATIONS = 200;
	public static final double A_ERROR = 0.1;
	public static final double D_ERROR = 0.2;
	public static final double V_ERROR = 0.01;

	/**
	 * Main method that executes the program
	 * @param args
	 */	
	public static void main(String[] args) 
	{
		YoungFit myfitting = new YoungFit();
		try 
		{
			oldFit(myfitting);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		
	/**
	 * This is the old way of fitting
	 * @param myfitting
	 */
	public static void oldFit(YoungFit myfitting)
	{
		try 
		{
			myfitting.importWithReader();
			double begina = 25;
			double begind = 140;
			double beginv = 0.3;
			double[][][] alldistances = new double[100][100][100];//is this necessary?
			double founda = 0;
			double foundd = 0;
			double foundv = 0;
			double founddistance = Double.MAX_VALUE;
			for (int astep = 0; astep<100; astep++)
			{
				for (int dstep = 0; dstep<100; dstep++)
				{
					for (int vstep=0; vstep<80; vstep++)
					{	
						double ina = begina+astep*0.1;
						double ind = begind+dstep*0.2;
						double inV = beginv+vstep*0.01;
						double distance = myfitting.calculateDistance
							(myfitting.calculateAllTheoryV(ina, ind, inV), myfitting.yvalue);
						alldistances[astep][dstep][vstep]=distance;
						System.out.println("a:"+ina+" d:"+ ind + " V:"+inV+" distance:"+distance);
						if (distance<founddistance)
						{
							founda=ina;
							foundd=ind;
							foundv=inV;
							founddistance = distance;
						}
					}
				}
			}
			System.out.println("Found a:"+founda+" found d:"+ foundd + " Found V:"+foundv+
					" distance:"+founddistance);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void altOldFit(YoungFit myfitting)
	{
		try 
		{
			myfitting.importWithReader();
			double currenta = 25;
			double currentd = 140;
			double currentV = 0.3;
			double founda = 0;
			double foundd = 0;
			double foundv = 0;
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
					for (int k=0; k<V_ITERATIONS; k++)
					{	
						if (i%2 == 0)
						{
							currentV = currentV-k*V_ERROR;
						}
						else
						{
							currentV = currentV+k*V_ERROR;
						}
						double distance = myfitting.calculateDistance(myfitting.calculateAllTheoryV
								(currenta, currentd, currentV), myfitting.yvalue);
						System.out.println("a:"+currenta+" d:"+ currentd + " V:"+currentV+
								" distance:"+distance);
						if (distance<founddistance)
						{
							founda=currenta;
							foundd=currentd;
							foundv=currentV;
							founddistance = distance;
						}
					}
				}
			}
			System.out.println("Found a:"+founda+" found d:"+ foundd + 
				" Found V:"+foundv+" distance:"+founddistance);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Another old way of fitting
	 * @param args
	 */
	public static void altMain(String[] args) 
	{
		YoungFit myfitting = new YoungFit();
		altOldFit(myfitting);
	}
	
	/**
	 * 
	 * @param ina
	 * @param ind
	 * @param inV
	 * @return
	 */
	public double[] calculateAllTheoryV(double ina, double ind, double inV) 
	{
		double[] retval = new double[ROW_NUMBER];
		for (int i=0; i<ROW_NUMBER; i++)
		{
			//System.out.println("K:"+colg[i]);
			retval[i] = calculateTheoryV(ina, ind, inV, colg[i]);
		}
		return retval;
	}
		
	/**
	 * Imports the data
		* @throws IOException
	 */
	public void importWithReader() throws IOException 
	{
		CSVReader reader = new CSVReader(new FileReader(expfilename));
	    String [] nextLine;
	    int linnum = 0;
	    while ((nextLine = reader.readNext()) != null) 
		{
	    	// nextLine[] is an array of values from the line
	    	cola[linnum]= Double.parseDouble(nextLine[0]);
	    	colb[linnum]= Double.parseDouble(nextLine[1]);
	    	double atheta = Double.parseDouble(nextLine[2]);
	    	theta[linnum]= atheta;		    		
	    	yvalue[linnum]= Double.parseDouble(nextLine[3]);
	    	cole[linnum]= Double.parseDouble(nextLine[4]);
	    	//colf[linnum]= Double.parseDouble(nextLine[5]);
	    	//colg[linnum]= Double.parseDouble(nextLine[6]);
	    	colg[linnum]=Math.sin(atheta)/lambda;
	    	System.out.println("K:"+colg[linnum]+" yvalue:"+yvalue[linnum]);
	    	linnum++;
		}
	    reader.close();
	}
	
	public static void importWithReader(YoungFit myfit) throws IOException 
	{
		CSVReader reader = new CSVReader(new FileReader(expfilename));
	    String [] nextLine;
	    int linnum = 0;
	    while ((nextLine = reader.readNext()) != null) 
		{
	    	// nextLine[] is an array of values from the line
	    	myfit.cola[linnum]= Double.parseDouble(nextLine[0]);
	    	myfit.colb[linnum]= Double.parseDouble(nextLine[1]);
	    	double atheta = Double.parseDouble(nextLine[2]);
	    	myfit.theta[linnum]= atheta;		    		
	    	myfit.yvalue[linnum]= Double.parseDouble(nextLine[3]);
	    	myfit.cole[linnum]= Double.parseDouble(nextLine[4]);
	    	//colf[linnum]= Double.parseDouble(nextLine[5]);
	    	//colg[linnum]= Double.parseDouble(nextLine[6]);
	    	myfit.colg[linnum]=Math.sin(atheta)/myfit.lambda;
	    	System.out.println("K:"+myfit.colg[linnum]+" yvalue:"+myfit.yvalue[linnum]);
	    	linnum++;
		}
	    reader.close();
	}
		
	public double calculateTheoryV(double ina, double ind, double inV, double inK)
	{
		double retval = 0;
		double firstterm = Math.pow(Math.sin(Math.PI*ina*0.000000009*inK)/
				(Math.PI*ina*0.000000009*inK), 2);
		double secondterm = 1+inV*Math.cos(2*Math.PI*ind*0.000000009*inK);
		retval=firstterm*secondterm;
		if ( Double.isNaN(retval))
		{
			//System.out.println("NaN resulted from:"+ina+" "+ind+" "+inV+" "+inK);
			return 0;
		}
		//NOT SURE WHY 10^6 order difference with experimental data.
		return retval;
	}
		
	public double calculateDistance(double[] theoryY, double[] expY)
	{
		double retval=0;
		for (int i=0; i<theoryY.length; i++)
		{
			//System.out.println("theory:" + theoryY[i] + " experiment:"+ expY[i]);
			retval = retval+Math.pow((theoryY[i]-expY[i]),2);
		}
		return retval;
	}
}
