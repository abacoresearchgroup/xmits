package global.structure;

public class Guard {
	
	//Attribute
	private String name;
	private GuardValue value;
	
	//Constructor
	public Guard () {
		this.value = GuardValue.DC;
	}
	
	//Methods
	public void  setName (String name) {
		this.name = name;
	}
	
	public void setValue (GuardValue value) {
		this.value = value;
	}
	
	public String getName () {
		return this.name;
	}
	
	public GuardValue getValue () {
		return this.value;	
	}

	public Guard getClone () {
		Guard clone = new Guard();
		clone.setName(name);
		clone.setValue(value);
		return clone;
	}

	public boolean matches(Guard guard) {
		if (!guard.getName().equals(this.name)) return false;
		if (!guard.getValue().equals(this.value)) return false;
		return true;
	}
	
}
