import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Hivolts
 * ---------------------------------
 * Controls:
 * QWE (upper left, up, upper right)
 * ASD (left, sit, right)
 * ZXC (bottom left, down, bottom right)
 * J (jump)
 * G (toggle grid)
 * B (ascii board)
 * 
 * Objects:
 * You (green smiley)
 * Mhos (red sad faces)
 * Fences (electric fences)
 * 
 * Board is set up with a perimeter and a set number of randomly placed fences and mhos.
 * On a jump, you are guarenteed to move onto a non-fence square (includes mhos).
 * 
 * You lose if you move onto a fence, you jump onto a mho, or a mho moves onto you.
 * 
 * After every non-jump movement, the mhos will move based off of these priorities.
 * Mhos move in the order they are stored in list, check ascii board to see order.
 * 
 * 1. if directly horizontal or vertical, will move towards you.
 * 2. moves diagonally towards you to empty square
 * 3. if horizontal distance to you is more, more vertical towards you to empty square
 * 4. if horizontal distance is less, move horizontal towards you to empty square
 * 5. repeat steps priorities 2-4 with intent on moving on fence
 * 
 * Mhos die if they move on fences.
 * After all mhos have moved, it is your turn.
 * You win when all mhos are dead.
 * 
 * @author Matthew Li
 *
 */
@SuppressWarnings("serial")
public class Hivolts extends JApplet
{	
	private int totalLoss = 0;
	private int totalWin = 0;
	private long lastDrawTime;
	private int timeBetweenDraw;
	private String score;
	private String killed;
	private String startTurn = "It is now your turn.";
	private String won = "You beat and killed all the Mhos!";
	private String lost = "You have lost.";
	private String again = "Try Again?";
	private String turns;
	private String quit = "Thanks for playing Hivolts!";
	private String author = "Made by Matthew Li";
	private String ascii;
	private String mhoName = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private int startY = 15;
	private int startX = 15;
	private Image fence;
	private Image mho;
	private Image explosion;
	private Image hero;
	private int sizeOfImg;
	private int fenceBorderX;
	private int randomFence;
	private int fenceBorderY;
	private int[] fenceLocX;
	private int[] fenceLocY;
	private int turn;
	private int previousTurn;
	private int numMhos;
	private int regMhos;
	private int specMhos;
	private int deadMhos;
	private int lastDead;
	private long lastPressProcessed;
	private boolean lose;
	private boolean win;
	private int heroX;
	private int heroY;
	private MoveListener move;
	private String asciiBoard;
	private int lastDeadIndex;
	private boolean lastAnimation;
	private Color background;
	private Color lineColor;
	private boolean grid = true;
	private Mho[] mhos;
	private Image rmho;
	private boolean reset;
	private JOptionPane board;
	
	/**
	 * Runs what needs to be done on reset and first run.
	 */
	public void init()
	{	
		//background color
		reset = true;
		background = Color.BLACK;
		lineColor = Color.GRAY;
		
		//changeable features
		fenceBorderX = 14;
		fenceBorderY = 14;
		randomFence = 8;
		regMhos = 5;
		specMhos = 0;
		timeBetweenDraw = 150;
		
		//spaces out applet interface, must be > 50
		sizeOfImg = 67;
		
		//reset variables
		numMhos = regMhos + specMhos;
		startX = sizeOfImg - 48;
		startY = sizeOfImg - 48;
		lastDeadIndex = 0;
		lastAnimation = false;
		asciiBoard = "";
		turn = 0;
		previousTurn = 0;
		deadMhos = 0;
		lastDead = 0;
		lastDrawTime = 0;
		lastPressProcessed = 0;
		lose = false;
		win = false;
		
		//adds key listener
		move = new MoveListener();
		addKeyListener(move);
		
		//total fences is perimeter + amount of random, x and y position ararys
		fenceLocX = new int[(fenceBorderX + fenceBorderY) * 2 - 4 + randomFence];
		fenceLocY = new int[(fenceBorderX + fenceBorderY) * 2 - 4 + randomFence];
		
		mhos = new Mho[numMhos];
		
		//initializes mhos
		for(int i = 0; i < numMhos; i++)
		{
			mhos[i] = new Mho(0,0, Mho.REGULAR);
		}
		
		
		//sets positions of everything
		setBorderFence();
		setRandomFence();
		setMho();
		setHero(true);
		
		//sets pictures
		hero = getImage(getDocumentBase(), "hero.png");
		explosion = getImage(getDocumentBase(), "explosion.png");
		mho = getImage(getDocumentBase(), "mho.png");
		fence = getImage(getDocumentBase(), "fence.gif");
		rmho = getImage(getDocumentBase(), "rmho.png");
		
		board = new JOptionPane();
		board.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
	}
	
