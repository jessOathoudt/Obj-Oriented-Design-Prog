import java.util.ArrayList;
/**
 * Makes the questions
 * finds the points, question, answer, and choices for Mc from the file and makes adds them to questions list
 * 
 * @author jessicaoathoudt
 *
 */
public class QuestionMaker 
{
	//instance var creates new FIleReader
	private FileReader fr = new FileReader();
	//instance var creates new StudyBuddy
	private StudyBuddy sB = new StudyBuddy();
	//instance var creates arraylist of type question
	private ArrayList<Question> questions;
	
	/**
	 * Goes through file and sorts based on TF/MC/FB
	 * then finds points, question, answer for questions (choices for MC too)
	 * then adds those questions to arraylist questions
	 * @return questions
	 */
	public ArrayList<Question> getQuestions() 
	{
		//arrayList of questions (empty)
		ArrayList<Question> questions = new ArrayList<Question>();
		//while the file has a next line
		while(fr.fileHasNextLine()) 
		{
			//curString is that line
			String curString = fr.getNextLineOfFile();
			//make an array of the line split up by ;
			String [] aLine = curString.split(";");
			//for each string in the array list of strings
			for(String chunk: aLine) 
			{
				//if its starts with TF
				if(chunk.equals("TF")) 
				{
					//find the points
					int points = Integer.parseInt(aLine[aLine.length-1]);
					//find hte question
					String question = aLine[1];
					//find the answer
					String answer = aLine[2];
					//make TF question
					QuestionTF tFQuestion = new QuestionTF(points, question, answer);
					//add it to questions
					questions.add(tFQuestion);
				}
				//starts with MC
				else if(chunk.equals("MC")) 
				{
					int points = Integer.parseInt(aLine[aLine.length-1]);
					String question = aLine[1];
					//finds the number of options to select for MC
					int numOfChoices = Integer.parseInt(aLine[2]);
					//sets the choices and answer to first index of it
					String choices = aLine[3];
					String answer = aLine[4];
					// for i less than the number of choices
					for(int i =0; i<numOfChoices; i =i+1) 
					{
						//add other options
						choices = choices.concat(";"+aLine[3+i]);
						//adapt answer to differnet options
						answer = aLine[4+i];
					}
					//create new MC question
					QuestionMC mCQuestion = new QuestionMC(points, question, numOfChoices, choices, answer);
					//add it to list
					questions.add(mCQuestion);
				}
				//starts with FB
				else if(chunk.equals("FB")) 
				{
					//points
					int points = Integer.parseInt(aLine[aLine.length-1]);
					//question
					String question = aLine[1];
					//answer
					String answer = aLine[2];
					//create new question FB
					QuestionFB fBQuestion = new QuestionFB(points, question, answer);
					//add it to list
					questions.add(fBQuestion);
				}
			}
		}
		//returns the list of questions
		return questions;
	}
	
	/**
	 * finds the number of questions in the list
	 * @return numOfQuestions
	 */
	public int getNumOfQuestions() 
	{
		//number of questions is the size of the questions list
		int numOfQuestions = questions.size();
		return numOfQuestions;
	}
}