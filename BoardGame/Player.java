//import Scanner
import java.io.IOException;
import java.util.Scanner;

//player move should check been hugged

/**
 * Models a player that moves on the board
 * it is boardable 
 * it is always visible
 * string version of player is: *
 * @author jessica oathoudt
 *
 */
public class Player extends Mobile implements Boardable, Runnable
{

	/**
	 * instance variable board
	 * type Board holds the board the stylus will move on
	 */
	
	private Board board;
    /**
     * instance variable input
     * type Scanner, holds the user's input for direction to move the player
     * 
     */
	//creates new scanner
	private Scanner input = new Scanner(System.in);
	
	/**
	 * instance variable delayTime- holds the amount of time player wait if lands on hw trap otherwise its 0
	 */
	private long delayTime;
	
    /**
     * Constructor for Stylus
     * @param board - type board, holds the board the stylus will move on
     */
	public Player(Board board) 
	{
		//calls Board's board
		super(board);
		this.board = board;
        //this calls the player
        //places player on the board
        // 0,0 is the top left of the board (where it starts)
		this.board.placeElement(this, 0, 0);
	}
    
    /**
     * Based on the direction entered, calls the board move 
     * method it calls the Direction from Enum and the player as the
     * boardable element
     *
     */
	@Override
	protected void move() 
	{
		//call delay
		//should be 0 if the player is not on a trap
		//should be 500 if the player is on a trap
		delay();
		//print the board
		board.printBoard();
		System.out.println("\nDIRECTIONS: q = UP-LEFT, w = UP, e = UP-RIGHT, a = LEFT, d = RIGHT, "
				+ "z = DOWN-LEFT, x = DOWN, c = DOWN-RIGHT");
		System.out.print("Enter a direction to move: ");
		//takes the direction the user enters
		//switch on string
		String dir = input.nextLine();
		//switch based on the user's input
		switch (dir) 
		{
		//if q,w,e,a,d,z,x,c
		//else act as s
		//this is player
		//moves the player on the board
		case "q":
			board.move(Direction.UP_LEFT, this);
			break;
		case "w":
			board.move(Direction.UP, this);
			break;
		case "e":
			board.move(Direction.UP_RIGHT, this);
			break;
		case "a":
			board.move(Direction.LEFT, this);
			break;
		case "d":
			board.move(Direction.RIGHT, this);
			break;
		case "z":
			board.move(Direction.DOWN_LEFT, this);
			break;
		case "x":
			board.move(Direction.DOWN, this);
			break;
		case "c":
			board.move(Direction.DOWN_RIGHT, this);
			break;
		case "s":
			//no move
			break;
		//if not these directions^ false
		default:
			//everything else
			break;
		}
	}
	
	/**
	 * Runnable interface, calls player to move as
	 * long as Jarvis is not hugged
	 */
	@Override
	public void run() 
	{
		//while beenHugged returns false
		//aka no jarvis and player sharing
		while(!board.beenHugged()) 
		{
			//move player
			move();
		}
	}

	/**
	 * method overload from boardable interface
	 * always true
	 */
	@Override
	public boolean isVisible() 
	{
		return true;
	}

	/**
	 * string representation of player
	 */
	@Override
	public String toString() 
	{
		return "*";
	}
	
	/**
	 * This sets delayTime to be whatever it needs to be
	 * either 0 if no trap or 500 if land on trap
	 * @param time
	 */
	public void setDelayTime(long time) 
	{
		this.delayTime = time;
	}
	
	/**
	 * method that makes player wait 5 seconds if they land on a trap
	 * They cannot move while they are trapped
	 */
	private void delay() 
	{
		try 
		{
			//wait 500 milliseconds
			Thread.sleep(delayTime);
			//stops the user from moving if they land on a trap
			System.in.read(new byte[System.in.available()]);
			//catches an error if doesn't delay
		}
		//catches error on sleep
		catch(InterruptedException | IOException exp) 
		{
			System.out.println("Error on delaying Player");
		}
		//set delaytime to 0 at the end of delay
		this.setDelayTime(0);
	}
	
	/**
	 * Player does not share with anything. It is always false
	 * @param elem
	 * @return false
	 */
	@Override
	public boolean share(Boardable elem) 
	{
		return false;
	}
}
