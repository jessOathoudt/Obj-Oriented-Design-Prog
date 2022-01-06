/**
 * Models a homework trap on the board. Placed by Jarvis move. Not visible. If shared with player, player gets stuck
 * for 5 seconds
 * @author jessicaoathoudt
 *
 */
public class HomeworkTrap implements Boardable 
{

	/**
	 * instance variable board - holds the board
	 */
	private Board board;
	
	/**
	 * HomeworkTrap constructor. Takes the board
	 * @param board
	 */
	public HomeworkTrap(Board board) 
	{
		this.board = board;
	}

	
	/**
	 * visibility for homework trap is always false
	 * @return - boolean (always false)
	 */
	public boolean isVisible() 
	{
		//traps should not be visible
		return false;
	}

	/**
	 * Checks what can share with type HWT. If sharing with player, the player is stuck for 5 seconds
	 * otherwise nothing happens
	 * @param - elem
	 * @return - boolean - true if sharing with player
	 */
	@Override
	public boolean share(Boardable elem) 
	{
		boolean shareHwTrap = false;
		//add elem method in cell calls share
		//if elem is type player
		if(elem instanceof Player) 
		{
			//only setsDelay and lands on hW trap if is "sprung"
			//removes itself from the board so that the player
			//cant go back into the same trap
			if(board.removeElement(this)) 
			{
				System.out.println("\nYou have landed on a homework trap! Do your homework for 5 seconds, "
						+ "then you can move again.");
				//sets delay time on player for 5 seconds
				((Player) elem).setDelayTime(5000);
				shareHwTrap = true;
			}				
		}
		//if elem type Jarvis, nothing happens
		if(elem instanceof Jarvis) 
		{
			shareHwTrap = false;
		}
		//if elem type HomeworkTrap, nothing happens
		if(elem instanceof HomeworkTrap)
		{
			shareHwTrap = false;
		}
		return shareHwTrap;
	}

	/**
	 * String representation of homework trap
	 * " " to camouflage them on the board
	 * @return- ret (string)
	 */
	public String toString() 
	{
		String ret = " ";
		return ret;
	}
}
