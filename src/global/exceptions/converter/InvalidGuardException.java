package global.exceptions.converter;

@SuppressWarnings("serial")
public class InvalidGuardException extends Exception{
	
	public InvalidGuardException () {
		System.out.println ("Converter Error: Invalid guard expression!\n");
	}
	
}
