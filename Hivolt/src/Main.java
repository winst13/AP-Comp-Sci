import javax.swing.JFrame;


public class Main 
{
	/**
	 * This is the main method for the Hivolts project
	 * 
	 * The project contains the files Actor, ElectricFence,
	 * ElectricFenceList, GridLines, Hivolt, Main, Mho, and MhoList
	 * 
	 * The Actor class characterizes the Actor, which can move up, down,
	 * left, right, and diagonally.  The player controls an instance of
	 * the Actor with the keyboard
	 * 
	 * The ElectricFence class contains the basic info for a fence:
	 * how to draw it, the coordinates, getter/setter methods, and a
	 * function that can compare two fences
	 * 
	 * The ElectricFenceList class itself is not a list, but has an 
	 * ArrayList of Electric fences.
	 * 
	 * The Mho and MhoList classes are pretty much the same as the 
	 * ElectricFence and ElectricFenceList class, except they have different
	 * shapes and slightly different constructors.
	 * 
	 * The GridLines class has several parameters, such as cell size, number
	 * of rows, and number of columns.  It contains a method that paints the
	 * white grid
	 * 
	 * The Hivolt class puts everything together.  This is the class with the
	 * KeyListener
	 * 
	 * So far I have encountered a few different problems. First, when I was
	 * creating the "equals" method, I mixed up different and equal, which 
	 * then affected the program to give it an infinite loop.  The problem is
	 * now resolved.
	 * 
	 * In addition, I have also had some problems painting the grid to be where
	 * I want it to be.  Java automatically draws starting from the top left
	 * corner of the frame.  However, it includes the top bar, so everything is
	 * shifted up 20 pixels.  This problem is also resolved
	 * 
	 * When the project instructions call for better graphics, I took that to
	 * mean different colors, other than just black and white, as the original
	 * is.  
	 * 
	 * Controls:
	 * Q:  Up, Left
	 * W:  Up
	 * E:  Up, Right
	 * A:  Left
	 * D:  Right
	 * Z:  Down, Left
	 * X:  Down
	 * C:  Down, Right
	 * 
	 * Objects:
	 * Actor:  Controlled by the player
	 * Mho:  Follows the Actor, eliminated if 
	 * 
	 * @author Winston Wang
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Hivolt c = new Hivolt();
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}
}