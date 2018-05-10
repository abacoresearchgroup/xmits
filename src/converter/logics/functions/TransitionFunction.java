package converter.logics.functions;

import global.exceptions.converter.TransitionException;
import global.logics.Builder;
import global.structure.Guard;
import global.structure.TransitionSystem;

import java.util.Hashtable;
import java.util.List;

import converter.collectors.guards.GuardDictionary;
import converter.logics.LogicTools;
import converter.logics.TransitionMemory;
import converter.logics.dictionaries.ElementDictionary;
import converter.logics.dictionaries.FunctionDictionary;
import converter.logics.interfaces.Function;
import converter.logics.structures.Element;
import converter.logics.structures.Transition;
import converter.logics.structures.Type;

public class TransitionFunction {
	
	//Attributes
	private FunctionDictionary functionDictionary;
	private ElementDictionary elementDictionary;
	private Builder builder;
	private GuardDictionary guardDictionary;
	private TransitionMemory transitionMemory;
	private LogicTools logicTools;
	
	//Constructor
	public TransitionFunction () {
		functionDictionary = FunctionDictionary.getInstance();
		elementDictionary = ElementDictionary.getInstance();
		builder = Builder.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		transitionMemory = TransitionMemory.getInstance();
		logicTools = new LogicTools();
	}

//--Public Methods------------------------------------------------------------------------------------------------------------------------------------------//
	public void process (Element element, List<Integer> path) throws TransitionException {		
		List<Integer> pathClone = logicTools.integerListClone(path);
		TransitionSystem position = builder.getPosition();
		Hashtable<String, Guard> guards = guardDictionary.getGuardListClone();
		String lastTransitionName = transitionMemory.getLastTransitionName();
		
		for (Transition transition : element.getContent().getOutgoingTransitions()) {		
			Element next = elementDictionary.getElement(transition.getOutgoingId());			
			if (next != null) {
				Function function = functionDictionary.getFunction(next.getType());
				if (function == null) {
					function = functionDictionary.getFunction(Type.Default);
				}
				builder.setPosition(position);
				guardDictionary.rebuildGuardList(guards);
				transitionMemory.setLastTransitionName(lastTransitionName);
				transitionMemory.setLastTransitionName(transition.getName());
				next.getContent().setLastTransition(transition);
				function.process(next, pathClone);
			}else{
				throw new TransitionException();
			}
		}
		
	}
	
}
