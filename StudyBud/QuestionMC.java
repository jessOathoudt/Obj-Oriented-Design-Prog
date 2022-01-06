//import scanner for caller input
import java.util.Scanner;

/**
 * Models a Multiple Choice question. Gets the points, question, number of Choices, choices, and answer
 * 
 * Will allow the user to answer t/f question and mark if correct or incorrect
 * 
 * @author jessica oathoudt
 *
 */
public class QuestionMC implements Question 
{
	//instance var that holds the points
	private int points;
	//instance var that holds the question
	private String question;
	//instance var that holds the number of choices for MC question
	private int numOfChoices;
	
	//private String choice1;
	//private String choice2;
	//private String choice3;
	
	//holds the choices in string form
	private String choices;
	//holds the answer
	private String answer;
	
	/**
	 * QuestionMC constructor. Takes points, question, numOfChoices, choices, and answer
	 * @param points
	 * @param question
	 * @param numOfChoices
	 * @param choices
	 * @param answer
	 * void - returns nothing
	 */
	public QuestionMC(int points, String question, int numOfChoices, String choices, String answer) 
	{
		this.points = points;
		this.question = question;
		this.numOfChoices = numOfChoices;
		this.choices = choices;
		this.answer = answer;
	}

	/**
	 * gets the question for MC question
	 * @return question
	 */
	public String getQuestion() 
	{
		return question;
	}
	
	/**
	 * returns the choices to select from for MC questions
	 * each options will have a Letter assinged to it
	 * @return twoOP
	 */
	public String [] getChoices() 
	{
		//splits the string by ;
		String [] twoOP = choices.split(";");
		//loops through the split string
		for(int i = 1; i < twoOP.length; i = i + 1) 
		{
			//list of letters
			String alphabet = (" ,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
			//splits list of letters by , 
			String [] select = alphabet.split(",");
			//prints out options with letter in front
			System.out.println((select[i]+".) "+twoOP[i]));
			choices = "";
		}
		//return twoOP
		return twoOP;
	}
	/**
	 * checks to see if answer user gave is correct
	 * true if correct and false if not
	 * @return correct
	 */
	public boolean isCorrect(String ans) 
	{
		//new scanner
		Scanner sc = new Scanner(System.in);
		String userAnswer;
		//boolean set to false
		boolean correct = false;
		userAnswer = sc.nextLine();
		//if the answer is correct
		if(ans.equalsIgnoreCase(answer))
		{
			//true
			correct = true;
		}
		//answer is not correct
		else if(!ans.equalsIgnoreCase(answer))
		{
			//return false
			correct = false;
		}
		//return true or false depending on answer
		return correct;
	}

	/**
	 * returns the correct answer for a MC questions
	 * @return answer
	 */
	public String getIsCorrectAns() 
	{
		return answer;
	}

	/**
	 * returns the points for a MC question
	 * @return points
	 */
	public int getPoints() 
	{
		return points;
	}
	
	/**
	 * Returns the number of choices for a MC question
	 * @return numOfChoices
	 */
	public int getNumOfChoices() 
	{
		return numOfChoices;
	}
}