import java.awt.*;

/*
This class codes for one star that is scalable
*/
public class Star
{
	/*
	 * Draws one star
	 * @param Graphics g
	 * @param int startx
	 * @param int starty
	 * @param double scalefactor
	 * @return void
	 */
	public static void paintStar(Graphics g, int startx, int starty, double scalefactor)
	{
		//so many numbers... they are the points calculated
		int[] xarray = new int[10];
		int[] yarray = new int[10];
		xarray[0] = startx;
		yarray[0] = (int)(starty-1*scalefactor);
		xarray[9] = (int)(startx-.587785252292*scalefactor*.382);//the number .382 was found online
		yarray[9] = (int)(starty-.809016994375*scalefactor*.382);//.382 makes it look good
		xarray[2] = (int)(startx+.951056516295*scalefactor);
		yarray[2] = (int)(starty-.309016994375*scalefactor);
		xarray[7] = (int)(startx-.951056516295*scalefactor*.382);
		yarray[7] = (int)(starty+.309016994375*scalefactor*.382);
		xarray[4] = (int)(startx+.587785252292*scalefactor);
		yarray[4] = (int)(starty+.809016994375*scalefactor);
		xarray[5] = startx;
		yarray[5] = (int)(starty+1*scalefactor*.382);
		xarray[6] = (int)(startx-.587785252292*scalefactor);
		yarray[6] = (int)(starty+.809016994375*scalefactor);
		xarray[3] = (int)(startx+.951056516295*scalefactor*.382);
		yarray[3] = (int)(starty+.309016994375*scalefactor*.382);
		xarray[8] = (int)(startx-.951056516295*scalefactor);
		yarray[8] = (int)(starty-.309016994375*scalefactor);
		xarray[1] = (int)(startx+.587785252292*scalefactor*.382);
		yarray[1] = (int)(starty-.809016994375*scalefactor*.382);
		
		Polygon starpoints = new Polygon(xarray, yarray, 10);
		
		g.setColor(Color.WHITE);
		g.fillPolygon(starpoints);
	}
	
	/*
	 * Paints all the stars, using paintStar
	 * @param Graphics g
	 * @param int height
	 * return void
	 */
	public static void paintStarRows(Graphics g, int height)
	{
		for (int i = 1; i <= 9; i++)//9 rows of stars
		{
			if ((i & 1) == 0)//row number is even
			{
				for (int j = 1; j <= 5; j++)//5 stars per even row
				{
					Star.paintStar(g, (int)(.126*(height-20)*j), (int)(20+.054*(height-20)*i), (int)((height-20)*.0308));
				}
			}
			else  //row number is odd
			{
				for (int j = 1; j <= 6; j++)//6 stars per odd row
				{
					Star.paintStar(g, (int)(.126*(height-20)*j-.063*(height-20)), (int)(20+.054*(height-20)*i), (int)((height-20)*.0308));
				}
			}
		}
	}
}