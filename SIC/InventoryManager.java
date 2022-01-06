import java.util.ArrayList;

/**
 * models an inventory holding all sic id cards for a bookstore/inventory
 * methods for adding/removing cards
 * getting info by Title, SIC-ID, and Author
 * changing price and quantity of books
 * seeing if card is in inventory
 * 
 * @author jessicaoathoudt
 *
 */
public class InventoryManager 
{
	/**
	 * holds the sic ids in an array list can be 0 to n
	 * empty right now
	 */
	private ArrayList<StockIndexCard> inventory = new ArrayList<StockIndexCard>(); 

	/**
	 * constructor for array list of sic id cards, inventory
	 * @param quantity 
	 * @param price 
	 * @param author 
	 * @param title 
	 * @param sicId 
	 * @return boolean  //false if already in inventory true if new sicId
	 */
	public boolean addCard(int sicId, String title, String author, double price, int quantity) 
	{
		//for a stock card in the list
		for(StockIndexCard curCard : inventory) 
		{
			//if sic id entered and sic id in list is the same
			if(curCard.getBookSicId() == sicId) 
			{
				//dont make new card and dont add bc already in list
				return false;
			}
		}
		//make new card and add it to list
		StockIndexCard card = new StockIndexCard(sicId, title, author, price, quantity); 
		this.inventory.add(card);
		return true;
	}// end of add card

	/**
	 * getter method that finds SIc card based on sic id. will print out the title, author, price, and quant of book in inventory
	 * @param sicId
	 * @return String
	 */
	public String getInfoBySicId(int sicId) 
	{
		// start with empty string
		String ret = " ";
		ret = ("This SIC-ID is not in the inventory\n\n");
		for(int i = 0; i < this.inventory.size(); i = i + 1) 
		{
			// get the index of every card in list
			StockIndexCard curCard = this.inventory.get(i);
			int tempId = curCard.getBookSicId();
			if(tempId == sicId) 
			{
				// if caller sicid and card sicid match, return all cards that match to string
				ret = (curCard.toString()+"\n");
			} 		
		}
		return ret;
	}// end of getinfoBySicId
	
	/**
	 * getter method that finds sic card based on title. will print the sic id, author, price, and quant of book in inventory
	 * @param title
	 * @return string
	 */
	public String getInfoByTitle(String title) 
	{
		//start with empty string
		String ret = "";
		for(int i = 0; i < this.inventory.size(); i = i + 1) 
		{
			//gets the index of every card
			StockIndexCard curCard = this.inventory.get(i);
			//tempTitle gets the title of caller
			String tempTitle = curCard.getBookTitle();
			//if caller title matches title in inventory
			if(tempTitle.equals(title)) 
			{
				//string gets that book
				ret = ret+curCard.toString()+"\n";
			} 
		}
		//if there is no title in inventory that matches the callers
		if(ret.length() == 0) {
			//print error message; no title
			ret = "This Title is not in the inventory";
		}
		return ret;
	}//end of getInfoByTitle
	
	/**
	 * getter method that finds sic card based on author. will print the sic id, author, price, and quant of book in inventory
	 * @param author
	 * @return
	 */
	public String getInfoByAuthor(String author) 
	{
		//length of 0
		String ret = "";
		//loops through inventory
		for(int i = 0; i < this.inventory.size(); i = i + 1) 
		{
			//get the index of curCard
			StockIndexCard curCard = this.inventory.get(i);
			//tempauthor is callers author
			String tempAuthor = curCard.getBookAuthor();
			//if caller's author and author in inventory match
			if(tempAuthor.equals(author)) 
			{
				//that book gets added to string
				ret = ret+curCard.toString()+"\n";
			}
		}
		//if no matches to callers author
		if(ret.length() == 0) {
			//print error message; no title
			ret = "This Author is not in the inventory";
		}
		return ret;
	}// end of getInfoByAuthor
	
	/**
	 * checks to see if inventory has the card by sicid. if not, return false.
	 * @param sicId
	 * @return
	 */
	public boolean hasCard(int sicId) 
	{
		//for all cards in inventory
		for(StockIndexCard curCard : inventory)
		{
			//if the sicId the caller inputs matches sicId in inventory
			if(curCard.getBookSicId() == sicId)
			{
				return false;
			} 
		}
		//if it doesnt match
		return true;
	}// end of has card
	
	/**
	 * removes card from inventory by sicID
	 * @param sicId
	 * @return boolean // removes card if sicId matches otherwise false
	 */
	public boolean removeCard(int sicId)
	{
		//for card in inventory
		for(StockIndexCard curCard : inventory)
		{
			//if the caller sicId and sicId in inventory match
			if(curCard.getBookSicId() == sicId)
			{
				//remove that card that matches
				inventory.remove(curCard);
				return true;
			}
		}
		//dont remove card because no matches
		return false;
	}// end of remove card
	
	/**
	 * allows caller to change price of book in inventory
	 * @param sicId
	 * @param price
	 * @return boolean // allows caller to set new price and returns true if sicID matches. otherwise false
	 */
	public boolean changeCardPrice(int sicId, double price) 
	{
		//for card in inventory
		for(StockIndexCard curCard : inventory) 
		{
			//if the sic Id matches and the caller's price is greater than 0
			if(curCard.getBookSicId() == sicId && curCard.getBookPrice()>0) 
			{
				//set the callers price to the inventorys price
				curCard.getBookPrice();
				curCard.setBookPrice(price);
				return true;
			}
		}
		//don't change the price from the original inventory price
		return false;
	}
	/**
	 * allows caller to inrease the stock of a book
	 * @param sicId
	 * @param amount
	 * @return
	 */
	public boolean increaseCardQuantity(int sicId, int amount)
	{
		//for card in inventory
		for(StockIndexCard curCard : inventory) 
		{
			//if the sicId's match and the caller's increase in stock is actually increasing the stock
			if(curCard.getBookSicId() == sicId && curCard.getBookQuantity() < amount) 
			{
				//set callers stock to inventory stock
				curCard.getBookQuantity();
				curCard.setBookQuantity(amount);
				return true;
			}
		}
		//dont increase stock
		return false;
	}
	
	/**
	 * allows caller to decrease stock of a book
	 * @param sicId
	 * @param amount
	 * @return
	 */
	public boolean decreaseCardQuantity(int sicId, int amount) 
	{
		//for card in inventory
		for(StockIndexCard curCard : inventory) 
		{
			//if sicIds match and callers decrease in stock is actually decreasing stock
			if(curCard.getBookSicId() == sicId && curCard.getBookQuantity() > amount) 
			{
				//set callers decrease stock to quantity in inventory
				curCard.getBookQuantity();
				curCard.setBookQuantity(amount);
				return false;
			}
		}
		//dont change quantity in inventory
		return true;
	}

	/**
	 * gets list of stock cards
	 */
	public ArrayList<StockIndexCard> getInventory() 
	{
		//gets list of books
		return inventory;
	}// end of get inventory
	
	
	/**
	 * returns inventory info to string; all books in inventory to string
	 */
	public String toString() 
	{
		String ret = "Inventory:  ";
		//for card in inventory
		for(StockIndexCard curCard : inventory)
		{
			//change that card to string
			ret = ret+"\n"+ curCard.toString();
			ret.toString();
		}
		return ret;
		}//end of toString
}

			

		



