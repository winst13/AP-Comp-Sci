import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



@SuppressWarnings("serial")
/**
 * 
 * This project is an applet, and therefore has no main method. 
 * It should be run as such, with the frame size 800x600.  I
 * tested the program using Eclipse's AppletViewer.  
 * 
 * The project contains the files Actor, ElectricFence,
 * GameBoard, GamePiece, HivoltGame, and Mho.  It also contains images,
 * gameover.jpg, hivolt.jpg, cookie.png, and monstor.jpg.
 * 
 * The Actor class characterizes the Actor, which can move up, down,
 * left, right, and diagonally.  The player controls an instance of
 * the Actor with the keyboard.  Actor extends GamePiece
 * 
 * The ElectricFence class contains the basic info for a fence:
 * how to draw it, the coordinates, getter/setter methods, and a
 * function that can compare two fences.  ElectricFences cannot
 * move
 * 
 * The Mho class characterizes a Mho, which can also move up, down, 
 * left, right, and diagonally.  The Mhos move towards the actor when
 * possible, and if they collide with anything except the player, that
 * instance of the Mho will be eliminated
 * 
 * The GameBoard class contains the information for the grid, as well
 * as the location of all the pieces
 * 
 * The GamePiece class is a superclass which contains the Actor, ElectricFence
 * and Mho classes.  It describes an object that can exist on the game
 * board. 
 * 
 * The HivoltGame class puts everything together and is the class that
 * holds the information for the applet.
 * 
 * So far I have encountered a few different problems. First, getting the grid
 * and the buttons to draw correctly was extremely hard.  LayoutManager is
 * not very easy to use.  However, after much trial and error, I was able to 
 * get the grid to draw correctly, as well as have logical placements for the
 * buttons "Play Again" and "Quit Game".  
 * 
 * In addition, I have had some problems painting the grid to be where I
 * want it to be.  Java automatically draws starting from the top left corner 
 * of the frame.  However, it includes the top bar, so everything is shifted 
 * up 20 pixels.  This problem is also resolved
 * 
 * Moreover, there was a problem with playing again.  As soon as the first
 * move of the second game was made, no matter where the actor moved, the
 * program would go straight to the game over page.  I resolved this
 * problem by removing the KeyListener as well as everything else.
 * 
 * Next, I had a lot of trouble getting the keyListener to work.  I tried many things 
 * and finally putting the requesFocus() in while loop worked.
 * 
 * Furthermore, there were some problems getting the mhos to move correctly.
 * It was extremely hard to troubleshoot; however, I was able to make the mhos move
 * when they should.  There are still some anomalies, where the mho will not move
 * even though it should move into a fence.
 * 
 * When the project instructions call for better graphics, I took that to
 * mean that the icons are not pixelated.  In addition, the project instructions say
 * nothing about the case in which the winning move causes the player to die.
 * I designed the game so that the player would die, rather than win.  In addition, I
 * interpreted the jump method to mean tha the mhos do not move after each jump
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
 * S or " ":  Jump
 * 
 * Objects:
 * Actor:  Controlled by the player
 * Mho:  Follows the Actor, eliminated if 
 * 
 * @author Winston Wang
 *
 */
public class HivoltGame extends JApplet implements Runnable, ActionListener
{
	public static final int ROWS_IN_GRID = 12;
	public static final int COLUMNS_IN_GRID = 12;
	public static final int NUMBER_OF_MHOS = 12;
	public static final int NUMBER_OF_FENCES = 20;
	public static final int SIZE_OF_CELL = 50;
	public static final int SIZE_OF_TOP_BAR = 0;
	String[]  possibleOutcome = {"Please make a move\n", "You hit the fence and died\n", "You are eaten by mho.  Game OVER\n", "YOU WIN! \n"};
	
	Thread thread = new Thread(this);
	boolean running = true;
	Random randomGenerator = new Random();
	JLayeredPane layeredPane;
	JLabel statuslabel;
	private GameBoard myboard;

	
	/**
	 * This is the game engine.  It has the rules about layout the board. 
	 * This is the constructor for a Hivolt game.  It is the same as the
	 * constructor for the superclass, JApplet.  
	 */
	public HivoltGame()
	{
		super();
	}
	
	/**
	 * This method contains the methods that set up the grid.  It 
	 * creates all the fences, the mhos, and the actor.  
	 */
	public void init() 
	{
		setSize(50*ROWS_IN_GRID, 50*COLUMNS_IN_GRID+100);
		getContentPane().setLayout(new BorderLayout());
		myboard = new GameBoard(ROWS_IN_GRID, COLUMNS_IN_GRID, this);
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.add(myboard, JLayeredPane.DEFAULT_LAYER);
		addPerimeterFences();
		addInteriorFences();
		addActor();
		addMhos();
		addKeyListener(myboard);
		statuslabel = new JLabel();
		statuslabel.setPreferredSize(new Dimension(100, 100));
		statuslabel.setFont(new Font("Arial",Font.BOLD,30));
		statuslabel.setText(possibleOutcome[0]);
		getContentPane().add(statuslabel, BorderLayout.SOUTH);
		validate();
		repaint();
	}

