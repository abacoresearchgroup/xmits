package converter.logics.parallel;

import global.exceptions.converter.ForkException;
import global.exceptions.converter.MultiStateException;
import global.exceptions.converter.TransitionException;
import global.tools.IdGenerator;

import java.util.ArrayList;
import java.util.List;

import converter.logics.TransitionMemory;
import converter.logics.structures.Content;
import converter.logics.structures.Element;
import converter.logics.structures.MultiElement;
import converter.logics.structures.Transition;
import converter.logics.structures.Type;

public class ParallelTools {
	
	//Attributes
	private NextSolver nextSolver;
	private IdGenerator idGenerator;
	private TransitionMemory transitionMemory;
	
	//Constructor
	public ParallelTools () {
		nextSolver = new NextSolver();
		idGenerator = IdGenerator.getInstance();
		transitionMemory = TransitionMemory.getInstance();
	}

	//Methods
	public Element createMultiElement (List<Element> elements) throws MultiStateException {
		
		if (elements.size() < 2) {
			throw new MultiStateException();
		}
		
		MultiElement multiElement = new MultiElement();
		for (Element element : elements) {
			multiElement.addElement(element);
		}
		
		Content content = new Content();
		content.setName(createMultiStateContent(multiElement));
		content.setId(idGenerator.getStringId());
		
		Element output = new Element();
		output.setMultiElement(multiElement);
		output.setContent(content);
		output.setType(Type.MultiState);
		
		return output;
		
	}

	public Element solveFork (List<Transition> outgoingTransitions) throws ForkException {
		
		if (outgoingTransitions.size() < 2) {
			throw new ForkException();
		}
		
		Element output = new Element();
		
		try {
			List<Element> nexts = nextSolver.getNexts(outgoingTransitions);		
			output = createMultiElement(nexts);
		} catch (MultiStateException | TransitionException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public String createMultiStateContent(MultiElement multiElement) {
		List<String> content = new ArrayList<String>();
		for (Element element : multiElement.getElements()) {
			if (element.getType().equals(Type.State)) {
				content.add(element.getContent().getName());
			}
			if (element.getType().equals(Type.MultiState)) {
				content.add(createMultiStateContent(element.getMultiElement()));
			}
		}
		String output = "";
		for (int i = 0; i < content.size(); i++) {
			if (i == 0) {
				output = content.get(i) + " and ";
				continue;
			}
			if (i == content.size() - 1) {
				output = output + content.get(i);
				continue;
			}
			output = output + content.get(i)  + " and ";
		}
		
		if (!transitionMemory.getLastTransitionName().equals("")) {
			output = transitionMemory.getLastTransitionName() + " & " + output;
		}
		
		return output;
	}

}
