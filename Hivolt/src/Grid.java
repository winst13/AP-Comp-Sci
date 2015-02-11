import java.awt.Graphics;


public class Grid 
{
	public int[][] grid;

	public Grid(int myrows, int mycolumns)
	{
		grid = new int[myrows][mycolumns];
	}
	
	public void addPerimeterFences()
	{
		for (int i = 0; i < Hivolt.COLUMNS_IN_GRID; i++)
		{
			if (i==0 || i == (Hivolt.COLUMNS_IN_GRID-1))
			{
				for (int j = 0; j < Hivolt.ROWS_IN_GRID; j++)
				{
					grid[i][j] = 1;
				}
			}
			else
			{
				grid[i][0] = 1;
				grid[i][Hivolt.ROWS_IN_GRID] = 1;
			}
		}
	}
	
	public void addInteriorFences()
	{
		
	}
	
	public void addMhos()
	{
		
	}
	
	public void addActor()
	{
		
	}

	public void paint(Graphics g) 
	{
		//draws the vertical lines
		for (int i = 1; i < Hivolt.COLUMNS_IN_GRID; i++)
		{
			int x = i*Hivolt.SIZE_OF_CELL;
			int y1 = Hivolt.SIZE_OF_TOP_BAR;
			int y2 = Hivolt.SIZE_OF_CELL*Hivolt.ROWS_IN_GRID+Hivolt.SIZE_OF_TOP_BAR;
			g.drawLine(x, y1, x, y2);
		}
		//draws the horizontal lines
		for (int i = 1; i < Hivolt.ROWS_IN_GRID; i++)
		{
			int x1 = 0;
			int y = i*Hivolt.SIZE_OF_CELL + Hivolt.SIZE_OF_TOP_BAR;
			int x2 = Hivolt.SIZE_OF_CELL*Hivolt.COLUMNS_IN_GRID;
			g.drawLine(x1, y, x2, y);
		}
	}
}
