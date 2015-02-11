import java.math.BigInteger;

/**
 * This is the Rational class for Exercise 4 of Chapter 15
 * It contains all object methods
 * @author winst13
 *
 */
public class Rational 
{
	private int numerator;
	private int denominator;
	
	/**
	 * Tests the class
	 * @param args
	 */
	public static void main(String args[])
	{
		Rational r1 = new Rational();
		r1.setnumerator(9);
		r1.setdenominator(12);
		r1.printRational();
		r1.negate();
		r1.printRational();
		r1.negate();
		r1.invert();
		r1.printRational();
		System.out.println(r1.toDouble());
		r1.reduce().printRational();
		Rational r2 = new Rational(5,6);
		r1.add(r2).printRational();
	}
	
	/**
	 * Constructor that creates a rational object with numerator 0 and denominator 1
	 */
	public Rational()
	{
		numerator = 0;
		denominator = 1;
	}
	
	public Rational(int mynumerator, int mydenominator)
	{
		numerator = mynumerator;
		denominator = mydenominator;
	}
	
	public void printRational()
	{
		if(this.denominator==1)
		System.out.println(this.numerator);
		else
		System.out.println(this.numerator+"/"+this.denominator);
	}
	
	public void negate()
	{
		numerator = -1*numerator;
	}
	
	public void invert()
	{
		int newnumerator = denominator;
		int newdenominator = numerator;
		numerator = newnumerator;
		denominator = newdenominator;
	}
	
	public double toDouble()
	{
		double decimalnum = (double) numerator;
		double decimalden = (double) denominator;
		double result = decimalnum/decimalden;
		return result;
	}
	
	public Rational reduce()
	{
		BigInteger b1 = new BigInteger(""+this.numerator);
	    BigInteger b2 = new BigInteger(""+this.denominator);
	    BigInteger gcd = b1.gcd(b2);
	    int greatestcd = gcd.intValue();
	    Rational result = new Rational(this.numerator/greatestcd, this.denominator/greatestcd);
	    return result;
	}
	
	public Rational add(Rational r1)
	{
		int num = r1.numerator*this.denominator+this.numerator*r1.denominator;
		int den = r1.denominator*this.denominator;
		Rational sum = new Rational(num,den);
		return sum.reduce();
	}
	
	public void setnumerator(int newnumerator)
	{
		numerator = newnumerator;
	}
	
	public void setdenominator(int newdenominator)
	{
		denominator = newdenominator;
	}
	
}
