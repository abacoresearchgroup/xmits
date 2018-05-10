package global.exceptions.tuts;

@SuppressWarnings("serial")
public class NoSequenceDiagramException extends Exception{

	public NoSequenceDiagramException () {
		System.out.println ("Tuts Error: You need at least one sequence diagram!\n");
	}
	
}
