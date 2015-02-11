
public class ElectricFence extends GamePiece
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * This method is the constructor for a new ElectricFence.  It takes
	 * the constructor for a GamePiece and changes the icon
	 * to a fence icon
	 * @param i
	 * @param j
	 * @param p
	 */
	public ElectricFence(int i, int j, GameBoard p) 
	{
		super(i, j, p);
		this.setIcon(GamePiece.fenceicon);
	}

	@Override
	/**
	 * This method allows an instance of Electric Fence to be identified
	 * as a fence
	 */
	public boolean isfence()
	{
		return true;
	}

	@Override
	/**
	 * Because the ElectricFence cannot move, this method should never
	 * be called.
	 */
	public int checkmove(int newx, int newy)
	{
		return -1;
	}
}
