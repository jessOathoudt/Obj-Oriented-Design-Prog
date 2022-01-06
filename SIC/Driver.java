/**
 * Main Class; starts the interface and creates new objects
 * 
 * @author jessicaoathoudt
 *
 */
public class Driver 
{

	public static void main(String[] args) 
	{
		//creates new book of type StockIndexCard for each card
		StockIndexCard book = new StockIndexCard(0, null, null, 0, 0);
		book.toString();
		//creates new manager of type InventoryManager for each book
		InventoryManager manager = new InventoryManager();
		manager.toString();
		//calls the UserInterface and makes it run
		UserInterface mainmenu = new UserInterface();
		mainmenu.startInterface();
	}

}
