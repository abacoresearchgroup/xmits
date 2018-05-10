package converter.logics.parallel;

import global.exceptions.converter.MultiStateException;
import global.exceptions.converter.TransitionException;

import java.util.ArrayList;
import java.util.List;

import converter.logics.LogicTools;
import converter.logics.structures.Element;
import converter.logics.structures.MultiElement;
import converter.logics.structures.Possibility;
import converter.logics.structures.Type;
import converter.tools.Combination;

public class PossibilityGenerator {
	
	//Attributes
	private Combination combination;
	private NextSolver nextSolver;
	private LogicTools logicTools;
	private ParallelTools parallelTools;
	
	//Constructor
	public PossibilityGenerator () {
		combination = new Combination();
		nextSolver = new NextSolver();
		logicTools = new LogicTools();
		parallelTools = new ParallelTools();
	}
	
//--Public Methods------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public List<Possibility> generate(Element multiState) {
		
		List<Possibility> output = new ArrayList<Possibility>();
		List<Integer> allElements = new ArrayList<Integer>();
		
		//All Elements
		for (int i = 1; i <= multiState.getMultiElement().getElements().size(); i ++) {
			allElements.add(i);
		}
		
		for (List<Integer> comb : combination.generate(multiState.getMultiElement().getElements().size())) {
			
			//Keep Elements
			List<Integer> keepElements = new ArrayList<Integer>();
			for (Integer a : allElements) {
				keepElements.add(a);
			}
			
			//End Elements
			List<Element> endElements = new ArrayList<Element>();
			for (Integer i : comb) {
				keepElements.remove(i);
				Element element = multiState.getMultiElement().getElements().get(i - 1);
				try {
					endElements = logicTools.joinElementList(endElements, nextSolver.getNexts(element.getContent().getOutgoingTransitions()));
				} catch (TransitionException e) {
					e.printStackTrace();
				}
			}
			
			output.add(generatePossibility(multiState.getMultiElement(), endElements, keepElements));
			
		}
		
		return output;
	}

//--Private Methods--------------------------------------------------------------------------------------------------------------------------------------------------------------//
	private Possibility generatePossibility(MultiElement multiState, List<Element> endElements, List<Integer> keepElements) {
		
		Possibility output = new Possibility();
		
		List<Element> elements = new ArrayList<Element>();
		
		for (Element element : endElements) {
			if (element.getType().equals(Type.Join)) {
				output.addElement(element);
			}else{
				elements.add(element);
			}
		}
		
		for (Integer i : keepElements) {
			elements.add(multiState.getElements().get(i - 1));
		}
		
		if (elements.size() > 1) {
			try {
				Element multiElement = parallelTools.createMultiElement(elements);
				output.addElement(multiElement);
			} catch (MultiStateException e) {
				e.printStackTrace();
			}
		}else{
			for (Element element : elements) {
				output.addElement(element);
			}
		}
		
		return output;
	}

	
}
