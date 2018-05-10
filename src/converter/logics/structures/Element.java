package converter.logics.structures;

public class Element {

	//Attributes
	private Content content;
	private MultiElement multiElement;
	private Type type;
	
	//Methods
	public void setContent (Content content) {
		this.content = content;
	}
	
	public Content getContent () {
		return content;
	}
	
	public void setMultiElement (MultiElement multiElement) {
		this.multiElement = multiElement;
	}
	
	public MultiElement getMultiElement () {
		return multiElement;
	}
	
	public void setType (Type type) {
		this.type = type;
	}
	
	public Type getType () {
		return type;
	}
	
	public Element getClone () {
		Element clone = new Element();
		clone.setContent(content.getClone());
		if (type.equals(Type.MultiState)) {
			clone.setMultiElement(multiElement.getClone());
		}
		clone.setType(type);
		return clone;
	}
	
}
