package robotproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Logger {
	
	private File file;
    private FileOutputStream fileOutPut;
	
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
             
            fileOutPut = new  FileOutputStream(file);
        }
        catch(IOException e) {
          
        }
	}
	
	public void writeData( String data ) {
		
        try {
            String timeString = ((Integer)(int)System.currentTimeMillis()).toString();
            
            //tijd
            for(int i=0; i<timeString.length(); i++)
            {
            	fileOutPut.write((byte) timeString.charAt(i)); 
            }
            
            // comma
            fileOutPut.write((byte)(','));
            
            //data
            for(int i=0; i<data.length(); i++)
            {
            	fileOutPut.write((byte) data.charAt(i));
            }

            // nieuwe lijn
            fileOutPut.write((byte)('\n'));				

        }
        catch(IOException e) {
        	
        }
    }
	
	public void Stop()
    {
        try {
            fileOutPut.close();
        }
        catch(IOException e) {

        }		 
    }
}
