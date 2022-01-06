/**
 * Mobile is a new abstract class that implements both Boardable and Runnable.
 *  
 * 
 * @author jessicaoathoudt
 *
 */
public abstract class Mobile implements Boardable, Runnable
{

	/**
	 * instance variable, protected board of type Board
	 */
	protected Board board;
	
	/**
	 * Mobile constructor. Takes the board as a parameter
	 * @param board
	 */
	public Mobile(Board board) 
	{
		this.board = board;
	}
	
	/**
	 * Lets Jarvis and Player move on the board
	 */
	protected abstract void move();
}
