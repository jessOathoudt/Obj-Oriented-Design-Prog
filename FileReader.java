//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * reads in files to get the questions
 * @author jessica oathoudt
 *
 */
public class FileReader 
{
	//creates a scanner
	private Scanner fileScanner;

	
	/**
	 * FileReader constructor
	 */
	public FileReader()
	{
		//creates object chooser of type JFileChooser
		JFileChooser chooser = new JFileChooser();
        
		//Exception Checker
        try {   
        	//if no file, error input not selected
            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            {
                throw new Error("Input file not selected");
            }
            //questionFile of type File gets selected by chooser
            File questionFile = chooser.getSelectedFile();
            //file gets scanned in
            fileScanner = new Scanner(questionFile);
          
        } 
        catch (FileNotFoundException e) 
        {
        	//if file not found print data not found
            System.err.println("Data file not found.");
        } 
        catch (Exception e) 
        {
        	//catches all exceptions
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }
	}
	
	/**
	 * returns true if file has next line
	 * @return boolean
	 */
	public boolean fileHasNextLine()
	{
		return this.fileScanner.hasNextLine();
	}
	
	public int getSize() 
	{
		int size = 0;
		for(int i =0; i < 100; i = i + 1)
		{
			if(this.fileScanner.hasNextLine()) 
			{
				size = size + i;
			}
		}
		return size;
	}
	
	/**
	 * gets the next line of the file
	 * @return String
	 */
	public String getNextLineOfFile()
	{
		return this.fileScanner.nextLine();
	}
	
	
	/**
	 * finishes reading the file
	 */
	public void finalize()
	{
		try
		{
			//close the file
			this.fileScanner.close();
		}
		catch(Exception ex)
		{
			//catches all exceptions
			 System.err.println("A mysterious error occurred on closing Scanner.");
	         ex.printStackTrace(System.err);
		}
	}
}
