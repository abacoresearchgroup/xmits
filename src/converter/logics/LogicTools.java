package converter.logics;

import java.util.ArrayList;
import java.util.List;

import converter.logics.structures.Element;

public class LogicTools {
	
	//Methods
	public List<Integer> integerListClone (List<Integer> integerList) {
		List<Integer> output = new ArrayList<Integer>();
		for (Integer i : integerList) {
			output.add(i);
		}
		return output;
	}
	
	public List<Element> joinElementList (List<Element> listOne, List<Element> listTwo) {
		List<Element> output = new ArrayList<Element>();
		for (Element element : listOne) {
			output.add(element);
		}
		for (Element element : listTwo) {
			output.add(element);
		}
		return output;
	}

}
