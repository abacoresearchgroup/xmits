package converter.logics.structures;

import java.util.ArrayList;
import java.util.List;

public class Possibility {

	//Attributes
	private List<Element> elements;
	
	//Constructor
	public Possibility () {
		elements = new ArrayList<Element>();
	}
	
	//Methods
	public void addElement (Element element) {
		elements.add(element);
	}
	
	public List<Element> getElements () {
		return elements;
	}
	
}