	/**
	 * This method starts a new thread
	 */
	public void start() 
	{
		thread.start();
	}
	
	/**
	 * This method adds the perimeter fences to the GameBoard
	 */
	public void addPerimeterFences()
	{
		for (int i = 0; i < HivoltGame.COLUMNS_IN_GRID; i++)
		{
			for (int j = 0; j < HivoltGame.ROWS_IN_GRID; j++)
			{
				if (i==0 || i==HivoltGame.COLUMNS_IN_GRID-1 || j==0 || j==HivoltGame.ROWS_IN_GRID-1){
					myboard.add(new ElectricFence(i,j, myboard));
				}
			}
		}
	}
	
	/**
	 * This program adds 20 random fences to the interior of the GameBoard
	 * (i.e. not overlapping with the perimeter)
	 */
	public void addInteriorFences()
	{
		int i=0;
		while (i<HivoltGame.NUMBER_OF_FENCES)
		{
			int a = randomGenerator.nextInt(11);
			int b = randomGenerator.nextInt(11);
			if (myboard.getpiece(a,b)==null)
			{
				myboard.add(new ElectricFence(a,b, myboard));
				i++;
			}
		}
	}
	
	/**
	 * This program adds 12 random mhos to the GameBoard.  They 
	 * will not be created on top of each other or any fences.
	 */
	public void addMhos()
	{
		int i=0;
		while (i<HivoltGame.NUMBER_OF_MHOS)
		{
			int a = randomGenerator.nextInt(11);
			int b = randomGenerator.nextInt(11);
			if (myboard.getpiece(a,b)==null)
			{
				myboard.add(new Mho(a,b, myboard));
				i++;
			}
		}
	}
	
	/**
	 * This method creates the actor in a random position on 
	 * the GameBoard.  It will not be created on top of a mho
	 * or a fence
	 */
	public void addActor()
	{
		boolean added=false;
		while(!added)
		{
			int a = randomGenerator.nextInt(11);
			int b = randomGenerator.nextInt(11);
			if (myboard.getpiece(a,b)==null)
			{
				myboard.add(new Actor(a,b, myboard));
				added=true;
			}
		}
	}
	
	@Override
	/**
	 * This method runs the applet and catches the exceptions
	 * that may occur during runtime.  
	 */
	public void run() 
	{
		while (running)
		{
			try
			{
				requestFocus();
				Thread.sleep(20);
				repaint();
			}
			catch (InterruptedException e)
			{
				System.out.println("ERROR OUT\n");
			}
		}
		//System.out.println("Game over\n"); //for testing purposes
		askToRestart();
	}
	/**
	 * This method asks the player whether they want to restart or quit
	 * after a game has been completed.  If the "Play Again" button is
	 * clicked, the game will restart.  If the "Quit" button is clicked,
	 * the applet window will close
	 */
	private void askToRestart() 
	{
		JButton newgame = new JButton("Play Again");
		newgame.addActionListener(this);
		JButton quitgame = new JButton("Quit Game");
		quitgame.addActionListener(this);
		getContentPane().removeAll();
		getContentPane().add(newgame, BorderLayout.LINE_START);
		getContentPane().add(statuslabel, BorderLayout.NORTH);
		ImageIcon gameovericon = new ImageIcon("gameover.jpg");
		JLabel gameover = new JLabel(gameovericon);
		getContentPane().add(gameover, BorderLayout.CENTER);
		getContentPane().add(quitgame, BorderLayout.LINE_END);
		this.removeKeyListener(myboard);
		validate();
		repaint();
	}
		
	/**
	 * This method changes running to false, which stops the
	 * while loop in the method run().  
	 */
	public void stop()
	{
		running=false;
	}
	
	/**
	 * This method exits the applet as well as stopping the 
	 * method run()
	 */
	public void destory()
	{
		running=false;
		System.exit(0);
	}
	
	/**
	 * This method sets the status of the game.  There are four
	 * different statuses outlined below:
	 * 0:  Continue the game (prints "Please make a move\n")
	 * 1:  Player died by electric fence (prints "You hit the fence and died\n")
	 * 2:  Player died by mho (prints "You are eaten by mho.  Game OVER\n")
	 * 3:  Player wins (prints "YOU WIN! \n")
	 * The status is displayed in the bottom.  However, on the game over screen, 
	 * this message is displayed on the top
	 * @param newstatus
	 */
	public void setStatus(int newstatus) 
	{
		statuslabel.setText(possibleOutcome[newstatus]);
		if (newstatus!=0)
		{
			stop();
		}		
	}
	
	@Override
	/**
	 * This method detects whether the player clicked "Play Again" or "Quit Game".
	 * It also runs the command that allow the player to play again or to 
	 * quit
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		//System.out.println("button was clicked:"+action);	//for testing purposes
		if (action.equalsIgnoreCase("Play Again"))
		{
			getContentPane().removeAll();
			validate();
			repaint();
			init();
			running=true;
			thread = new Thread(this);
			thread.start();
		}
		else //When the player clicks quit
		{
			destory();
		}
	}
}
