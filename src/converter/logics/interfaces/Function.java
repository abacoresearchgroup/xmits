package converter.logics.interfaces;

import java.util.List;

import converter.logics.structures.Element;

public interface Function {

	public void process (Element element, List<Integer> path);
	
}
