import java.util.Scanner;
import java.util.Random;

/** Retirement Simulator, we will use methods to find investment, raises,
 * and returns future savings
 * 
 * @author Jessica Oathoudt
 *
 */
public class RetirmentSimulator 
{
	// declares class variables so they can be used
	/**
	 * stores input for initial investment
	 */
	private static double initialInvestment;
	/**
	 * stores input for initial salary
	 */
	private static double initialSalary;
	/**
	 * current salary that is changed later
	 */
	private static double currentSalary;
	/**
	 * the lower bound for salary investment
	 */
	private static double investmentRangeMin;
	/**
	 * the upper bound for salary investment
	 */
	private static double investmentRangeMax;
	/**
	 * the lower bound for returns
	 */
	private static double returnRangeMin;
	/**
	 * the upper bound for returns
	 */
	private static double returnRangeMax;
		
	/**
	 * stores input for years to retirement and simulates the account
	 */
	private static int yearsToRetire;
	/**
	 * the lower bound for expected raises
	 */
	private static double raiseRangeMin;
	/**
	 * the upper bound for expected raises
	 */
	private static double raiseRangeMax;
		
		
		
	/**
	 * the maximum salary used in projection
	 */
	private static double maxSalary;
	/** 
	 * array to store investment balance
	 */
	private static double investmentBalance[];
	/**
	 * stores minimum investment
	 */
	private static double minInvestment;
	/**
	 * stores the maximum investment
	 */
	private static double maxInvestment;
	/**
	 * stores the average investment
	 */
	private static double averageInvestment;
	/**
	 * stores minimum raise
	 */
	private static double minRaise;
	/**
	 * stores maximum raise
	 */
	private static double maxRaise;
	/**
	 * stores average raise
	 */
	private static double averageRaise;
	/**
	 * stores minimum return
	 */
	private static double minReturn;
	/**
	 * stores maximum return
	 */
	private static double maxReturn;
	/**
	 * stores average return
	 */
	private static double averageReturn;
		

	/**
	* @param args builds the methods
	*/	
	public static void main(String[] args) 
	{
		// calls methods
		userInputs();
		runSimulation();
		printResults();
	} 	// end of main
	/**
	 * This method gives the year by year simulation of user's retirement account
	 */
	public static void runSimulation() 
	{
		Random randomNumber = new Random();
		// making arrays to store values
		double investmentRange[] = new double[yearsToRetire];
		double raiseRange[] = new double[yearsToRetire];
		double returnRange[] = new double[yearsToRetire];
		investmentBalance = new double[yearsToRetire];
		
		// assigning variables. max/min are constants
		minInvestment = Double.MAX_VALUE;
		maxInvestment = Double.MIN_VALUE;
		averageInvestment = 0;
		minRaise = Double.MAX_VALUE;
		maxRaise = Double.MIN_VALUE;
		averageRaise = 0;
		minReturn = Double.MAX_VALUE;
		maxReturn = Double.MIN_VALUE;
		averageReturn = 0;
		
		/**
		 * assign arrays and fill them with averages for each variable
		 */
		// loops through array of years starting at 0 to the end
		for (int i = 0; i < yearsToRetire; i = i + 1) 
		{
			// calculates amount of money that should be added to investment account each year
			investmentRange[i] = investmentRangeMin + (investmentRangeMax
					- investmentRangeMin) * randomNumber.nextDouble();
			//loops through array and finds the max of investments
			if (investmentRange[i] < minInvestment) 
			{
				// minimum Investment user input get an array to store through all years
				minInvestment = investmentRange[i];
			} 	// if (investmentRaing[i] < minInvestment)
			
			//loops through array and finds the max of investments
			if (investmentRange[i] > maxInvestment) 
			{
				// max investment user input gets an array to store through all years
				maxInvestment = investmentRange[i];
			}	// if (investmentRaing[i] > maxInvestment)
			
			averageInvestment = averageInvestment + investmentRange[i];
			
			// calculates the yearly raise range and generates a random number in range 
			raiseRange[i] = raiseRangeMin + (raiseRangeMax - raiseRangeMin) * randomNumber.nextDouble();
			//loops through array and finds the min of raises
			if (raiseRange[i] < minRaise) 
			{
				minRaise = raiseRange[i];
			}	// if (raiseRange[i] < minRaise)
			
			//loops through array and finds the max of raises
			if (raiseRange[i] > maxRaise) 
			{
				maxRaise = raiseRange[i];
			}	// if (raiseRange[i] > maxRaise)
			
			averageRaise = averageRaise + raiseRange[i];
			
			// calculates how much the total investments earned/lost each year
			returnRange[i] = returnRangeMin + (returnRangeMax - returnRangeMin) * randomNumber.nextDouble();
			//loops through array and finds the min of returns
			if (returnRange[i] < minReturn) 
			{
				minReturn = returnRange[i];
			}	// if (returnRange[i] < minReturn)
			
			//loops through array and finds the max of returns
			if (returnRange[i] > maxReturn) 
			{
				maxReturn = returnRange[i];
			}	// if (returnRange[i] > maxReturn)
			
			averageReturn = averageReturn + returnRange[i];
		}	// for (int i = 0; i < yearsToRetire; i = i + 1)
		
		
		
		//finds the averages of investments, raises, and returns and stores them to variable name
		averageInvestment = averageInvestment / yearsToRetire;
		averageRaise = averageRaise / yearsToRetire;
		averageReturn =  averageReturn / yearsToRetire;
		
		
		/**
		 * Ranging to fill arrays
		 */
		// declaring variable currentYearInvestment to go 
		double currentYearInvestment;
		
		// loop through array of years starting at 0 till the end
		for(int i = 0; i < yearsToRetire; i = i + 1) 
		{
			//math for investment of each year until retirement
			// turns investment to percent and multiplies it by salary for each year till retirement
			currentYearInvestment = (investmentRange[i] / 100.0) * currentSalary;
			
			if (i==0) 
			{
				// adds initialInvestment to current year investment
				investmentBalance[i] = currentYearInvestment + initialInvestment;
			} 	// if (i == 0)
			else
				// adds past (stored) years to current year
				investmentBalance[i] = currentYearInvestment + investmentBalance[i - 1];

			investmentBalance[i] = investmentBalance[i] + investmentBalance[i] * (returnRange[i] / 100.0);
			
			maxSalary = currentSalary;
			
			// calculates next year's retirement savings
			// turns raise to percent
			currentSalary = currentSalary + currentSalary * (raiseRange[i] / 100.0);
		}	// for(int i = 0; i < yearsToRetire; i = i + 1)
	}	//  end of method (public static void runSimulation())

