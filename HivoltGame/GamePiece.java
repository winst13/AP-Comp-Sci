import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


abstract public class GamePiece extends JLabel
{
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	public abstract int checkmove(int newx, int newy);
	public static ImageIcon fenceicon = loadFenceIcon();
	public static ImageIcon mhoicon = loadMhoIcon();
	public static ImageIcon actoricon = loadActorIcon();
	GameBoard parentboard;
	
	/**
	 * This is the constructor for the GamePiece, which is the superclass for 
	 * Actor, Mho, and ElecricFence.  It is an abstract class. It creates the
	 * pieces at the coordinates (inputx, inputy), on the GameBoard myparent. 
	 * @param inputx 
	 * @param inputy
	 * @param myparent
	 */
	GamePiece(int inputx, int inputy, GameBoard myparent)
	{
		super();
		x = inputx;
		y = inputy;
		parentboard=myparent;
	}
	
	/**
	 * This method is only overridden in the ElectricFence class.  This
	 * allows the program to identify which piece is which
	 * @return
	 */
	public boolean isfence() 
	{
		return false;
	}
	
	/**
	 * This method, like the isfence() mehtod, is only overriden in the Mho
	 * class.  When this returns true, the piece is a mho.
	 * @return
	 */
	public boolean ismho() 
	{
		return false;
	}
	
	/**
	 * Similar to both the ismho() and isfence() methods, this method
	 * is overriden in the Actor class, and identifies the piece as an
	 * actor
	 * @return
	 */
	public boolean isactor() 
	{
		return false;
	}
	
	/**
	 * This method uploads the image that represents the actor
	 * @return
	 */
	private static ImageIcon loadActorIcon() 
	{
		File iconfile = new File("cookie.png");
		return loadIconSize(iconfile, 49, 49);
	}
	
	/**
	 * This method uploads the image that represents a mho
	 * @return
	 */
	private static ImageIcon loadMhoIcon() 
	{
		File iconfile = new File("monstor.jpg");
		return loadIconSize(iconfile, 49, 49);
	}
	
	/**
	 * This method uploads the image that represents an ElectricFence
	 * @return
	 */
	private static ImageIcon loadFenceIcon() 
	{
		File iconfile = new File("hivolt.jpg");
		return loadIconSize(iconfile, 49, 49);
	}
	
	/**
	 * This method makes sure that the uploaded images will fit within each grid cell
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	private static ImageIcon loadIconSize(File file, int width, int height)
	{
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(file);
		} catch (IOException e) 
		{
		    e.printStackTrace();
		}
		Image resizeimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(resizeimg);
		return imageIcon;
	}
	
	/**
	 * Gets the x coordinate
	 * @return
	 */
	public int getx()
	{
		return x;
	}
	
	/**
	 * Gets the y coordinate
	 * @return
	 */
	public int gety()
	{
		return y;
	}
	
	/**
	 * Sets x coordinate
	 * @param x
	 */
	public void setx(int newx)
	{
		x = newx;
	}
	
	/**
	 * Gets y coordinate
	 * @param y
	 */
	public void sety(int newy)
	{
		y = newy;
	}
	
	/**
	 * This method makes the GamePiece move up.
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return
	 */
	public int moveUp()
	{
		int returnval = checkmove(x, y-1);
		if (returnval==0)
		{
			y--;
			parentboard.move(x, y+1, this);
		}
		return returnval;
	}
	
	/**
	 * This method makes the GamePiece move down
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return 
	 */
	public int moveDown()
	{
		int returnval = checkmove(x, y+1);
		if (returnval==0)
		{
			y++;
			parentboard.move(x, y-1, this);
		}
		return returnval;
	}
	
	/**
	 * This method makes the GamePiece move left
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return
	 */
	public int moveLeft()
	{
		int returnval = checkmove(x-1, y);
		if (returnval==0)
		{
			x--;
			parentboard.move(x+1, y, this);
		}
		return returnval;
	}
	
	/**
	 * This method makes the GamePiece move right
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return
	 */
	public int moveRight()
	{
		int returnval = checkmove(x+1, y);
		if (returnval==0)
		{
			x++;
			parentboard.move(x-1, y, this);
		}
		return returnval;
	}
	
	/**
	 * This method makes the GamePiece move up and left
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return
	 */
	public int moveUpLeft()
	{
		int returnval = checkmove(x-1, y-1);
		if (returnval==0)
		{
			x--;
			y--;
			parentboard.move(x+1, y+1, this);
		}
		return returnval;
	}
	
	/**
	 * This method makes the GamePiece move up and right
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return 
	 */
	public int moveUpRight()
	{
		int returnval = checkmove(x+1, y-1);
		if (returnval==0)
		{
			x++;
			y--;
			parentboard.move(x-1, y+1, this);
		}
		return returnval;
	}
	
	/**
	 * This method makes the GamePiece move down and left
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return 
	 */
	public int moveDownLeft()
	{
		int returnval = checkmove(x-1, y+1);
		if (returnval==0)
		{
			x--;
			y++;
			parentboard.move(x+1, y-1, this);
		}
		return returnval;
	}
	
	/**
	 * This method makes the GamePiece move down and right
	 * Returns whether it it moved onto a blank space, mho, electricfence, or actor
	 * @return
	 */
	public int moveDownRight()
	{
		int returnval = checkmove(x+1, y+1);
		if (returnval==0)
		{
			x++;
			y++;
			parentboard.move(x-1, y-1, this);
		}
		return returnval;
	}
}
