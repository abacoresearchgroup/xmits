package converter.logics.functions;

import global.logics.Builder;
import global.structure.Guard;
import global.structure.GuardValue;
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

public class DecisionFunction implements Function{
	
	//Attributes
	private Builder builder;
	private GuardDictionary guardDictionary;
	private FunctionDictionary functionDictionary;
	private ElementDictionary elementDictionary;
	private TransitionMemory transitionMemory;
	private LogicTools logicTools;
	
	//Constructor
	public DecisionFunction () {
		builder = Builder.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		functionDictionary = FunctionDictionary.getInstance();
		elementDictionary = ElementDictionary.getInstance();
		transitionMemory = TransitionMemory.getInstance();
		logicTools = new LogicTools();
	}

	@Override
	public void process (Element decision, List<Integer> path) {

		List<Integer> pathClone = logicTools.integerListClone(path);
		TransitionSystem position = builder.getPosition();
		Hashtable<String, Guard> guards = guardDictionary.getGuardListClone();
		String lastTransitionName = transitionMemory.getLastTransitionName();
	
		Transition transitionLeft = decision.getContent().getOutgoingTransitions().get(0);
		Transition transitionRight = decision.getContent().getOutgoingTransitions().get(1);
		
		Element elementTrue = new Element();
		Element elementFalse = new Element();
		
		if (transitionLeft.getGuardValue().equals(GuardValue.True) || transitionRight.getGuardValue().equals(GuardValue.False)) {
			elementTrue = elementDictionary.getElement(transitionLeft.getOutgoingId());
			elementFalse = elementDictionary.getElement(transitionRight.getOutgoingId());
		}
		if (transitionRight.getGuardValue().equals(GuardValue.True) || transitionLeft.getGuardValue().equals(GuardValue.False)) {
			elementTrue = elementDictionary.getElement(transitionRight.getOutgoingId());
			elementFalse = elementDictionary.getElement(transitionLeft.getOutgoingId());
		}
		
		guardDictionary.setTrue(decision.getContent().getId());
		Function trueFunction = functionDictionary.getFunction(elementTrue.getType());
		trueFunction.process(elementTrue, pathClone);
		
		builder.setPosition(position);
		guardDictionary.rebuildGuardList(guards);
		transitionMemory.setLastTransitionName(lastTransitionName);

		guardDictionary.setFalse(decision.getContent().getId());
		Function falseFunction = functionDictionary.getFunction(elementFalse.getType());
		falseFunction.process(elementFalse, pathClone);
		
	}

}
