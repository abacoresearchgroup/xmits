package global.exceptions.converter;

@SuppressWarnings("serial")
public class StartException extends Exception{
	
	public StartException () {
		System.out.println("Converter Error: Your diagram have no begin!\n");
	}

}
