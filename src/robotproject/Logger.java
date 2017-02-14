package robotproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The logger class , will log any String to a file whit a system time for refence.
 * 
 * @author {Jasper van Hulst}
 * @version 0.1
 * @date 17/3/2015 
 */

public class Logger {
	private File file; ///< private File The instance of the file class
	private FileOutputStream fileOutput;
	
	/**
	 * Contructor for the logger class
	 *
	 * @param fileName The name that the file while have.
	 */
	public Logger(String fileName) {
		try {	        
			file = new File(fileName);
            if( !file.exists() ) {
            	file.createNewFile();
            }
            else {
            	file.delete();
            	file.createNewFile();
            }
             
            fileOutput = new  FileOutputStream(file);
        }
        catch(IOException e) {
          
        }
	}
	
	/**
	 * writeData will write any string whit system time (ms) to the file.
	 *
	 * @param data The string you want to write to the file.
	 */
	public void writeData(String data) {
		 try {
	            String timeString = ((Integer)(int)System.currentTimeMillis()).toString();
	            
	            //tijd
	            for(int i=0; i<timeString.length(); i++)
	            {
	            	fileOutput.write((byte) timeString.charAt(i)); 
	            }
	            
	            // comma
	            fileOutput.write((byte)(','));
	            
	            //data
	            for(int i=0; i<data.length(); i++)
	            {
	            	fileOutput.write((byte) data.charAt(i));
	            }

	            // nieuwe lijn
	            fileOutput.write((byte)('\n'));				

	        }
	        catch(IOException e) {
	        	
	        }
	}
	
	/**
	 * Stop must be called when you are done writing to the file.
	 *
	 */
	public void Stop() {
		 try {
			 	fileOutput.close();
	        }
	        catch(IOException e) {

	        }	
	}
}
