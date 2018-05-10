package converter.logics.structures;

import java.util.ArrayList;
import java.util.List;

public class MultiElement {

	//Attributes
	private List<Element> elements;
	
	//Constructor
	public MultiElement () {
		elements = new ArrayList<Element>();
	}
	
	//Methods
	public void addElement (Element element) {
		elements.add(element);
	}
	
	public List<Element> getElements () {
		return elements;
	}
	
	public MultiElement getClone () {
		MultiElement clone = new MultiElement();
		for (Element element : elements) {
			clone.addElement(element);
		}
		return clone;
	}
	
}
