package global.exceptions.converter;

@SuppressWarnings("serial")
public class ForkException extends Exception{

	public ForkException () {
		System.out.println ("Converter Error: Fork node have less than two elements!\n");
	}
	
}
