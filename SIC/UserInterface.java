import java.util.Scanner;
/**
 * runs the mainmenu interface and prints out caller inputs. Uses switch statements
 * and do while loop to run different caller options
 * 
 * @author jessicaoathoudt
 *
 */
public class UserInterface 
{
	//variables that work throughout entire class
	int userInput;
	boolean quit = true;
	Scanner input = new Scanner(System.in);
	/**
	 * Manager, holds array list of books
	 */
	private InventoryManager manager = new InventoryManager();
	
	/**
	 * starts the main menu that user has to enter number for
	 */
	public void startInterface() 
	{
		//do while loop (while userinput != 10)
		do 
		{
				//main menu
				System.out.println();
				System.out.println("Please select a number from the following options:");
				System.out.println("1.) Add new Stock Index Card");
				System.out.println("2.) Remove Stock Index Card by SIC-ID");
				System.out.println("3.) Increase Stock by SIC-ID");
				System.out.println("4.) Decrease Stock by SIC-ID");
				System.out.println("5.) Display Stock Index Card by SIC-ID");
				System.out.println("6.) Display Stock Index Card by Author");
				System.out.println("7.) Display Stock Index Card by Title");
				System.out.println("8.) Display all Stock Index Cards");
				System.out.println("9.) Change Price by SIC-ID");
				System.out.println("10.) Quit");
				int userInput = input.nextInt();
			//switch statement; each case is a different option for the caller
			switch(userInput)
			{
			// if caller enters 1
			case 1:
				int sicId;
				String title;
				String author;
				double price;
				int quantity;

				
				System.out.println("Adding new Stock Index Card: ");
				
				System.out.println("Please enter a SIC-ID: ");
				// caller input
				sicId = input.nextInt();
				//if card is already in inventory
				if(!manager.hasCard(sicId)) 
				{
					//dont add to list because its already in inventory
					System.out.println();
					System.err.print("\nThe SIC-ID "+sicId+" is already in the inventory\n");
				} 
				else
				{
					// if card is not in inventory get other info
					System.out.println("Please enter the book title: ");
					title = input.nextLine();
					
					//simply makes it so next line doesn't print before caller puts in title
					title = input.nextLine();
					
					System.out.println("Please enter the book author: ");
					author = input.nextLine();
					
					
					System.out.println("Please enter the price of the book: ");
					price = input.nextDouble();
					
					System.out.println("Please enter the number of books in inventory: ");
					quantity = input.nextInt();
					// add card to inventory
					manager.addCard(sicId, title, author, price, quantity);
				}
				//end case 1
				break;
			
			case 2:
				System.out.print("Removing Stock Index Card: ");
				System.out.print("\nPlease enter SIC-ID: ");
				sicId = input.nextInt();
				//if sic id matches and is in inventory, remove that card/book
				if(!manager.hasCard(sicId))
				{
					manager.removeCard(sicId);
					System.out.println("Card removed!");
				} 
				// else dont remove because its not in inventory
				else 
				{
					System.err.println("The SIC-ID "+sicId+" is not in the inventory");
					System.out.println();
				}
				//end of case 2
				break;
				
			case 3:
				System.out.println("Increasing Stock by SIC-ID");
				System.out.println("Please enter the SIC-ID: ");
				//caller input
				sicId = input.nextInt();
				System.out.println("Please enter the new stock amount: ");
				// caller input
				quantity = input.nextInt();
				//card is in inventory and quantity is greater than og quant... 
				if(!manager.hasCard(sicId) && manager.increaseCardQuantity(sicId, quantity) == true) 
				{
					//increase the quantity
					manager.increaseCardQuantity(sicId, quantity);
					System.out.println("Stock Increased!");
				}
				//if quant is not greater than og quant
				else if(manager.increaseCardQuantity(sicId, quantity)==false)
				{
					//dont increase quant and print out error message
					System.out.println();
					System.err.println("Unable to increase the inventory amount");
				}
				//end of case 3
				break;
			
			case 4:
				System.out.println("Decreasing Stock by SIC-ID");
				System.out.println("Please enter the SIC-ID: ");
				// caller input
				sicId = input.nextInt();
				System.out.println("Please enter the new stock amount: ");
				// caller input
				quantity = input.nextInt();
				//if card is in inventory and quant is less than og quant and quantity is greater or equal to 0
				if(!manager.hasCard(sicId) && manager.decreaseCardQuantity(sicId, quantity) == false && quantity >= 0) 
				{
					// decrease the quantity
					manager.decreaseCardQuantity(sicId, quantity);
					System.out.println("Stock Decreased!");
				} 
				//dont increase the quant and print out error message
				else
				{
					System.out.println();
					System.err.print("Unable to decrease the inventory amount\n");
				}
				// end of case 4
				break;
			
				
			case 5:
				System.out.println("Displaying book by SIC-ID");
				System.out.println("Please enter a SIC-ID: ");
				//make sure user sic id is not already in system
				sicId = input.nextInt();
				System.out.println("Books with SIC-ID: "+sicId);
				// if card is in inventory and length of string is greater than 0
				if(!manager.hasCard(sicId) && manager.getInfoBySicId(sicId).length() >= 1) 
				{
					//print all books with same sicID
					System.out.print(manager.getInfoBySicId(sicId));
				}
				else
				{
					//if no books with sic id, print error message
					System.err.println("There are no books with SIC-ID "+sicId+" in inventory");
				}
				// end of case 5
				break;
				
			case 6:
				System.out.println("Displaying book by Author");
				System.out.println("Please enter a Author: ");
				//caller input
				author = input.next();
				//takes whole line
				author = author + input.nextLine();
				//prints the string returned from getInfoByAuthor (either books with same author or not in inventory)
				if(manager.getInfoByAuthor(author).length()>0) 
				{
					//print all books with same author
					System.out.println("Books with Author: "+author);
					System.out.println(manager.getInfoByAuthor(author));
				
				}
				//end of case 6
				break;
				
			case 7:
				System.out.println("Displaying book by Title");
				System.out.println("Please enter a Title: ");
				//caller input
				title = input.next();
				//takes whole line
				title = title + input.nextLine();
				//if string length is greater than 0
				if(manager.getInfoByTitle(title).length() >=1) 
				{
					//print that string of books with same title
					System.out.println("Books with Title: "+title);
					System.out.println(manager.getInfoByTitle(title));
				}
				else 
				{
					//no books with title called
					System.err.println("There are no book with the title "+title+" in inventory");
				}
				//end of case 7
				break;
				
			case 8:
				System.out.println("Displaying all books:\n");
				//if the list of book is empty
				if(manager.getInventory().isEmpty()) 
				{
					//print error message
					System.out.println();
					System.err.print("The inventory is empty!\n");
				}
				else 
				{
					//print out list of books
					System.out.println(manager.getInventory());
				}
				//end of case 8
				break;
			
			case 9:
				System.out.println("Change Price by SIC-ID");
				System.out.println("Please enter the SIC-ID: ");
				//caller input
				sicId = input.nextInt();
				//if card is in inventory
				//get price info
				System.out.println("Please enter the new price for book with SIC-ID "+sicId);
				price = input.nextDouble();
				//if price is greater than 0 and card is in inventory
				if(!manager.hasCard(sicId) && manager.changeCardPrice(sicId, price) == true && price >0)
				{
					//change price
					manager.changeCardPrice(sicId, price);
					System.out.print("Price changed!\n");
				}
				else
				{
					//dont change price and print error message
					System.err.println("ERROR: cannot change book price");
				}
				//end of case 9
				break;
				
			case 10:
				//var for true
				quit = false;
				//end of case 10
				break;
			
				
			default:
				//prints when caller puts in number not 1-10
				System.out.println("Invalid choice\n");
			}
		}
		//ends the do while loop for userInterface
		while(quit == true);
		//ending print statement
		System.out.println("Thank you for using Jess' Amazing inventory!");
	}
}
	


