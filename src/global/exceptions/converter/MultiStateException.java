package global.exceptions.converter;

@SuppressWarnings("serial")
public class MultiStateException extends Exception{

	public MultiStateException () {
		System.out.println ("Converter Error: MultiState have less than 2 elements!");
	}
	
}
