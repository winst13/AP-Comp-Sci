
/**
 * This class represents complex numbers
 * Written for homework
 * @author winst13
 */
public class Complex 
{
	private int real;
	private int imag;
	
	/**
	 * Tests the methods in the Complex class
	 * @param args
	 */
	public static void main(String args[])
	{
		System.out.println((new Complex(3,4)).abs());//should print 5.0
		System.out.println(equals(new Complex(3,4), new Complex(4,5)));//should print false
		System.out.println(equals(new Complex(3,4), new Complex(3,4)));//should print true
	}
	
	/**
	 * Creates a new complex number
	 * @param myreal
	 * @param myimag
	 */
	public Complex(int myreal, int myimag)
	{
		real = myreal;
		imag = myimag;
	}
	
	/**
	 * Returns the absolute value, or magnitude, of the complex number
	 * @return
	 */
	public double abs() 
	{
	    return Math.sqrt(real * real + imag * imag);
	}
	
	/**
	 * Tests whether two complex numbers are equal
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(Complex a, Complex b) 
	{
	    return(a.real == b.real && a.imag == b.imag);
	}
}