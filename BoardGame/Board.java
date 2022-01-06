//imports arrayList and HashMap
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Models a playing board for Etcha Sketch
 * Creates board, allows caller to move boardable objects,
 * Checks is cells are valid, 
 * @author jessica oathoudt
 *
 */
public class Board 
{
	/**
	 * instance variable creates the board out of a double array of Cells
	 */
	private Cell[][] board;
	/**
	 * instance variable holds the desired height of the board
	 */
	private int height;
	/**
	 * instance variable holds the desired width of the board
	 */
	private int width;
	/**
	 * instance variable that finds location in the board based on key.
	 */
	private HashMap<Boardable, Cell> elementPlace;
	
	/**
	 * instance variable that is true when jarvis and player share a cell, otherwise false
	 */
	private boolean hugged = false;

	/**
	 * Creates the board and fills it with either "#", "*", or " "
	 * @param height - stores the height of the board
	 * @param width - stores the width of the board
	 */
	public Board(int height, int width) 
	{
		this.height = height;
		this.width = width;
		//makes the board with height and width
		board = new Cell[height][width];
		int row = height;
		int col = width;
		//checker for conditions of board size
		if(row<1 || col < 1 || row>100 || col>100) 
		{
			throw new IllegalArgumentException("Row and Colum must be greater than 1 and less than 100");
		}
		//for all rows
		for (row = 0; row < height; row = row+1) 
		{
			//for all columns
			for (col = 0; col < width; col=col+1) 
			{
				//fill board
				board[row][col] = new Cell(row, col);
			}
		}
		//creates Hashmap
		elementPlace = new HashMap<Boardable, Cell>();
	}
	
	/**
	 * Moves elem on the board based on Direction. Returns true if valid movement and false if not
	 * @param dir - direction of movement
	 * @param elem - a Boardable element, so it can go on the board (will be Stylus)
	 * @return boolean - true if cell is valid, false if not
	 */
	public synchronized boolean move(Direction dir, Boardable elem) 
	{
		
		//finds the elem
		Cell cell = elementPlace.get(elem);
		//make column variable
		int xCol = cell.col;
		//make row variable
		int yRow = cell.row;
		//switching on dir
		switch (dir) 
		{
		//changes based on Direction
		//moves the elem on the Board
		case UP:
			yRow = yRow - 1;
			break;
		case DOWN:
			yRow = yRow + 1;
			break;
		case RIGHT:
			xCol = xCol + 1;
			break;
		case LEFT:
			xCol = xCol - 1;
			break;
		case UP_LEFT:
			xCol = xCol - 1;
			yRow = yRow - 1;
			break;
		case UP_RIGHT:
			xCol = xCol + 1;
			yRow = yRow - 1;
			break;
		case DOWN_LEFT:
			xCol = xCol - 1;
			yRow = yRow + 1;
			break;
		case DOWN_RIGHT:
			xCol = xCol + 1;
			yRow = yRow + 1;
			break;
		default:
			break;
		}
        // Check if cell is valid
		if (yRow >= 0 && yRow < height && xCol >= 0 && xCol < width) 
		{
			//remove the element
			board[cell.row][cell.col].removeElement(elem);
			//place the element at new spot
			placeElement(elem, yRow, xCol);
			return true;
		}
		else
		{	
			//the cell was not valid
			return false;
		}
	}

    //true if element is placed on the board
	/**
	 * Method that puts elem on the board if valid (adds the player/jarvis/hwtrap to the board)
	 * @param elem - this is a Boardable object
	 * @param row - row of board
	 * @param col - column of board
	 * @return boolean - returns true if elem added and false if not
	 */
	public synchronized boolean placeElement(Boardable elem, int row, int col) 
	{
		if (row >= 0 && row < height && col >= 0 && col < width) 
    	{
			//add the element before you make mapping 
			if(board[row][col].addElement(elem)) 
			{
				//make the mapping
				//hashMap
				elementPlace.put(elem, board[row][col]);
			}
			//return true
			return true;
		}
		//if element is not placed
		else 
		{
			return false;
		}
	}

	/**
	 * Nested Class Cell models a cell in the Board. Adds elem to cell, removes elem from cell, and 
	 * turns cell to a string
	 * @author jessica oathoudt
	 */
	private class Cell 
	{
    	/**
    	 * instance variable - holds the row
    	 */
		private int row;
        /**
    	 * instance variable - holds the column
    	 */
		private int col;
        /**
    	 * instance variable - boolean that is true when Cell is visible and false if not.
    	 */
		private boolean isVisible;
        /**
    	 * instance variable - array list of boardable elements
    	 */
		private ArrayList<Boardable> elements;

