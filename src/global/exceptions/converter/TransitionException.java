package global.exceptions.converter;

@SuppressWarnings("serial")
public class TransitionException extends Exception{

	public TransitionException () {
		System.out.println("Converter Error: A transition have no target!\n");
	}
	
}
