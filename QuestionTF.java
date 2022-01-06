
//import scanner so I can take caller inputs
import java.util.Scanner;

/**
 * Models a true or false question. Gets the number of points it is worth, 
 * the question in string form, and the answer in string form
 * 
 * Will allow the user to answer t/f question and mark if correct or incorrect
 * 
 * @author jessica oathoudt
 */
public class QuestionTF implements Question 
{

	//instance var that holds the point value for t/f questions
	private int points;
	// instance var that holds the t/f question
	private String question;
	// instance var that holds answer of t/f question
	private String answer;
	// instance var for scanner. It will hold caller input.
	private Scanner sc;
	// instance var reference to QuestionTF
	QuestionTF qTF;
	// instance var reference to QuestionMaker
	QuestionMaker qM;

	/**
	 * Constructor for QuestionTF. Takes points, question, and answer
	 * @param points
	 * @param question
	 * @param answer
	 * void - returns nothing
	 */
	public QuestionTF(int points, String question, String answer) 
	{
		this.points = points;
		this.question = question;
		this.answer = answer;	
	}

	/**
	 * Gets the question part of the QuestionTF
	 * returns the question in string form
	 * @return question
	 */
	public String getQuestion()
	{
		return question;
	}
	/**
	 * returns a null list of strings because t/f questions have no options to choose
	 * @return choices
	 */
	public String [] getChoices() 
	{
		String[]  choices = null;
		return choices;
	}

	/**
	 * Checks if the answer the caller gave is correct
	 * if it is, they gain points
	 * if it is not, they lose points
	 * if caller enters pass, the question is skipped
	 * if caller enters delay, the question is added to the end of the arrayList so it can be asked again
	 * returns true if answer is right and false if not
	 * @return correct
	 */
	public boolean isCorrect(String ans) 
	{

		Scanner sc = new Scanner(System.in);
		String userAnswer;
		boolean correct;
		userAnswer = sc.nextLine();
		//if the answer is correct
		if(ans.equalsIgnoreCase(answer))
		{
			//print correct
			correct = true;
		}
		else 
		{
			correct = false;
		}
		//returns correct
		return correct;
	}//end of isCorrect()

	/**
	 * returns the correct answer in string form (should be true or false)
	 * @return answer
	 */
	public String getIsCorrectAns() 
	{
		//returns the correct answer
		return answer;
	}
	
	/**
	 * returns the value of points each question is worth
	 * @return points
	 */
	public int getPoints() 
	{
		//returns integer value of points
		return points;
	}
}
