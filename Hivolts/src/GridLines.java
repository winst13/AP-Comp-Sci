import java.awt.Graphics;

public class GridLines 
{
	public int sizeofcell;
	public static final int SIZE_OF_TOP_BAR = 20;
	public int rows;
	public int columns;
	public Graphics g;
	
	/**
	 * 
	 * @param myrows
	 * @param mycolumns
	 * @param mysizeofcell
	 */
	public GridLines(int myrows, int mycolumns, int mysizeofcell)
	{
		sizeofcell = mysizeofcell;
		rows = myrows;
		columns = mycolumns;
	}
	
	/**
	 * 
	 * @param g
	 */
	public void paint(Graphics g)
	{
		//draws the vertical lines
		GridLines grid = new GridLines(Hivolt.ROWS_IN_GRID, Hivolt.COLUMNS_IN_GRID, Hivolt.SIZE_OF_CELL);
		for (int i = 1; i < grid.columns; i++)
		{
			int x = i*grid.sizeofcell;
			int y1 = SIZE_OF_TOP_BAR;
			int y2 = grid.sizeofcell*grid.rows+SIZE_OF_TOP_BAR;
			g.drawLine(x, y1, x, y2);
		}
		//draws the horizontal lines
		for (int i = 1; i < grid.rows; i++)
		{
			int x1 = 0;
			int y = i*grid.sizeofcell + SIZE_OF_TOP_BAR;
			int x2 = grid.sizeofcell*grid.columns;
			g.drawLine(x1, y, x2, y);
		}
	}	
}
