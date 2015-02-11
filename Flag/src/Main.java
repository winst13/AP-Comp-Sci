import javax.swing.JFrame;

/*
The main class creates the flag and draws it on a JFrame
In order to create this program, I first programmed everything in terms of a single
variable, myheight.  After that program could draw a satisfactory U.S. flag, I programmed
the Flag class to implement ComponentListener, which then allowed me to detect changes in 
frame size.  The flag scales by either dragging the bottom line or the corner, but not the
side lines.  The frame will also stick to the sides of the flag, so there will only be a
minimal amount of background.  There is some discrepancy between the blue and the stripes
due to rounding.  Also, for extremely small frame sizes, the stripes become too long. 
However, at all other sizes, the flag is proportional and satisfies all requirements
*/
public class Main 
{
	public static void main(String[] args) 
	{
		Flag c = new Flag(500); //Creates an new flag object, which draws itself
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}
}
