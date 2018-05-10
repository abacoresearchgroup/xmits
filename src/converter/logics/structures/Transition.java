package converter.logics.structures;

import global.structure.GuardValue;

public class Transition {
	
	//Attributes
	private String id;
	private String name;
	private GuardValue guardValue;
	private String incommingId;
	private String outgoingId;
		
	//Methods
	public void setId (String id) {
		this.id = id;
	}
	
	public String getId () {
		return id;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
	
	public void setGuardValue (GuardValue guardValue) {
		this.guardValue = guardValue;
	}
	
	public GuardValue getGuardValue () {
		return guardValue;
	}
	
	public String getIncommingId () {
		return incommingId;
	}
	
	public void setIncommingId (String incommingId) {
		this.incommingId = incommingId;
	}
	
	public String getOutgoingId () {
		return outgoingId;
	}
	
	public void setOutgoingId (String outgoingId) {
		this.outgoingId = outgoingId;
	}
	
	public Transition getClone () {
		Transition clone = new Transition();
		clone.setId(id);
		clone.setName(name);
		clone.setIncommingId(incommingId);
		clone.setOutgoingId(outgoingId);
		return clone;
	}

}
