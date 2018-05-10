package reader.list;

public class Attribute {

	//Attributes
	private String name;
	private String value;
	
	//Constructor
	public Attribute () {
		
	}
	
	//Methods
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setValue (String value) {
		this.value = value;
	}
	
	public String getValue () {
		return this.value;
	}
}
