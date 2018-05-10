package global.exceptions.converter;

@SuppressWarnings("serial")
public class NotSupportedDiagramException extends Exception{

	public NotSupportedDiagramException (String xmitype) {
		System.out.println ("Converter Exception: The '" + xmitype + "' diagram is not supported by the Converter\n");
	}
	
}
