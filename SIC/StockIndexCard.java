

// this is the class that actually modles a stock index card
/**
 * Models a stock index card from a book store inventory. Has the following attributes:
 * SIC-ID This is a unique integer used to identify every title in the inventory.
 * Title The title of the book being sold.
 * Author The author of the book.
 * Price The price the book is being sold for.
 * Quantity The number of copies the bookstore has of the book
 * 
 * @author jessicaoathoudt
 *
 */

public class StockIndexCard 
{
	//holds the SIC-ID of the card: valid values are integers
	private int bookSicId;
	//holds the title of the book: valid values are strings
	private String bookTitle;
	//holds the author of the book: valid values are strings
	private String bookAuthor;
	//holds the price of the book: valid values are double
	private double bookPrice;
	//holds the number of copies: valid values are integers
	private int bookQuantity;

	/**
	 * parameterized constructor that allows the caller to set values for bookSicId, bookTitle, 
	 * bookAuthor, bookPrice, and bookQuantity
	 * @param newBookSicId the sic id of the book, must be int
	 * @param newBookTitle the title of the book, must be string
	 * @param newBookAuthor the author of the book, must be string
	 * @param newBookPrice the price of the book, must be double
	 * @param newBookQuantity the num of copies, must be int
	 */
	public StockIndexCard(int bookSicId, String bookTitle, String bookAuthor, double bookPrice, int bookQuantity) 
	{
		//variables for each stock index card
		this.bookSicId = bookSicId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
		this.bookQuantity = bookQuantity;	
		}//end of StockIndexCard constructor
	
	/**
	 * getter method that allows the sic-id of a book to be retrieved
	 * @return sic-id of the book, type int
	 */
	public int getBookSicId() 
	{
		return bookSicId;
	}//end of getBookSicId
	
	/**
	 * getter method that allows the book title to be retrieved
	 * @return title of the book, type string
	 */
	public String getBookTitle() 
	{
		return bookTitle;
	}//end of getBookTitle
	
	/** 
	 * getter method that allows the author of the book to be retrieved
	 * @return author of the book, type string
	 */
	public String getBookAuthor() 
	{
		return bookAuthor;
	}//end of getBookAuthor
	
	/**
	 * getter method that allows the price of the book to be retrieved
	 * @return price of the book, type double
	 */
	public double getBookPrice() 
	{
		return bookPrice;
	}//end of getBookPrice
	
	/**
	 * setter method that allows the user to set the price of book
	 * @param newPrice
	 * @return boolean, true if greater than 0 and false if negative
	 */
	public boolean setBookPrice(double newPrice) 
	{
		if(newPrice > 0.00) 
		{
			bookPrice = newPrice;
			return true;
		} 
		return false;
	}//end of setBookPrice
	
	/**
	 * caller can set the book sicID
	 * @param bookSicId
	 */
	public void setBookSicId(int bookSicId) 
	{
		this.bookSicId = bookSicId;
	}//end of setBookSicId
	
	/**
	 * caller can set the book title
	 * @param bookTitle
	 */
	public void setBookTitle(String bookTitle) 
	{
		this.bookTitle = bookTitle;
	}//end of setBookTitle
	
	/**
	 * caller can set the book author
	 * @param bookAuthor
	 */
	public void setBookAuthor(String bookAuthor) 
	{
		this.bookAuthor = bookAuthor;
	}//end of setBookAuthor
	
	/**
	 * getter method that allows the num of copies to be retrieved
	 * @return quantity of books, type int
	 */
	public int getBookQuantity() 
	{
		return bookQuantity;
	}//end of getBookQuantity
	
	/**
	 * setter method that allows the caller to set the number of copies of a book
	 * @param newQuantity
	 * @return 
	 * @return bookQuantity, type int
	 */
	public boolean setBookQuantity(int newQuantity)
	{
		if(newQuantity >= 0) 
		{
			bookQuantity = newQuantity;
			return false;
		}
		return true;
	}//end of setBookQuantity
	
	/**
	 * returns the stockcard info as a string so it can be passed into different programs
	 * @return a string representation of the sic id card
	 */
	public String toString() 
	{
		String ret = "\nThe information for SIC-ID: "+this.bookSicId+"\n";
		ret = ret+"Stock Index Card: "+bookSicId+"\n" + "Title: "+bookTitle+"\n" + "Author: "+bookAuthor+"\n" + ""
				+ "Price: $"+bookPrice+ "\n" + "Quantity: "+bookQuantity+"\n";
		return ret;
		}//end of toString
}//end of StockIndexCard
