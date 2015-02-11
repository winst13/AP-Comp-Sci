/**
 * This class holds a static method that takes two strings and
 * finds the common prefix. The main method tests that method
 * @author winst13
 *
 */
public class CommonPrefix 
{
	/**
	 * This method is the method that takes two strings and
	 * finds the common prefix.  For example, if the inputs 
	 * to this method are "String" and "Strap", it would
	 * return "Str".  It does take into account case, so if
	 * the inputs are "String" and "strap", then the method
	 * would return "".
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String common(String s1, String s2)
	{
		char[] char1 = s1.toCharArray();
		char[] char2 = s2.toCharArray();
		String prefix = "";
		int minVal = char1.length;
		if (char1.length > char2.length)
		{
			minVal = char2.length;
		}
		
		for (int i = 0; i < minVal; i++)
		{
			if (String.valueOf(char1[i]).equals(String.valueOf(char2[i])))
			{
				prefix = prefix + char1[i];
			}
			else
			{
				break;
			}
		}
		
		return prefix;
	}
	
	/**
	 * This method tests quite a few cases
	 * for the common() method
	 * @param args
	 */
	public static void main(String args[])
	{
		System.out.println("\"\", \"Foo\": "+common("", "Foo"));
		System.out.println("\"Foo\", \"Foo\": "+common("Foo", "Foo"));
		System.out.println("\"Hello\", \"hello\": "+common("Hello", "hello"));
		System.out.println("\"Catastrophe\", \"Cat\": "+common("Catastrophe", "Cat"));
		System.out.println("\"String\", \"Strap\": "+common("String", "Strap"));
		System.out.println("\"Foo\", \"\": "+common("Foo", ""));
		System.out.println("\"Foo\", \"Food\": "+common("Foo", "Food"));
		System.out.println("\"100\", \"1100\": "+common("100", "1100"));
	}
}
