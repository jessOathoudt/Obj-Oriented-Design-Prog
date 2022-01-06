/**
 * interface that has abstract methods
 * outline for Question TF, MC, and FB
 * 
 * @author jessicaoathoudt
 *
 */
public interface Question 
{
	/**
	 * will return question
	 * @return question
	 */
	public String getQuestion();
	
	/**
	 * will return true/false if user answer is right/wrong
	 * @param ans
	 * @return correct
	 */
	public boolean isCorrect(String ans);
	
	/**
	 * will return the correct answer
	 * @return answer
	 */
	public String getIsCorrectAns();
	
	/**
	 * will return the points for the question
	 * @return points
	 */
	public int getPoints();
	
	/**
	 * will return the choices for questions (null for Fb and TF)
	 * @return choices
	 */
	public String [] getChoices();

}
