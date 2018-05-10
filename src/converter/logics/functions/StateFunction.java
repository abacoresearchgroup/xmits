package converter.logics.functions;

import global.exceptions.converter.TransitionException;
import global.logics.Builder;
import global.structure.Guard;
import global.structure.Message;
import global.structure.State;
import global.structure.TransitionSystem;

import java.util.List;

import converter.collectors.guards.GuardDictionary;
import converter.logics.TransitionMemory;
import converter.logics.interfaces.Function;
import converter.logics.structures.Element;

public class StateFunction implements Function{
	
	
	//Attributes
	private Builder builder;
	private GuardDictionary guardDictionary;
	private TransitionFunction transitionFunction;
	private TransitionMemory transitionMemory;
			
	//Constructor
	public StateFunction () {
		builder = Builder.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		transitionFunction = new TransitionFunction();
		transitionMemory = TransitionMemory.getInstance();
	}

	//Public Methods
	@Override
	public void process (Element element, List<Integer> path) {

		Message message = new Message();
		message.setContent(createContent(element.getContent().getName()));
		message.setContentId(element.getContent().getId());
		
		State state = new State();
		state.setMessage(message);
		for (Guard guard : guardDictionary.getGuardList().values()) {
			state.addGuard(guard);
		}
		
		TransitionSystem transitionSystem = new TransitionSystem();
		transitionSystem.addState(state);
		transitionSystem.setDiagram(element.getContent().getDiagram());
		
		builder.addChild(transitionSystem);
		builder.setPosition(transitionSystem);
		
		try {
			transitionFunction.process(element, path);
		} catch (TransitionException e) {
			e.printStackTrace();
		}
		
	}

	//Private Methods
	private String createContent(String name) {
		String content = "";
		if (transitionMemory.getLastTransitionName().equals("")) {
			content = name;
		}else{
			content = transitionMemory.getLastTransitionName() + " & " + name;
			transitionMemory.clearLastTransitionName();
		}
		return content;
	}

}
