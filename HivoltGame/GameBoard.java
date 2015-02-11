import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;


public class GameBoard extends JPanel implements KeyListener
{
	private static final long serialVersionUID = 1L;
	private GamePiece[][] grid;
	private ArrayList<GamePiece> fencelist = new ArrayList<GamePiece>();
	private ArrayList<Mho> mholist = new ArrayList<Mho>();
	private Actor runner;
	private HivoltGame parentgame;

	/**
	 * This is the constructor for a new GameBoard.  It has a couple criteria, the 
	 * number of rows, number of columns, and the HivoltGame to which it belongs.
	 * @param myrows  the number of rows in the GameBoard
	 * @param mycolumns  the number of columns in the GameBoard
	 * @param myparent  the parent HivoltGame
	 */
	public GameBoard(int myrows, int mycolumns, HivoltGame myparent)
	{
		parentgame=myparent;
		grid = new GamePiece[myrows][mycolumns];
		setLayout(new GridLayout(myrows, mycolumns));
		setBounds(0, 0, 600, 600);
		for (int i = 0; i < 144; i++) 
		{
			  JPanel square = new JPanel(new BorderLayout());
			  square.setBackground(new Color(0,0,0,64));
			  this.add(square);
		}
		//System.out.println("done setting up board\n"); //for testing purposes
	}
	
	/**
	 * This method returns the x coordinate of the actor of the GameBoard
	 * @return
	 */
	public int getActorX()
	{
		return runner.getx();
	}
	
	/**
	 * This method returns the y coordinate of the actor of the GameBoard
	 * @return
	 */
	public int getActorY()
	{
		return runner.gety();
	}

	@Override
	/**
	 * This method paints the actual grid
	 */
    public void paintComponent(Graphics g) 
	{
		//draws the vertical lines
		for (int i = 1; i < HivoltGame.COLUMNS_IN_GRID; i++)
		{
			int x = i*HivoltGame.SIZE_OF_CELL;
			int y1 = HivoltGame.SIZE_OF_TOP_BAR;
			int y2 = HivoltGame.SIZE_OF_CELL*HivoltGame.ROWS_IN_GRID+HivoltGame.SIZE_OF_TOP_BAR;
			g.drawLine(x, y1, x, y2);
		}
		//draws the horizontal lines
		for (int i = 1; i < HivoltGame.ROWS_IN_GRID; i++)
		{
			int x1 = 0;
			int y = i*HivoltGame.SIZE_OF_CELL + HivoltGame.SIZE_OF_TOP_BAR;
			int x2 = HivoltGame.SIZE_OF_CELL*HivoltGame.COLUMNS_IN_GRID;
			g.drawLine(x1, y, x2, y);
		}
	}
	
	/**
	 * This method adds a GamePiece to the GameBoard.  If the piece
	 * is a fence, then it will be added to the fencelist.  If it is
	 * a mho, it will be added to the mholist.  However, if the new 
	 * object is an actor, it will replace the old actor
	 * @param piece
	 */
	public void add (GamePiece piece)
	{
		if (piece.isfence()) 
		{
			fencelist.add(piece);			
		}
		else if (piece.ismho()) 
		{
			mholist.add((Mho)piece);
		}
		else if (piece.isactor()) 
		{
			runner=(Actor) piece;
		}
		int a=piece.getx();
		int b=piece.gety();
		grid[a][b]=piece;
		JPanel panel = (JPanel)getComponent(12*b+a);
		panel.add(piece);
	}
	
	/**
	 * This method removes a specific GamePiece.  It will only
	 * remove a fence or mho, but not an actor
	 * @param piece
	 */
	public void remove (GamePiece piece)
	{
		if (piece!=null)
		{
			if (piece.isfence()) 
			{
				fencelist.remove(piece);
			}
			else if (piece.ismho()) 
			{
				mholist.remove(piece);
			}
			int a=piece.getx();
			int b=piece.gety();
			grid[a][b]=null;
			JPanel panel = (JPanel)getComponent(12*b+a);
			panel.removeAll();
		}
	}
	
	/**
	 * This method moves any GamePiece to a new location, determined
	 * by the parameter piece.  The oldx and oldy parameters are the
	 * coordinates of the old GamePiece, before the move
	 * @param oldx
	 * @param oldy
	 * @param piece
	 */
	public void move (int oldx, int oldy, GamePiece piece)
	{
		JPanel panel = (JPanel)getComponent(12*oldy+oldx);
		panel.removeAll();
		grid[oldx][oldy]=null;
		int a=piece.getx();
		int b=piece.gety();
		grid[a][b]=piece;
		JPanel panel2 = (JPanel)getComponent(12*b+a);
		panel2.add(piece);	
	}
	
	/**
	 * This method returns the GamePiece that is located at the
	 * coordinates (a,b)
	 * @param a
	 * @param b
	 * @return
	 */
	public GamePiece getpiece(int a, int b) 
	{
		return grid[a][b];
	}

	@Override
	/**
	 * This method detects which key has been pressed.  The controls
	 * are described in the project description in the HivoltGame class.
	 * If the player jumps, the player gets another turn.  If the player
	 * moves onto a fence or mho, this method will set the status to 
	 * the player has died (either 1 or 2)
	 */
	public void keyPressed(KeyEvent e) 
	{
		char key=e.getKeyChar();
		//System.out.println("keypressed "+key); //for testing purposes
		if (key == ' '||key == 's')
		{
			parentgame.setStatus(runner.jump());
		}
		switch (key)
		{
			case('Q'): case ('q'): 
			{
				int status = runner.moveUpLeft();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
			case('W'): case('w'): 
			{
				int status = runner.moveUp();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
			case('E'): case ('e'): 
			{
				int status = runner.moveUpRight();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
			case('A'): case ('a'): 
			{
				int status = runner.moveLeft();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
			case('D'): case ('d'): 
			{
				int status = runner.moveRight();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
			case('Z'): case ('z'): 
			{
				int status = runner.moveDownLeft();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
			case('X'): case ('x'): 
			{
				int status = runner.moveDown();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
			case('C'): case ('c'): 
			{
				int status = runner.moveDownRight();
				parentgame.setStatus(status);
				if (status == 1)
				{
					break;
				}
				mhomove();
				break;
			}
		}
	}
	
	/**
	 * This method calls another method that moves the mhos.  
	 * It also contains the win condition, which is when there are
	 * no mhos left.  
	 */
	public void mhomove()
	{
		int mhomovestatus = 1;
		
		for (int i=0; i<mholist.size(); i++)
		{
			Mho oneMho = mholist.get(i);
			mhomovestatus = oneMho.calculatemove();	
			if (mhomovestatus==3)
			{ 
				parentgame.setStatus(2);
				break;
			}
		}
		
		if (mholist.size()==0)//no mhos left
		{
			parentgame.setStatus(3);//win!
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{

	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{

	}
}
