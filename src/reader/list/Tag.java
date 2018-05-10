package reader.list;

import java.util.LinkedList;
import java.util.List;

public class Tag extends Content{

	//Attributes
	private String name;
	private List<Attribute> attributes;
	private boolean close;
	
	//Constructor
	public Tag () {
		attributes = new LinkedList<Attribute>();
	}
	
	//Methods
	@Override
	public void setName (String name) {
		this.name = name;
	}
	
	@Override
	public String getName () {
		return this.name;
	}
	
	@Override
	public void addAttribute (Attribute attribute) {
		this.attributes.add(attribute);
	}
	
	@Override
	public List<Attribute> getAttributes () {
		return this.attributes;
	}
	
	@Override
	public void setClose () {
		this.close = true;
	}
	
	@Override
	public boolean getClose () {
		return this.close;
	}
	
}