	/**
	 * Checks if the user has won.
	 */
	public void checkWin()
	{
		//user wins if number of dead mhos is all of them.
		checkKilled();
		if(deadMhos == numMhos)
		{
			win = true;
		}
	}
	
	/**
	 * Given position on board, returns which place the mho is on list.
	 * @param x X position
	 * @param y Y position
	 * @return Index on list
	 */
	public int checkMhoIndex(int x, int y)
	{
		int index = 0;
		for(int i = 0; i < numMhos; i++)
		{
			if(mhos[i].getX() == x && mhos[i].getY() == y)
			{
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * Gives message of asciiboard based off of positions
	 */
	public void showBoard()
	{
		if(numMhos < 61)
		{
			//y values
			for(int i = 0; i < fenceBorderY; i++)
			{
				//x values
				for(int j = 0; j < fenceBorderX; j++)
				{
					//checks for fence
					if(checkOccupied(j,i,false,true))
					{
						asciiBoard += "F   ";
					}
					//checks for mho
					else if(checkOccupied(j,i,true,false))
					{
						//places mho in order of list
						asciiBoard += mhoName.charAt(checkMhoIndex(j,i)) + "   ";
					}
					//checks for hero
					else if(heroX == j && heroY == i)
					{
						asciiBoard += "+   ";
					}
					//otherwise is space
					else
					{
						asciiBoard+="    ";
					}
						
				}
				asciiBoard+= "\n";
			}
			JTextArea asciiBoardLabel = new JTextArea(asciiBoard);
			
			asciiBoardLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			
			JOptionPane.showMessageDialog(board, asciiBoardLabel);
			asciiBoard = "";
		}
	}
	
	/**
	 * Draws grid if specified
	 * @param g Graphics to draw on
	 */
	public void drawLines(Graphics g)
	{
		g.setColor(lineColor);
		for(int i = 0; i < fenceBorderX + 1; i++)
		{
			g.drawLine(startX / 2 + i*sizeOfImg, 0,startX / 2 + i*sizeOfImg, getHeight());
		}
		for(int i = 0; i < fenceBorderY + 1; i++)
		{
			g.drawLine(0, startY /2 + i*sizeOfImg,getWidth(), startY /2 + i*sizeOfImg);
		}
	}
	
	/**
	 * Paints the applet
	 */
	public void paint(Graphics g)
	{
		
		//initializes image
		g.drawImage(explosion,-sizeOfImg,-sizeOfImg,this);
		
		//gives window focus
		requestFocus();
		
		//refreshes screen
		setSize(startX + sizeOfImg*fenceBorderX,startY + sizeOfImg*fenceBorderY);
		g.setColor(background);
		g.fillRect(0,0, getWidth(), getHeight());

		//draws previously set fences
		drawFences(g);
		drawMhos(g);
		
		if(grid)
		{
			drawLines(g);
		}
		
		//checks status of game
		checkLose();
		checkWin();
		
		//draws hero only if not lost, as to not draw a hero on top of a fence
		if(!lose)
		{
			drawHero(g);
			addKeyListener(move);
		}

		//if a turn has passed (move, not jump)
		if(turn != previousTurn)
		{
			//small delay between movement and mho movement
			waitTime(timeBetweenDraw);
			
			//moves mhos only if the character did not kill himself
			if(!lose && !win && turn != 0)
			{
				previousTurn = turn;
				mhoMovement(g);
			}
			
			checkLose();
			checkWin();

			//if no loss of win, sends out turn dialog
			if(!lose && !win && turn != 0)
			{
				
				waitTime(timeBetweenDraw);
				
				killed = (deadMhos - lastDead) +" mho(s) were killed last turn.";
				ascii = "See ascii board?";
				lastDead = deadMhos;
				
				Object[] options = {"Yes", "No"};
				int answer = JOptionPane.showOptionDialog(rootPane, startTurn + "\n" + killed + "\n" + ascii,  "Your turn.", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null, options, options[1]);
				
				if(answer == 0)
				{
					//allows player to see which mho will move first
					showBoard();
				}
			}
			
			//lose message
			else if(lose && turn != 0 && reset)
			{
				reset = false;
				win = false;
				lose = false;
				heroDeath(g);
				
				totalLoss++;
				score = "You have " + totalWin + " wins and " + totalLoss + " losses.";
				turns = "It took you " + turn + " turns to lose.";
				
				Object[] options = {"Yes", "No"};
				int answer = JOptionPane.showOptionDialog(rootPane, lost + "\n" + score + "\n" + turns + "\n" + again,  "Game Over", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null, options, options[0]);
				
				if(answer == 0)
				{
					init();
					paint(g);
				}
				else
				{
					JOptionPane.showMessageDialog(rootPane, quit + "\n" + author);
					System.exit(0);
				}
			}
		}
		
		//win message
		else if(win && turn != 0 && reset)
		{
			reset = false;
			lastAnimation = true;
			mhoAnimation(g,lastDeadIndex);
			
			win = false;
			lose = false;
			
			totalWin++;
			removeKeyListener(move);
			score = "You have " + totalWin + " wins and " + totalLoss + " losses.";
			turns = "It took you " + turn + " turns to win.";
			
			Object[] options = {"Yes", "No"};
			int answer = JOptionPane.showOptionDialog(rootPane, won + "\n" + score + "\n" + turns + "\n" + again,  "Game Over", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
					null, options, options[0]);
			
			if(answer == 0)
			{
				init();
				paint(g);
			}
			else
			{
				JOptionPane.showMessageDialog(rootPane, quit + "\n" + author);
				System.exit(0);
			}
		}
	}
	
	/**
	 * Randomizes unoccupied space for the hero
	 * @param countMhos Whether or not hero can be on a mho (jump condition)
	 */
	public void setHero(boolean countMhos)
	{
		int x;
		int y;
		boolean pass = false;
		
		//randomizes until condition is met
		do
		{
			pass = false;
			x = (int) (Math.random()*(fenceBorderX - 2)) + 1;
			y = (int) (Math.random()*(fenceBorderY - 2)) + 1;
			
			if(!checkOccupied(x,y,countMhos,true))
				pass = true;
				
		}while(!pass);
		
		//sets x and y of hero
		heroX = x;
		heroY = y;
	}
	
	/**
	 * Sets position of randomized mhos
	 */
	public void setMho()
	{
		for(int i = 0; i < numMhos; i++)
		{
			int x;
			int y;
			boolean pass = false;
	
			//randoms x and y values until conditions are met
			do
			{
				pass = false;
				x = (int) (Math.random()*(fenceBorderX - 2)) + 1;
				y = (int) (Math.random()*(fenceBorderY - 2)) + 1;
				
				if(!checkOccupied(x,y,true, true))
					pass = true;
					
			}while(!pass);
			
			//x and y values are stored
			if(i < regMhos)
				mhos[i] = new Mho(x,y, Mho.REGULAR);
			
			else
				mhos[i] = new Mho(x,y, Mho.SPECIAL);
		}
	}
	
	/**
	 * Draws the fences around the border
	 */
	public void setBorderFence()
	{
		int counter = 0;
		
		//upper row
		for(int i = 0; i < fenceBorderX; i++)
		{
			fenceLocX[counter] = i;
			fenceLocY[counter] = 0;
			counter++;
		}
		
		//left side column
		for(int i = 0; i < fenceBorderY-2; i++)
		{
			fenceLocX[counter] = 0;
			fenceLocY[counter] = i + 1;
			counter++;
		}
		
		//bottom row
		for(int i = 0; i < fenceBorderX; i++)
		{
			fenceLocX[counter] = i;
			fenceLocY[counter] = fenceBorderY - 1;
			counter++;
		}
		
		//right side column
		for(int i = 0; i < fenceBorderY-2; i++)
		{
			fenceLocX[counter] = fenceBorderX - 1;
			fenceLocY[counter] = i + 1;
			counter++;
		}
	}
	
	/**
	 * Finds random positions for random fences
	 */
	public void setRandomFence()
	{
		int counter = (fenceBorderX + fenceBorderY) * 2 - 4;
		for(int i = 0; i < randomFence; i++)
		{
			int x;
			int y;
			boolean pass = false;
			
			//randoms x and y until conditions are met
			do
			{
				pass = false;
				x = (int) (Math.random()*(fenceBorderX - 2)) + 1;
				y = (int) (Math.random()*(fenceBorderY - 2)) + 1;
				
				if(!checkOccupied(x,y,true,true))
					pass = true;
					
			}while(!pass);
			
			//adds random fences to fence x and y information
			fenceLocX[counter] = x;
			fenceLocY[counter] = y;
			counter++;
		}
	}
	
	/**
	 * Draws fences based on location and size of img
	 * @param g Graphic to draw on
	 */
	public void drawFences(Graphics g)
	{
		for(int i = 0; i < fenceLocX.length; i++)
		{
			g.drawImage(fence,startX +  fenceLocX[i]*sizeOfImg, startY +  fenceLocY[i]*sizeOfImg,this);
		}
	}
	
	/**
	 * Draws mhos based on location and size of img
	 * @param g Graphic to draw on
	 */
	public void drawMhos(Graphics g)
	{
		for(int i = 0; i < numMhos; i++)
		{
			if(!mhos[i].getDead())
			{
				if(mhos[i].getType() == Mho.REGULAR)
					g.drawImage(mho, startX + mhos[i].getX()*sizeOfImg, startY + mhos[i].getY()*sizeOfImg,this);
				
				else if(mhos[i].getType() == Mho.SPECIAL)
					g.drawImage(rmho, startX + mhos[i].getX()*sizeOfImg, startY + mhos[i].getY()*sizeOfImg,this);
			}		
		}
	}
	
	/**
	 * Draws hero based on location and size of img
	 * @param g Graphic to draw on
	 */
	public void drawHero(Graphics g)
	{
		g.drawImage(hero,startX + heroX*sizeOfImg, startY + heroY*sizeOfImg,this);

	}
	
	/**
	 * Checks is given position is occupied.
	 * @param x X position to investigate
	 * @param y Y position to investigate
	 * @param careFence Whether or not to include fences
	 * @param careMho Wheter or not to include mhos
	 * @return Whether or not position is occupied
	 */
	public boolean checkOccupied(int x, int y, boolean careMho, boolean careFence)
	{
		boolean occupied = false;
		
		//searches through mho list, excluding dead ones if specified
		if(careMho)
		{
			for(int i = 0; i < numMhos; i++)
			{
				
				if(!mhos[i].getDead())
				{
					if(x == mhos[i].getX() && y == mhos[i].getY())
						occupied = true;
				}
			}
		}
		
		//searches through fence list if specified
		if(careFence)
		{
			for(int i = 0; i < fenceLocX.length; i++)
			{
				if(fenceLocX[i] == x && fenceLocY[i] == y)
					occupied = true;
			}
		}

		return occupied;
	}
	
	/**
	 * Checks how many mhos are killed and at which indices.
	 */
	public void checkKilled()
	{
		//checks to see if any mhos on on fences, is so, they are dead
		for(int i = 0; i < numMhos; i++)
		{

			for(int j = 0; j < fenceLocX.length; j++)
			{
				if(mhos[i].getX() == fenceLocX[j] && mhos[i].getY() == fenceLocY[j] && !mhos[i].getDead())
				{
					mhos[i].setDead(true);
					deadMhos ++;
				}
			}
			
			
		}
	}
	
	/**
	 * Programs movement for regular mhos
	 * @param i Index of mho array
	 */
	public void regMhoMovement(int i)
	{

		//counter for second priority from caring about fence to not caring about fence
		int counter = 0;
		
		//whether or not a valid position has been found
		boolean goal = false;
		
		//whether or not to take into account for position of fence
		boolean careFence = true;
		
		//directly horizantal
		if(mhos[i].getY() == heroY)
		{
			if(mhos[i].getX() < heroX && !checkOccupied(mhos[i].getX() + 1, mhos[i].getY(), true, false))
			{
				mhos[i].setX(mhos[i].getX()+1);
				goal = true;
			}
			
			if(mhos[i].getX() > heroX && !checkOccupied(mhos[i].getX() - 1, mhos[i].getY(), true, false))
			{
				mhos[i].setX(mhos[i].getX()-1);
				goal = true;
			}
			
		}
		
		//directly vertical
		else if(mhos[i].getX() == heroX)
		{
			if(mhos[i].getY() < heroY && !checkOccupied(mhos[i].getX(), mhos[i].getY() + 1, true, false))
			{
				mhos[i].setY(mhos[i].getY()+1);
				goal = true;
			}
			
			if(mhos[i].getY() > heroY && !checkOccupied(mhos[i].getX(), mhos[i].getY() - 1, true, false))
			{
				mhos[i].setY(mhos[i].getY()-1);
				goal = true;
			}
			
		}
		
		//does others, first does without getting on fence, then does without caring.
		else 
		{
			do			
			{
				//diagonals
				//upper right diagonal
				if(mhos[i].getX() < heroX && mhos[i].getY() > heroY && !checkOccupied(mhos[i].getX() + 1, mhos[i].getY() - 1,true,  careFence))
				{
					mhos[i].setX(mhos[i].getX()+1);
					mhos[i].setY(mhos[i].getY()-1);
					goal = true;
				}
				//upper left diagonal
				else if(mhos[i].getX() < heroX && mhos[i].getY() < heroY && !checkOccupied(mhos[i].getX() + 1, mhos[i].getY() + 1, true, careFence))
				{
					mhos[i].setX(mhos[i].getX()+1);
					mhos[i].setY(mhos[i].getY()+1);
					goal = true;
				}
				//lower right diagonal
				else if(mhos[i].getX() > heroX && mhos[i].getY() > heroY && !checkOccupied(mhos[i].getX() - 1, mhos[i].getY() - 1,true,  careFence))
				{
					mhos[i].setX(mhos[i].getX()-1);
					mhos[i].setY(mhos[i].getY()-1);
					goal = true;
				}
				//lower left diagonal
				else if(mhos[i].getX() > heroX && mhos[i].getY() < heroY && !checkOccupied(mhos[i].getX() - 1, mhos[i].getY() + 1, true, careFence))
				{
					mhos[i].setX(mhos[i].getX()-1);
					mhos[i].setY(mhos[i].getY()+1);
					goal = true;
				}
				
				//x distance is more
				else if(Math.abs(mhos[i].getX()-heroX) >= Math.abs(mhos[i].getY()-heroY))
				{
					//mho x is below
					if(mhos[i].getX() < heroX && !checkOccupied(mhos[i].getX() + 1, mhos[i].getY(),true,  careFence))
					{
						mhos[i].setX(mhos[i].getX()+1);
						goal = true;
					}
					//mho x is above
					else if(mhos[i].getX() > heroX && !checkOccupied(mhos[i].getX() - 1, mhos[i].getY(), true, careFence))
					{
						mhos[i].setX(mhos[i].getX()-1);
						goal = true;
					}	
				}
				
				//x distance is less
				else if(Math.abs(mhos[i].getX()-heroX) < Math.abs(mhos[i].getY()-heroY))
				{
					//mho y is less
					if(mhos[i].getY() < heroY && !checkOccupied(mhos[i].getX(), mhos[i].getY() + 1, true, careFence))
					{
						mhos[i].setY(mhos[i].getY()+1);
						goal = true;
					}
					//mho y is more
					else if(mhos[i].getY() > heroY && !checkOccupied(mhos[i].getX(), mhos[i].getY() - 1, true, careFence))
					{
						mhos[i].setY(mhos[i].getY()-1);
						goal = true;
					}
				}

				//switches fence preference if goal is still not reached
				//I interpret it as moving not caring about landing on a fence while still moving towards you
				//on the second time
				if(!goal)
				{
					counter++;
					careFence = false;
				}
			}while(counter <= 2 && goal == false);
		}
	}
	/**
	 * Programs movement for special type mhos
	 * @param i Index of mho array
	 */
	public void specialMhoMovement(int i, Graphics g)
	{
		//random placement, one block away
		int x;
		int y;
		
		do
		{
			x = (int) Math.round((mhos[i].getX() + Math.random()*2 - 1));
			y = (int) Math.round((mhos[i].getY() + Math.random()*2 - 1));
			
		} while(x == mhos[i].getX() || y == mhos[i].getY());
	
		//if special lands on another mho, it kills it
		int index = checkMhoIndex(x,y);
		if(checkOccupied(x,y,true, false) && index != i)
		{
			mhos[index].setDead(true);
			deadMhos++;
			
			mhos[i] = new Mho(-1,-1,mhos[i].getType());
			mhoAnimation(g, i);
			
			//draws explosion
			g.drawImage(explosion, startX + mhos[index].getX()*sizeOfImg, startY + mhos[index].getY()*sizeOfImg, this);
		
			//waits to paint explosion out
			waitTime(timeBetweenDraw + 200);
		}	
		mhos[i] = new Mho(x,y,mhos[i].getType());
	}
	/**
	 * Moves the mhos based off of their priorities
	 */
	public void mhoMovement(Graphics g)
	{
		
		for(int i = 0; i < numMhos; i++)
		{
			checkLose();
			checkWin();
			
			if(!mhos[i].getDead() && !lose && !win)
			{
				//regular type
				if(mhos[i].getType()== Mho.REGULAR)
				{
					regMhoMovement(i);
				}
				
				//special type
				else if(mhos[i].getType()==  Mho.SPECIAL)
				{
					specialMhoMovement(i, g);
				}
			
				//does nothing if nothing else works
				checkKilled();
				checkWin();
				
				//prevents mhos from being moved in the next game after conditions are cleared
				if(win)
				{
					lastDeadIndex = i;
					i = numMhos + 1;
				}
				mhoAnimation(g, i);
			}
		}
	}
	
	/**
	 * Animates death of hero
	 * @param g Graphics to draw on
	 */
	public void heroDeath(Graphics g)
	{
		//draws explosion
		g.drawImage(explosion, startX + heroX*sizeOfImg, startY + heroY*sizeOfImg, this);
	}
	
	public void waitTime(int time)
	{
		lastDrawTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - lastDrawTime <= time)
		{}
	}
	/**
	 * Makes graphical changes to screen after every mho's turn
	 * @param g Graphics to draw on
	 * @param index Index of given mho
	 */
	public void mhoAnimation(Graphics g, int index)
	{
		//delay of animation
		waitTime(timeBetweenDraw);
		
		//updates mho positions
		if(!lastAnimation)
		{
			paint(g);
		}
		
		//explosion animation if mho is dead
		if(turn != 0 && mhos[index].getDead())
		{
			//draws explosion
			g.drawImage(explosion, startX + mhos[index].getX()*sizeOfImg, startY + mhos[index].getY()*sizeOfImg, this);
		
			//waits to paint explosion out
			waitTime(timeBetweenDraw + 200);
			
			//does not repaint on last frame
			//otherwise paints explosion away.
			if(!lastAnimation)
			{
				paint(g);
			}
		}
	}
	/**
	 * Checks if game has been lost
	 */
	public void checkLose()
	{	
		//condition of loss
		if(checkOccupied(heroX, heroY, true, true))
			lose = true;	
	}
	
	/**
	 * Listens for buttom presses on applet to move the hero.
	 * @author Matthew
	 *
	 */
	public class MoveListener implements KeyListener
	{
		/**
		 * Listens for specific key presses and changes the
		 * hero position and increases the turn value if necessary.
		 */
		public void keyPressed(KeyEvent e)
		{
			//Makes sure that key is not spammed, spacing by 100 ms
			 if(System.currentTimeMillis() - lastPressProcessed > 100) 
			 {
				//increases turn if movement counts as turn
				//upper left
				int pressed = e.getKeyChar();
				switch(pressed)
				{
					case 'q':
						
						heroY = heroY-1;
						heroX = heroX-1;
						turn++;
						repaint();
						
					break;
					//up
					case 'w':
						
						heroY = heroY-1;
						turn++;
						repaint();
						
					break;
					//upper right
					case 'e':
						
						heroY = heroY-1;
						heroX = heroX+1;
						turn++;
						repaint();
						
					break;
					//left
					case 'a':
						
						heroX = heroX-1;
						turn++;
						repaint();
						
					break;
					//sit
			 		case 's':
			 			
						turn++;
						repaint();
						
					break;
					//right
			 		case 'd':
			 			
						heroX = heroX+1;
						turn++;
						repaint();
						
					break;
					
					//bottom left
			 		case 'z':
			 			
						heroY = heroY+1;
						heroX = heroX-1;
						turn++;
						repaint();
						
					break;
					//down
			 		case 'x':
			 			
						heroY = heroY+1;
						turn++;
						repaint();
						
					break;
					//bottom right
			 		case 'c':
			 			
						heroY = heroY+1;
						heroX = heroX+1;
						turn++;
						repaint();
						
					break;
					//jump
			 		case 'j':
			 			
						setHero(false);
						
						//increases turn to end if hero has died
						checkLose();
						if(lose)
						{
							turn++;
						}
						repaint();
						
					break;
					//b for board
			 		case 'b': showBoard(); break;
					
					//toggles grid
			 		case 'g':
			 			
						if(grid)
						{
							grid = false;
						}
						
						else
						{
							grid = true;
						}
						repaint();
						
					break;
				}
				
				//accounts for time spacing
				lastPressProcessed = System.currentTimeMillis();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}
	
}
