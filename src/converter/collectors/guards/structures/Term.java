package converter.collectors.guards.structures;

public class Term {

	//Attributes
	private String term;
	private TermNature nature;
		
	//Constructor
	public Term () {
		term = "";
	}
	
	//Methods
	public String getTerm () {
		return term;
	}
	
	public void addCharacter (char character) {
		term = term + character;
	}
	
	public TermNature getNature () {
		return nature;
	}
	
	public void setNature (TermNature nature) {
		this.nature = nature;
	}
	
}