	/**
	 * takes user input and checks if values was correct
	 */
	private static void userInputs() 
	{
		//creates new scanner object to take user inputs
		Scanner input = new Scanner(System.in);
		// makes prompted questions show up one at a time
		initialInvestment = currentSalary = initialSalary = -1;
		investmentRangeMin = investmentRangeMax = returnRangeMin = returnRangeMax = raiseRangeMin = raiseRangeMax = -1;
		
		System.out.print("Please enter the current amount you have in your retirement account: ");
		while (initialInvestment < 0) 
		{
			initialInvestment = input.nextDouble();
			
			// prompts next question if initialInvestment is greater than or equal to 0
			if (initialInvestment >= 0) 
			{
			}	// if (initialInvestment >= 0)
			else
				// prints error message if initialInvestment is less than 0
				System.out.print("ERROR: The retirement account balance must be zero or greater."
						+ " Please re-enter your current amount you have in your " + "retirement account: ");
		}	//while (initialInvestment < 0)
		
		System.out.print("Please enter your current salary: ");
		while (initialSalary < 0) 
		{
			initialSalary = input.nextDouble();
			
			// prompts next question if initialSalary is greater than or equal to 0
			//and assigns inputed salary to variable current salary
			if (initialSalary >= 0) 
			{
				currentSalary = initialSalary;
			}	// if (initialSalary >= 0)
			else
				// prints error message if initialSalary is less than 0
				System.out.print("ERROR: Your current salary must be zero or greater. Please"
						+ " re-enter your current salary: ");
		}	//while (initialSalary < 0)
		
		
		System.out.print("As a percentage of your salary, please enter the minimum amount you"
				+ " plan to save for retirement in any given year: ");
		while (investmentRangeMin < 0) 
		{
			investmentRangeMin = input.nextDouble();
			
			// prompts next question is investmentRangeMin is greater than or = to 0
			if (investmentRangeMin >= 0) 
			{
			}	// if (investmentRangeMin >= 0)
			else
				// prints error message if investmentRangeMin is less than 0
				System.out.print("ERROR: Your minimum investment range value must be zero or"
						+ " greater. Please re-enter the value: ");
		}	// while (investmentRangeMin < 0)
		
		
		System.out.print("As a percentage of your salary, please enter the maximum amount"
				+ " you plan to save for retirement in any given year: ");
		while (investmentRangeMax < investmentRangeMin) 
		{
			investmentRangeMax = input.nextDouble();
			
			// prompts next question if investmentRangeMax is greater than or = to investmentRangeMin 
			// also can't be greater than 100
			if (investmentRangeMax <= 100 && investmentRangeMax >= investmentRangeMin) 
			{
			}	// if (investmentRangeMax <= 100 && investmentRangeMax >= investmentRangeMin)
			else
				//prints error message if investmentRangeMax fails to be greater than min or is greater than 100
				System.out.print("ERROR: Your maximum investment range value must be greater than"
						+ " the minimum investment range. Please re-enter the value: ");
		}	// while (investmentRangeMax < investmentRangeMin)
		
		System.out.print("As a percentage, please enter the expected minimum yearly return"
				+ " for your investments: ");
		returnRangeMin = input.nextDouble();
		while (returnRangeMin < -100) 
		{
			System.out.print("ERROR: Minimum yearly return cannot be less than -100%."
					+ " Please re-enter the value: ");
			returnRangeMin = input.nextDouble();
		}	// while (returnRangeMin < -100 && returnRangeMin > 100)
		
		
		System.out.print("As a percentage, please enter the expected maximum yearly return"
				+ " for your investments: ");
		returnRangeMax = input.nextDouble();
		while (returnRangeMax < returnRangeMin) 
		{
			System.out.print("ERROR: Your maximum expected yearly return value must be greater than minimum expected yearly"
					+ " returns. Please re-enter the value: ");
			returnRangeMax = input.nextDouble();
		}	// while (returnRangeMax < returnRangeMin)
		
		System.out.print("As a percentage, the minimum pay raise you expect to receive in any given year: ");
		raiseRangeMin = input.nextDouble();
		while (raiseRangeMin < -100) 
		{
			System.out.print("ERROR: Minimum year raise cannot be less than -100%. Please re-enter the value: ");
			raiseRangeMin = input.nextDouble();
		}	// while (raiseRangeMin < -100 && raiseRangeMin > 100)
		
		
		System.out.print("As a percentage, the maximum pay raise you expect to receive in any give year: ");
		raiseRangeMax = input.nextDouble();
		while (raiseRangeMax < raiseRangeMin) 
		{
			System.out.print("ERROR: Your maximum expected year raise value must be greater than"
					+ " the minimum expected year raise. Please re-enter the value: ");
			raiseRangeMax = input.nextDouble();
		}	// while (raiseRangeMax < raiseRangeMin)
		
		
		System.out.print("Please enter the number of years until retirement: ");
		yearsToRetire = input.nextInt();
		input.close();
	}	// end of (private static void userInputs())
	
