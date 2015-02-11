import java.util.ArrayList;


public class Foo
{	
	public static void main(String args[])
	{
		//Creates an adjacency matrix that is the representation
		//of a graph
		int[][] adjacent = new int[5][5];
		
		for (int i = 0; i < adjacent.length; i++)
		{
			for (int j = 0; j < adjacent[i].length; j++)
			{
				adjacent[i][j] = 0;
			}
		}
		
		adjacent[1][0] = 1;
		adjacent[2][1] = 1;
		adjacent[1][3] = 1;
		adjacent[4][0] = 1;
		adjacent[4][2] = 1;
		adjacent[0][3] = 1;
	}
}
