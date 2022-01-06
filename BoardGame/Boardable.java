/**
 * specifies what player does
 * specifies what homeworkTrap does
 * player must have method isVisible
 * 
 * @author jessicaoathoudt
 *
 */
public interface Boardable 
{
	/**
	 * Checks the visibility of the assingment
	 * @return boolean - like a light switch - true if elem is visible
	 */
	public boolean isVisible();
	
	/**
	 * Checks the sharability of an element
	 * dictates how objects of type Boardable interact if they are sharing a Cell. 
	 * If a Cell already contains an element A when its addElement method is invoked with the argument B, then it should call A.share(B).
	 * If the share call returns true, the cell should add B to its elements list otherwise it should return false and not add B
	 * @param elem
	 * @return boolean - true if elem shares with other
	 */
	public boolean share(Boardable elem);
	
	/**
	 * String representation of elements
	 * @return - String
	 */
	public String toString();
}