        /**
         * Cell constructor, sets the rows and columns and elements
         * @param row - horizontal cells
         * @param col - vertical cells
         */
		public Cell(int row, int col) 
		{
			this.row = row;
			this.col = col;
			elements = new ArrayList<Boardable>();
		}
        
        /**
         * Adds boardable element to board if elem is visible.
         * @param elem - boardable object (Stylus)
         * @return isVisible (true if element that is visible moves into a cell
         * 
         */
		public boolean addElement(Boardable elem) 
		{
			//have a problem with board of 1,2
			//hwTrap is null?
			if(elem.isVisible()) 
			{
				//if the element is visible
				isVisible = true;
			}
			//for each loop, all the boardable elements in the arraylist
			for(Boardable otherElem : elements) 
			{
				//if they can share with each other
				if(otherElem.share(elem)) 
				{
					//if the element getting added is visible
					if(elem.isVisible()) 
					{
						//then the cell becomes visible
						isVisible = true;
					}
				}
			}
			//return true or false if element was added
			return elements.add(elem);
		}

        /**
         * Removes elem from board
         * @param elem - boardable object
         * @return boolean - true if elem removed from ArrayList elements
         */
		public boolean removeElement(Boardable elem) 
		{
			//true or false if the element was removed
			return elements.remove(elem);
		}

		/**
         * Method that changes the string representation of cell based on isVisible boolean
         * @return - String "#" if isVisible is false, " " if it's true and "*" if the elem is in it
         *  If the Cells is not visible, it should return "#". If it is visible but empty, it should return " ". 
         *  If it is visible and contains elements, it should return the toString of the last element that was added to it.
         *  A Cell becomes visible if any element is added to it that is visible. 
         *  Once a cell is visible it should ever become not visible.
         */
		public String toString() 
		{
			//if false (not visible)
			if (!isVisible) 
			{
				//if not visible (false)
				return "#";
			} 
			else 
			{
				//if ArrayList elements is empty (visible)
				if (elements.size() == 0 && isVisible) 
				{
					return " ";
					// if elem is in cell
				} 
				else 
				{
					return elements.get(elements.size() - 1).toString();
				}
			}
		}
	}
	
	 /**
     * prints the board to console with height and width
     * "#" if not visible
     */
	public synchronized void printBoard() 
	{
		System.out.println();
    	//loop through height
		for (int i = 0; i < height; i++) 
		{
        	//loop through width
			for (int j = 0; j < width; j++) 
			{
            	//print the board
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Removes an element from arrayList elements
	 * The removeElement should remove the passed-in element from the board 
	 * (meaning from the Cell and the elementPlace HashMap.) 
	 * It should return true if the element was removed false otherwise.
	 * @param elem
	 * @return boolean - true if element is removed, false otherwise
	 */
	public boolean removeElement(Boardable elem) 
	{
		
		//had a problem with removing homeworkTraps
		
		boolean placed = false;
		//if the hashMap didn't find the element?
		if(elementPlace.remove(elem) != null) 
		{
			placed = true;
		}
		
		return placed;

		
	}
   
	/**
	 * Allows the hugged boolean to be changed from true and false. Jarvis should only be able to do this
	 * @param hugged
	 */
	public synchronized void setHugged(boolean hugged) 
	{
		//return whatever hugged is at the time (usually false)
		this.hugged = hugged;
	}
	
	/**
	 * Checks if jarvis has been hugged.  If hugged is true, beenHugged is true. If false, no hug
	 * @return boolean - beenHugged (true or false)
	 */
	public synchronized boolean beenHugged() 
	{
		boolean beenHugged = false;
		if(hugged == true) {
			beenHugged = true;
		}
		return beenHugged;
	}
	
	/**
	 * Finds the numeric value of the row an element is in
	 * returns row coordinate
	 * throws IllegalArgumentException if the element is not on the board
	 * @param elem
	 * @return int - the number value of the row
	 */
	public int getRow(Boardable elem) 
	{
		//rowCell using the hashKey to find the place of the element
		Cell rowCoordinate = elementPlace.get(elem);
		//if there is no coordinate(trying to move something not on board)
		if(rowCoordinate == null) 
		{
			throw new IllegalArgumentException("The element was not on the board");
		}
		//y gets the row found
		int y = rowCoordinate.row;
		return y;
	}
	
	/**
	 * finds the numeric value of the column an element is in
	 * returns column coordinate
	 * throws IllegalArgumentException if the element is not on the board
	 * @param elem
	 * @return int - the number value of the column
	 */
	public int getColum(Boardable elem) 
	{
		//finds hte location of the colCell on the board
		Cell colCoordinate = elementPlace.get(elem);
		//if he trys to move something not on the board
		if(colCoordinate == null) 
		{
			throw new IllegalArgumentException("The element was not on the board");
		}
		//x gets the column
		int x = colCoordinate.col;
		return x;
	}
}
