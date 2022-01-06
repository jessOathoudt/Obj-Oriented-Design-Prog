import java.util.Scanner;

/**
 * Driver for the program. Uses threads through the interface Runnable to run the program
 * creates a board, jarvis, and player
 * places jarvis and player
 * makes jarvis and player threads
 * starts threads
 * @author jessicaoathoudt
 *
 */
public class Game 
{
	/**
	 * Main for program
	 * @param args
	 */

	public static void main(String[] args) 
	{
		System.out.println("\nWelcome to Hug the Jarvis!\n");
    	//new scanner
    	Scanner input = new Scanner(System.in);
    	boolean valid = false;

    	int height = 0;
    	int width = 0;
        //tests that the input for board dimensions is correct
    	do 
    	{
    		//exception for height and width input
    		try 
    		{
    			System.out.print("Enter the height for the board: ");
    			height = Integer.parseInt(input.nextLine());
    			System.out.print("Enter the width for the board: ");
    			width = Integer.parseInt(input.nextLine());
                // cant be 0 or less and cant be greater than 100!
    			if (height <= 0 || height > 100 || width <= 0 || width > 100) 
    			{
    				throw new Exception();
    			}
    			valid = true;
    		//if they enter something other than numbers
    		} catch (IllegalArgumentException e) 
    		{
    			System.out.println("\nInvalid input, height and width should be numbers!\n");
    		//if the dimensions are too big or too small
    		} catch(Exception exp)
    		{
    			System.out.println("Invalid board measurements, dimensions are too small or too big!");
    		}
    		//run this loop while board dimensions are invalid
    	} while (!valid);

		//creates board of of size height and width
		Board board = new Board(height,width);
		//creates jarvis on board
		Jarvis jarvis = new Jarvis(board);
		//checks if jarvis was placed
		boolean jarvisPlaced = false;
		int row = 0;
		int column = 0;
		do 
    	{
    		//exception jarvis placement input
    		try 
    		{
    			System.out.print("Enter the row for Jarvis: ");
    			row = Integer.parseInt(input.nextLine());
    			System.out.print("Enter the column for Jarvis: ");
    			column = Integer.parseInt(input.nextLine());
                // cant be 0 or less and cant be greater than board measurements!
    			if (row < 0 || row >= height || column < 0 || column >= width) 
    			{
    				throw new Exception();
    			}
    		jarvisPlaced = true;
    		//if they enter something other than numbers
    		} catch (IllegalArgumentException e) 
    		{
    			System.out.println("\nInvalid input, jarvis must be placed with two numbers"
    					+ "in the boards dimensions!\n");
    		//if the dimensions are too big or too small
    		} catch(Exception exp)
    		{
    			System.out.println("Invalid jarvis placement!");
    		}
    	//run this loop while board dimensions are invalid
    	}
		while(!jarvisPlaced);
		//places jarvis on board
		board.placeElement(jarvis,row, column);
		//creates player on board
		Player player = new Player(board);
		//thread for player
		Thread pThread = new Thread(player);
		//thread for jarvis
		Thread jThread = new Thread(jarvis);
		//start threads
		pThread.start();
		jThread.start();
		//join threads
		try 
		{
			jThread.join();
			pThread.join();
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Error on Thread joining");
		}
		//end
		//board.printBoard();
		System.out.println("\nYour hug has melted Jarvis' heart. Thank you for playing.");
		input.close();
	}
}
