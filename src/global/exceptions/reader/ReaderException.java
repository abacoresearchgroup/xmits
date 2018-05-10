package global.exceptions.reader;

@SuppressWarnings("serial")
public class ReaderException extends Exception{

	public ReaderException (String errorMessage, Exception exception) {		
		System.out.println ("Reader Error: " + errorMessage + "\n");
		exception.printStackTrace();
	}
	
}
