import java.util.Scanner;

/**
 * Models a fill in the blank question. Takes points, question, and answer
 * 
 * @author jessicaoathoudt
 *
 */
public class QuestionFB implements Question 
{
	//instance var to hold points
	private int points;
	//instance var to hold question
	private String question;
	//instance var to hold answer
	private String answer;
	
	/**
	 * QuestionFB constructor. Makes the question! 
	 * @param points
	 * @param question
	 * @param answer
	 */
	public QuestionFB(int points, String question, String answer)
	{
		this.points = points;
		this.question = question;
		this.answer = answer;
	}

	/**
	 * returns the question
	 * @return question
	 */
	public String getQuestion()
	{
		return question;
	}
	/**
	 * returns the choices for Fb (will be null)
	 * @return choices
	 */
	public String [] getChoices()
	{
		String[]  choices = null;
		return choices;
	}
	/**
	 * checks if answer is correct
	 * true if yes, false if no
	 * @return correct
	 */
	public boolean isCorrect(String ans) 
	{

		Scanner sc = new Scanner(System.in);
		String userAnswer;
		//set correct to false
		boolean correct = false;
		userAnswer = sc.nextLine();
		//if the answer is correct
		if(ans.equalsIgnoreCase(answer))
		{
			//true
			correct = true;
		}
		else 
		{
			//false
			correct = false;
		}
		return correct;
	}

	/**
	 * gets the correct answer
	 * @return answer
	 */
	public String getIsCorrectAns() 
	{
		return answer;
	}

	/**
	 * gets the points for the question
	 * @return points
	 */
	public int getPoints() 
	{
		return points;
	}
}
