package converter.logics.dictionaries;

import java.util.HashMap;

import converter.logics.structures.Element;

public class ElementDictionary {

	//Attributes
	private static ElementDictionary uniqueInstance;
	private HashMap<String, Element> elements;
		
	//Constructor
	private ElementDictionary	() {
		elements = new HashMap<String, Element>();
	}
		
	//Static Methods
	public static ElementDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new ElementDictionary();
		}
		return uniqueInstance;
	}
		
	//Public Methods
	public void addElement (String id, Element element) {
		elements.put(id, element);
	}
		
	public Element getElement (String id) {
		return elements.get(id);
	}
	
	public void reset () {
		elements = new HashMap<String, Element>();
	}
	
}
