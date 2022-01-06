import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Runs the study program
 * prints and scans user input to run the study program
 * 
 * @author jessicaoathoudt
 *
 */
public class StudyBuddy 
{
	//instance var scanner saves input from user
	private Scanner input;
	
	/**
	 * driver for program
	 * @param args
	 */
	public static void main(String[] args)
	{
		//creates new StudyBuddy
		StudyBuddy sB = new StudyBuddy();
		//calls study
		sB.study();
	}
	/**
	 * prints the questions to screen
	 * allows user to answer
	 * checks if right or wrong
	 * keeps track of points, how many questions, total points
	 */
	public void study() 
	{
		//arraylist of questions
		ArrayList<Question> questions;
		//points set to 0
		int points = 0;
		//numCorrect set to 0
		int numCorrect = 0;
	
		System.out.println("\nWelcome to StudyBuddy! Press enter when you are ready to "
				+ "select the file holding your study question(s).\n");
		//new questionsMaker
		QuestionMaker qM = new QuestionMaker();
		//questions get teh arraylist questions
		questions = qM.getQuestions();
		//size is size of arraylist
		int size = questions.size();
		Scanner input = new Scanner(System.in);
		int userResponse = 0;
		System.out.println("The questions are loaded!\n");
		do 
		{
			try 
			{
				System.out.print("How many questions would you like to answer: ");
				
				userResponse = input.nextInt();
				if(userResponse > 0 && userResponse <= size) 
				{
					//if the userResponse is within the number of questions in the file
					System.out.println("\nOkay, you'll answer "+userResponse+" question(s)\n");
				}
				//if they are not
				if(userResponse > size || userResponse < 0) 
				{
					userResponse = 0;
					System.out.println("Sorry, you have loaded "+ size+" questions");
				}
			}
			//if they enter garbage
			catch(InputMismatchException exp) 
			{
				System.out.println("Sorry, that is not a valid input.");
				input.nextLine();
				userResponse = 0;
			}
		}
		//runs while userRespone is 0 aka when userResponse is not a number within the range
		while(userResponse == 0);

		//for i to the number to user gave
		for(int i = 0; i<userResponse; i = i + 1) 
		{
			//question
			Question ques = questions.get(i);
			//print the points and question
			System.out.println("Points: " + ques.getPoints());
			System.out.println("Question: "+ ques.getQuestion());
			//if the question has options (MC) get those options
			if(!(ques.getChoices() == null)) 
			{
				ques.getChoices();
			}
			//allow the user to answer
			System.out.print("Enter the answer to the question: ");
			String userAnswer = input.nextLine();

			//if the answer is pass
			if(userAnswer.toUpperCase().equals("PASS")) 
			{
				//skip the question
				System.out.println("Okay, we will skip that one!");
			}
			else if(userAnswer.toUpperCase().equals("DELAY")) 
			{
				//remove the question
				System.out.println("Okay, we will come back to that one!");
				questions.remove(ques);
				//add it to the end!
				//this works but not printing the options for MC questions!
				questions.add(userResponse-1, ques);
			}
			//if the user entered the right answer
			else if(ques.isCorrect(userAnswer)) 
			{
				System.out.println("Correct! You get "+ques.getPoints()+" points!\n");
				//total points increases
				points = points + ques.getPoints();
				//number correct increases
				numCorrect = numCorrect + 1;
			}
			else if(!ques.isCorrect(userAnswer))
			{
				//wrong answer
				/**
				 * for some reason, no matter what, my first answer is always false? I can't figure out why. All the other questions work perfectly,
				 * marking them wrong when they are wrong and right when they are right. But the first question, the userAnswer is always set to false?
				 * Even when the correct answer is false, the userAnswer is set to false and marked as incorrect (even though it obviously should be correct)
				 * only happens on the first question!!?!?!?!?!?!?!?!?! plz be merciful
				 */
				System.out.println("Incorrect! The answer is "+ques.getIsCorrectAns()+". You entered "+ ques.isCorrect(userAnswer)+". "
						+ "You lose "+ques.getPoints()+" points.\n");
				//lose points
				points = points - ques.getPoints();
			}
		}
		System.out.println("Of the "+userResponse+" questions you attempted, you got "+numCorrect+" correct!");
		System.out.println("You got a total of "+points+" point(s)!");
		if(points > 0) 
		{
			System.out.println("Great Job!");
		}
		if(points < 0) 
		{
			System.out.println("I've seen better >:/");
		}
		input.close();
	}
}


