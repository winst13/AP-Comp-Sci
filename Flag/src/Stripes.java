import java.awt.*;

/*
This class codes for the stripes.  There is a small rounding error
*/
public class Stripes
{
	private final static int topbarheight = 20;
	
	/*
	 * Paints the stripes in the flag
	 * @param Graphics g
	 * @param int nofstripes
	 * @param int width 
	 * @param int height
	 * @return void
	 */
	public static void paintStripes(Graphics g, int nofstripes, int width, int height)
	{
		for (int i=1; i<= nofstripes; i++)
		{
			if ((i & 1) == 0) //when the stripe number is even
			{
				drawWhiteStripes(g, height, nofstripes, i, width);
			}
			else //when the stripe number is odd
			{
				drawRedStripe(g, height, nofstripes, i, width);
			}
		}
	}
	
	/*
	 * Paints a red stripe
	 * @param Graphics g
	 * @param int myheight
	 * @param int mynofstripes
	 * @param int myi
	 * @param int mywidth
	 */
	public static void drawRedStripe(Graphics g, int myheight, int mynofstripes, int myi, int mywidth)
	{
		g.setColor(Color.RED); //for the red stripes
		g.fillRect(0, Math.round((myheight-topbarheight)/mynofstripes*(myi-1)+20), Math.round(mywidth), Math.round((myheight-20)/mynofstripes));
	}
	
	/*
	 * Paints a white stripe
	 * @param Graphics g
	 * @param int myheight
	 * @param int mynofstripes
	 * @param int myi
	 * @param int mywidth
	 */
	public static void drawWhiteStripes(Graphics g, int myheight, int mynofstripes, int myi, int mywidth)
	{
		g.setColor(Color.WHITE);//for the white stripes
		g.fillRect(0, Math.round((myheight-topbarheight)/mynofstripes*(myi-1)+20), Math.round(mywidth), Math.round((myheight-20)/mynofstripes));
	}
}