	/**
	 * prints the user values, simulates values, and histogram
	 */
	private static void printResults() 
	{
		// prints out messages and output from simulation
		System.out.println("\nYou Entered: ");
		System.out.println("\nInitial Investment: $" + initialInvestment);
		System.out.println("\nInitial Salary: $" + initialSalary);
		System.out.println("\nYearly percentage of salary saved: " + investmentRangeMin +
				 "% - " + investmentRangeMax + "%");
		System.out.println("\nRange of yearly returns: " + returnRangeMin + "% - "
				+ returnRangeMax + "%");
		System.out.println("\nYearly salary increase range: " + raiseRangeMin + "% - "
				+ raiseRangeMax + "%");
		System.out.println("\nNumber of years until retirment: " + yearsToRetire + "\n");
		
			
		System.out.println("The simulation generated the following values: \n");
		System.out.printf("Yearly percentage of salary saved-- min: %.2f%% max: %.2f%% average: %.2f%%\n",
				minInvestment, maxInvestment, averageInvestment);
		System.out.printf("\nRange of yearly returns-- min: %.2f%% max: %.2f%% average: %.2f%%\n", minReturn, 
				maxReturn, averageReturn);
		System.out.printf("\nYearly percentage of salary increase-- min: %.2f%% max: %.2f%% average: %.2f%%\n", minRaise, 
				maxRaise, averageRaise);
		System.out.printf("\nThe maximum salary used in the simulation was: $%.2f",
				 maxSalary);
		System.out.print("\n\nYear:");
		System.out.printf("\n0:#($%.2f)\n", initialInvestment);
		
		//Loop prints the array as a histogram
		for(int i = 0; i < yearsToRetire; i = i + 1) 
		{
			System.out.print(i + 1 + ":");
			for(int j = 0; j <= i; j = j + 1) 
				System.out.print("#");
			
			System.out.printf("($%.2f)\n", investmentBalance[i]);
		} 	// for(int i = 0; i < yearsToRetire; i = i + 1)
	}	// end of (private static void printResults())	
}	// end of (public class RetirementSimulator)
